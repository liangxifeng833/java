package domain.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类负责处理JsonObj和JsonArray相关信息
 * @author : LiangXiFeng
 * @date : 2018-01-21
 */
@Component
public class JsonTool {
    /**
     * 定义泛型方法, 将参数对象类型转换为指定类型的List
     * @param obj
     * @param entity
     * @param <E>
     * @return
     * @throws Exception
     */
    public static <E> List<E> paramObjToList (Object obj, Class<E> entity) throws Exception
    {
        //转换为JSONArray
        JSONArray jsonArr = JSONArray.fromObject(obj);
        List<E> list = new ArrayList<E>();
        if(list != null)
        {
            for (Object o: jsonArr) {
                //将java对象转换为JSONObject
                JSONObject jsonObj = JSONObject.fromObject(o);
                //将JSONObject转换为JavaBean
                E entityNew = (E) JSONObject.toBean(jsonObj,entity.newInstance().getClass());
                //将转换后的实体add到list中
                list.add(entityNew);
            }
        }
        return list;
    }

    /**
     * 将java对象换为指定的javaBean
     * @param obj
     * @param e
     * @param <E>
     * @return
     * @throws Exception
     */
    public static <E> E paramObjToBean(Object obj, Class<E> e) throws  Exception
    {
        JSONObject jsonObj = JSONObject.fromObject(obj);
        E javaBean = (E) JSONObject.toBean(jsonObj,e.newInstance().getClass());
        return javaBean;
    }
    /**
     * 将json参数换为指定Map类型
     * @param params
     * @return  map
     * @throws Exception
     */
    public static Map paramsToMap(Map<String,Object> params) throws  Exception
    {
        //判断是否有 equals 查询
        if(params.get("equals") != null)
        {
            // 把json格式字符串转换为 java.util.Map
            String jsonStr = (String) params.get("equals");
            ObjectMapper objMap = new ObjectMapper();
            Map equals = objMap.readValue(jsonStr, Map.class);
            params.put("equals",equals);
        }
        //判断是否有 noequals 查询
        if(params.get("noEquals") != null)
        {
            // 把json格式字符串转换为 java.util.Map
            String jsonStrNo = (String) params.get("noEquals");
            ObjectMapper objMap = new ObjectMapper();
            Map equals = objMap.readValue(jsonStrNo, Map.class);
            params.put("noEquals",equals);
        }
        //判断是否有whereIn查询
        if(params.get("whereIn") != null)
        {
            //接受brandId的对象并转换为JSONArray
            JSONArray jsonArr = JSONArray.fromObject(params.get("whereIn"));
            List whereIn = (List)JSONArray.toCollection(jsonArr);
            //在参数中加入whereIn信息(将原brandId所对应的对象覆盖掉)
            params.put("whereIn",whereIn);
        }
        //判断是否有符号查询(> ,< ,<= ,>=)
        if(params.get("symbol") != null) {
            String symbolStr = (String) params.get("symbol");
            com.alibaba.fastjson.JSONArray jsonArray;
            //将json字符串转换成json数组
            jsonArray = JSON.parseArray(symbolStr);
            List symbolList = new ArrayList();
            for (int i = 0; i < jsonArray.size(); i++) {
                //将数组中的元素转换成对象
                com.alibaba.fastjson.JSONObject jsonObject = jsonArray.getJSONObject(i);
                Map map = new HashMap();
                map.put("key", jsonObject.getString("key"));
                map.put("compare", jsonObject.getString("compare"));
                map.put("value", jsonObject.getString("value"));
                symbolList.add(map);
            }
            params.put("symbol", symbolList);
        }
        return params;
    }
}
