package com.concurrency.book.fourChapter;

/**
 * 可变坐标点类,线程安全
 * 将拷贝构造函数实现为this(p.x, p.y)，那么会产生竞态条件，
 * 而私有构造函数则可以避免这种竞态条件。这是私有构造函数捕获模式的一个实例
 */
public class SafePoint {
    private int x, y;
    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    private SafePoint(int[] arr) {
        this.x = arr[0];
        this.y = arr[1];
    }

    public synchronized int[] get() {
        return new int[]{x,y};
    }

    public synchronized void set(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "SafePoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
