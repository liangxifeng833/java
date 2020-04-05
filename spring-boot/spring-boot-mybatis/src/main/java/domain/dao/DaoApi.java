package domain.dao;

import java.util.List;
import java.util.Map;

/**
 * 领域层业务Dao泛型接口
 * @author: liangXiFeng
 * @date: 2018-1-19
 * @param <T>
 */
public interface DaoApi<T> {

    /**
     * 通过主键查询一条记录
     * @param id
     * @return 返回查询实体
     */
    T getOne(Long id);

    /**
     * 新增数据
     * @param entity 实体对象
     * @return 返回影响行数
     */
    Integer insert(T entity);

    /**
     * 修改数据, 根据实体对象中的主键
     * @param entity
     * @return 返回影响行数
     */
    Integer update(T entity);

    /**
     * 根据主键删除数据
     * @param id
     * @return 返回影响行数
     */
    Integer delete(Long id);
    /**
     * 根据不通查询条件查询多条记录
     * @param params
     *  Map list = new HashMap();
     *   //equals
     *  Map equals = new HashMap();
     *   equals.put("brand_name","string");
     *   equals.put("user_id","0");
     *   //whereIn
     *   List whereIn = new ArrayList();
     *   whereIn.add(3);
     *   whereIn.add(6);
     *   whereIn.add(13);
     *   whereIn.add(10);
     *   //大于
     *   String gtStr = "2018-01-02 22:22:12";
     *   Timestamp gt = Timestamp.valueOf(gtStr);
     *   //小于
     *   String ltStr = "2018-01-02 22:22:10";
     *   Timestamp lt = Timestamp.valueOf(ltStr);
     *   //大于等于
     *   String gteStr = "2018-01-02 22:22:10";
     *   Timestamp gte = Timestamp.valueOf(gteStr);
     *   //小于等于
     *   String lteStr = "2018-01-02 22:22:11";
     *   Timestamp lte = Timestamp.valueOf(lteStr);
     *   Map between = new HashMap();
     *   between.put("start","2018-01-02 22:22:12");
     *   between.put("end","2018-11-02 22:22:12");
     *   list.put("gting",gt);
     *   list.put("lting",lt);
     *   list.put("gteing",gte);
     *   list.put("lteing",lte);
     *   list.put("between",between);
     *   list.put("whereIn",whereIn);
     *   list.put("equals",equals);
     *   list.put("order","asc");
     * @return List
     */
    List getList(Map params);

    /**
     * 根据不通查询条件查询总记录条数
     * @param params
     * @return Integer 总条数
     */
    Integer getCount(Map params);
}
