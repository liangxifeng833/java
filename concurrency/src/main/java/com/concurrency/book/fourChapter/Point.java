package com.concurrency.book.fourChapter;

/**
 * 不变坐标点类,线程安全
 * Create by liangxifeng on 19-8-29
 */
public class Point {
    public final int x, y;

    public Point(int x,int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
