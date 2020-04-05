package com.soufang.esproject.lxf;

public class TestA {
    public static void main(String[] args){
        System.out.println( isRed( Color.RED ) );
        System.out.println( getType(Color.RED) );

    }

    public static String getType(Object o){ //获取变量类型方法
        return o.getClass().toString(); //使用int类型的getClass()方法
    }
    static boolean isRed (Color color){
        if(Color.RED.equals("color")){
            return true;
        }
        return false;
    }
}
