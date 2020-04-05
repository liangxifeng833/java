package com.example.readinglist;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class TestJson {
    @Test
    public void testJsonObj()
    {
        //JsonObject和JsonArray区别就是JsonObject是对象形式，JsonArray是数组形式
        //创建JsonObject第一种方法
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("UserName", "ZHULI");
        jsonObject.put("age", "30");
        jsonObject.put("workIn", "ALI");
        System.out.println("jsonObject1：" + jsonObject);
        System.out.println(jsonObject.toString());
        //创建JsonObject第二种方法
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("UserName", "ZHULI");
        hashMap.put("age", "30");
        hashMap.put("workIn", "ALI");
        System.out.println("jsonObject2：" + JSONObject.toJSONString(hashMap));
    }

    @Test
    public void testJsonArr()
    {
        //创建一个JsonArray方法1
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, "ZHULI");
        jsonArray.add(1, "30");
        jsonArray.add(2, "ALI");
        System.out.println("jsonArray1：" + jsonArray);

        //创建JsonArray方法2
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("ZHULI");
        arrayList.add("30");
        arrayList.add("ALI");
        System.out.println("jsonArray2：" + JSONArray.toJSONString(arrayList));

        //组装一个复杂的JSONArray
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("UserName", "ZHULI");
        jsonObject2.put("age", "30");
        jsonObject2.put("workIn", "ALI");
        //jsonObject2.element("Array", arrayList);
        System.out.println("jsonObject2：" + jsonObject2);
    }
}
