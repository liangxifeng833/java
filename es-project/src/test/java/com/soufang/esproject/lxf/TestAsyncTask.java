package com.soufang.esproject.lxf;

import com.soufang.esproject.ApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Future;

/**
 * Description: es-project
 * Create by liangxifeng on 19-5-6
 */
public class TestAsyncTask extends ApplicationTests {
    @Autowired
    private Task task;
    @Test
    public void sync() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> task1 = task.doTaskOne();
        Future<String> task2 =task.doTaskTwo();
        Future<String> task3 =task.doTaskThree();
        while(true) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

}
