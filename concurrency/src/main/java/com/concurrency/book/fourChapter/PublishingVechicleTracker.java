package com.concurrency.book.fourChapter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 安全发布底层状态的车辆追踪器
 * 操作的坐标点为线程安全且可变的SafePoint
 * Create by liangxifeng on 19-8-29
 */
public class PublishingVechicleTracker {
    private final ConcurrentHashMap<String,SafePoint> locations;
    private final Map<String,SafePoint> unmodifiabledMap;

    public PublishingVechicleTracker(Map<String, SafePoint> SafePoints) {
        this.locations = new ConcurrentHashMap<>(SafePoints);
        this.unmodifiabledMap = Collections.unmodifiableMap(locations);
    }

    public Map<String,SafePoint> getLocations() {
        return unmodifiabledMap;
    }

    public SafePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocations(String id, int x, int y) {
      if(!locations.containsKey(id)) {
          locations.get(id).set(x,y);
      }
    }

    public static void main (String[] args) {
        Map<String,SafePoint> map = new HashMap<>();
        map.put("1",new SafePoint(1,2));
        map.put("2",new SafePoint(3,4));
        PublishingVechicleTracker pvt = new PublishingVechicleTracker(map);
        System.out.println(pvt.getLocations());
        //修改车辆位置信息
        SafePoint safePoint = pvt.getLocation("1");
        safePoint.set(10,20);
        List a= Collections.synchronizedList(new ArrayList<>());
        //输出：
        //{1=SafePoint{x=1, y=2}, 2=SafePoint{x=3, y=4}}
        //{1=SafePoint{x=10, y=20}, 2=SafePoint{x=3, y=4}}
        System.out.println(pvt.getLocations());
    }
}
