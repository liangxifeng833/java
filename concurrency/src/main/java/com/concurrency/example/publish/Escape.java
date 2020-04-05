package com.concurrency.example.publish;

import com.concurrency.annoations.NotRecommend;
import com.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: 对象逸出
 * Create by liangxifeng on 19-7-18
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {
    private int thisCanbeEscape = 0;
    //构造器
    public Escape() {
        new InnerClass();
        //...其他一些动作,在其他一些构造动作完成之前就将this发布出去了
    }
    //内部类
    private class InnerClass {
        public InnerClass() {
            log.info("{}",Escape.this.thisCanbeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape(); //输出0
    }
}
