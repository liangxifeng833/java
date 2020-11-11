package com.lxf;

public class TestClass1 {
    public static void main(String[] args) {
        //打印系统的所有属性,list()方法将结果发送给它的参数System.out
        System.getProperties().list(System.out);
        //打印属性java.library.path的值 = /usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib
        System.out.println(System.getProperty("java.library.path"));
    }
}
