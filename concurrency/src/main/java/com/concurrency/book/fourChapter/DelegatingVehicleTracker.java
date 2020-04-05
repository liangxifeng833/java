package com.concurrency.book.fourChapter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于委派的车辆追踪器(将线程安全委托给ConcurrentHashMap)
 * Create by liangxifeng on 19-8-29
 */
public class DelegatingVehicleTracker {
    private final ConcurrentHashMap<String,Point> locations;
    private final Map<String,Point> unmodifiabledMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiabledMap = Collections.unmodifiableMap(locations);
    }

    public Map<String,Point> getLocations() {
        return unmodifiabledMap;
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocations(String id, int x, int y) {
        if (locations.replace(id,new Point(x,y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name:" + id);
        }
    }

    public static void main (String[] args) {
        Map<String,Point> map = new HashMap<>();
        map.put("1",new Point(1,2));
        map.put("2",new Point(3,4));
        DelegatingVehicleTracker de = new DelegatingVehicleTracker(map);
        System.out.println(de.getLocations());
    }
}
