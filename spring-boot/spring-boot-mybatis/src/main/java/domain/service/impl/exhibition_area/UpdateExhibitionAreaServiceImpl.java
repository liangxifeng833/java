package domain.service.impl.exhibition_area;

import domain.dao.impl.AreaDao;
import domain.dao.impl.ExhibitionAreaDao;
import domain.dao.impl.ExhibitionDao;
import domain.domain.DomainResponse;
import domain.domain.ExhibitionArea;
import domain.service.interfaces.exhibition_area.UpdateExhibitionAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 修改展位与区域关系信息逻辑实现类
 * @author ningyachao 2018-02-02
 */
//propagation 传播行为 isolation 隔离级别  rollbackFor 回滚规则
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
@Service
public class UpdateExhibitionAreaServiceImpl implements UpdateExhibitionAreaService {
    @Autowired
    private ExhibitionAreaDao exhibitionAreaDao;
    @Autowired
    private ExhibitionDao exhibitionDao;
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private InsertExhibitionAreaServiceImpl insertHandle;

    /**
     * 换区操作
     * @param entity 实体对象
     * @return code说明
     *      0 修改失败
     *      1 修改成功
     *      2 接收必要参数失败
     *      3 展位主表中无记录
     *      4 区域表中无记录
     *      5 旧的关联记录不存在
     *      6 对应区域没有变更,修改失败
     */
    @Override
    public DomainResponse<?> update(ExhibitionArea entity) {
        //验证参数
        DomainResponse checkRes = insertHandle.checkParams(entity);
        if (checkRes.getCode() == 1)
        {
            String name = entity.getExhibitionName().trim();
            //进行换区操作,需要将之前的关联关系状态变更为过期
            //查询关联表,将旧数据状态变为0,新增新的关联关系
            Map<String,Object> map = new HashMap<String,Object>();
            Map<String,Object> equals = new HashMap<String,Object>();
            equals.put("exhibition_name", name);
            equals.put("state", 1);
            map.put("equals", equals);
            map.put("limiting", 1);
            List<ExhibitionArea> res = exhibitionAreaDao.getList(map);
            if (res.size() == 0)
                return new DomainResponse<String>(5,"旧的关联记录不存在","");
            ExhibitionArea old = res.get(0);
            if (old.getAreaId() == entity.getAreaId())
                return new DomainResponse<String>(6,"对应区域没有变更,修改失败","");
            old.setState(0);
            Date now = new Date();
            old.setValidCutoff(now);
            exhibitionAreaDao.update(old);
            //新增记录---对数据进行处理
            entity.setState(1);
            //截止时间当前时间+100年
            Calendar calendar = new GregorianCalendar(2118, 12, 30,0,0,0);
            Date date = calendar.getTime();
            entity.setValidCutoff(date);
            int number = exhibitionAreaDao.insert(entity);
            if( number == 1 ){
                Long id = entity.getId();
                return new DomainResponse<Long>(1,"修改成功",id);
            }else {
                return new DomainResponse<String>(0,"修改失败","");
            }
        }else{
            return checkRes;
        }
    }
}
