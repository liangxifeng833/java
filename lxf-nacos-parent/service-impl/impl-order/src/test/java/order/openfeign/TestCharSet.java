package order.openfeign;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import order.infrastructure.dao.IMerchantContractSignedMapper;
import order.infrastructure.po.MerchantContractSignedPO;
import order.infrastructure.util.TransitionUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单元测试数据库字段编码转换（读取latin1显示为utf8）
 * @author liangxifeng
 * @date 2022-01-06
 */
public class TestCharSet extends ApplicationTests{
    @Autowired
    private IMerchantContractSignedMapper mapper;

    @Test
    public void testSelectList() throws UnsupportedEncodingException {
        LambdaQueryWrapper<MerchantContractSignedPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.gt(MerchantContractSignedPO::getContractCid, 38100);

        MerchantContractSignedPO signedPO = new MerchantContractSignedPO();
        //signedPO.setAName("中文测试");
        //signedPO.setContractCid(17306);
        //QueryWrapper<MerchantContractSignedPO> wrapper = new QueryWrapper<>();
        //wrapper.eq("contract_cid", 17306);
        //wrapper.eq("A_name", "haha");
        //List<MerchantContractSignedPO> entitys = mapper.selectList(wrapper);
        //wrapper.gt(MerchantContractSignedPO::getContractState,2);
        List<MerchantContractSignedPO> list = mapper.selectList(wrapper);
        list.forEach(System.out::println);

        //assertEquals(5, list.size());
    }

    @Test
    public void testGetOne() {
        MerchantContractSignedPO po = mapper.selectById(38103);
        //MerchantContractSignedPO po = mapper.selectById(38067);
        System.out.println(po);
    }

    /**
     * 新增合同签订表数据
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testAdd() throws UnsupportedEncodingException {
        MerchantContractSignedPO addPO = new MerchantContractSignedPO();
        //addPO.setAName(TransitionUtil.utf8ToLatin1("中文测试---bak"));
        addPO.setAName("中文测试---中国");
        addPO.setConMerchantId("K1001001002");
        addPO.setConID("H002");
        addPO.setConResource("B-001-0022");
        addPO.setStDate(LocalDate.now());
        addPO.setEndDate(LocalDate.now());
        int rows = mapper.insert(addPO);
        System.out.println(addPO.getContractCid());
    }

    @Test
    public void objectToMap(){
        MerchantContractSignedPO addPO = new MerchantContractSignedPO();
        addPO.setAName("中文测试");
        addPO.setConMerchantId("K1001001001");
        addPO.setConID("H001");
        addPO.setConResource("A-001-0011");
        addPO.setStDate(LocalDate.now());
        addPO.setEndDate(LocalDate.now());
        Object object = addPO;
        Class<?> aClass = object.getClass();
        //Bean转Map
        Map<String, Object> map = BeanUtil.beanToMap(object);
        for (String s : map.keySet()) {
            if(map.get(s) instanceof String && map.get(s).equals("H001")) {
                System.out.println(s+",是字符串类型值＝"+map.get(s));
                map.put(s,"H001-replace");
                System.out.println(s+",转码后的值＝"+map.get(s));
            }
        }
        //MerchantContractSignedPO newPO = aClass.map;
        Object poNew = JSON.parseObject(JSON.toJSONString(map), aClass);
        //Object poNew = BeanUtil.
        System.out.println(poNew);
    }

}
