package com.lxf;

import java.util.Random;
class TestA {

    public void testA(){}
    public String testA(String a){ return null;}
}
public class TestClass1 {
    public static void main(String[] args) {
        TestA testA = new TestA();
        //打印系统的所有属性,list()方法将结果发送给它的参数System.out
        //System.getProperties().list(System.out);
        //打印属性java.library.path的值 = /usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib
        //System.out.println(System.getProperty("java.library.path"));
        Random rand = new Random();
        int i,j,k;
        //１～100之间的随机整数
        j = rand.nextInt(100)+1;
        System.out.println("j:"+j);
        k = rand.nextInt(100)+1;
        System.out.println("k:"+k);


        i = 0;
        //注意outer:与下面for之间不能有代码
        outer:
        for(j=0; j<100; j++)
        {
            System.out.println("j = "+j);
            inner:
            for(;i<10; i++)
            {
                System.out.println("i="+i);
                if(i == 2)
                {
                    System.out.println("continue");
                    continue;
                }
                if(i == 3)
                {
                    System.out.println("break");
                    i++;
                    break;
                }
                if(i==7)
                {
                    System.out.println("continue outer");
                    i++;
                    continue outer;
                }
                if(i==8)
                {
                    System.out.println("break outer");
                    break outer;
                }
                for(int f = 0; f < 5; f++)
                {
                    if (f == 3)
                    {
                        System.out.println("continue inner");
                        continue  inner;
                    }
                }
            }
        }
        System.out.println("hello");
    }
}
