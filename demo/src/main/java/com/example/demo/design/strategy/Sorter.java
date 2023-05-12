package com.example.demo.design.strategy;

/**
 * 冒泡排序算法
 * @author liangxifeng
 * @date 2023/5/12 11:03
 */

public class Sorter<T> {
    public void sort(T[]arr, IComparator comparator ) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = 1;

            for (int j = i+1; j < arr.length; j++) {
                minPos = comparator.compare(arr[j],arr[minPos]) == -1 ? j : minPos;
            }
            swap(arr,i,minPos);
        }
    }

    private void swap(T[] arr, int i, int j) {
        T temp =  arr[i];
        arr[i] = arr[j];
        arr[j] =  temp;
    }
}
