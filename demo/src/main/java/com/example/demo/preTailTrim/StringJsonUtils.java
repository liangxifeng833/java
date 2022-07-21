package com.example.demo.preTailTrim;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 利用fastjson实现json字符串转map功能
 * @author liangxifeng
 * @date 2022/7/21 14:16
 */

public class StringJsonUtils {
    /**
     * @Description: 把jsonString转为Map
     * @param jsonString
     * @return
     */
    public static Map<String, Object> jsonStringToMap(String jsonString) {
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        for (Object k : jsonObject.keySet()) {
            Object o = jsonObject.get(k);
            if (o instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<>();
                Iterator<Object> it = ((JSONArray) o).iterator();
                while (it.hasNext()) {
                    Object obj = it.next();
                    list.add(jsonStringToMap(obj.toString()));
                }
                map.put(k.toString(), list);
            } else if (o instanceof JSONObject) {
                // 如果内层是json对象的话，继续解析
                map.put(k.toString(), jsonStringToMap(o.toString()));
            } else {
                // 如果内层是普通对象的话，直接放入map中
                // map.put(k.toString(), o.toString().trim());
                if (o instanceof String) {
                    map.put(k.toString(), o.toString().trim());
                } else {
                    map.put(k.toString(), o);
                }
            }
        }
        return map;
    }
}
