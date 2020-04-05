package com.concurrency.book.fourChapter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于监视器模式的车辆追踪器 (内部使用内置锁synchronized维护不变性)
 * Create by liangxifeng on 19-8-28
 */
public class MonitorVehicleTracker {
    private final Map<String,MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String,MutablePoint> getLoactions() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalArgumentException("No such Id:"+id);
        }
        loc.x = x;
        loc.y = y;
    }

    private static Map<String,MutablePoint> deepCopy(Map<String,MutablePoint> map) {
        Map<String,MutablePoint> result = new HashMap<>();
        map.forEach((k,v)->{
            result.put(k,new MutablePoint(map.get(k)));
        });
        return Collections.unmodifiableMap(result);
    }

    public static void main(String[] args) {
        new Thread(()->{
            Map<String,MutablePoint> map = new HashMap<>();
            map.put("1",new MutablePoint());
            MonitorVehicleTracker monitorVehicleTracker = new MonitorVehicleTracker(map);
            monitorVehicleTracker.getLoactions();
            System.out.println("thread1=>"+monitorVehicleTracker.getLoactions());
        }).start();

        new Thread(()->{
            Map<String,MutablePoint> map = new HashMap<>();
            map.put("1",new MutablePoint());
            MonitorVehicleTracker monitorVehicleTracker = new MonitorVehicleTracker(map);
            monitorVehicleTracker.setLocation("1",22,33);
            System.out.println("thread2=>"+monitorVehicleTracker.getLoactions());
        }).start();

    }
}
