package com.concurrency.book.fourChapter;

/**
 * 可变坐标点类,线程不安全
 * Create by liangxifeng on 19-8-28
 */
public class MutablePoint {
    public int x,y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }

    @Override
    public String toString() {
        return "MutablePoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
