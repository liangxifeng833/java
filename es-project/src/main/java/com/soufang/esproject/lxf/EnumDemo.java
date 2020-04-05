package com.soufang.esproject.lxf;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;

/**
 * Description: demo
 * Create by liangxifeng on 2019-4-14
 */
public class EnumDemo {
    public static final int RED = 0x1;
    public static final int GREEN = 0x2;
    public static final int BLUE = 0x3;
    public int color;

    /**
     * 不使用枚举类类型, color可以存储除了以上三个常量之外, 还可以存储其他int类型的数据
     */
    @Test
    public void test1(){
        color = RED;
        color = 4;
    }

    @Override
    public String toString() {
        return "EnumDemo{" +
                "colorEnum=" + colorEnum +
                '}';
    }

    /**
     * 定义一个枚举类型的变量, 因为Color是个枚举类型
     * 使用枚举类型 colorEnum只能赋值为Color中定义的三个对象.
     */
    public Color colorEnum;
    @Test
    public void test2(){
        colorEnum = Color.RED;
        //打印名称, 输出: RED
        System.out.println(colorEnum.name());
        //打印需要, 输出: 0
        System.out.println(colorEnum.ordinal());
        //打印名称,输出: RED, 与.name()一致
        System.out.println(colorEnum.toString());
        //打印所属类型, 输出: com.soufang.esproject.lxf.Color
        System.out.println(colorEnum.getClass().getName());

        //获取枚举中所有值, 输出: [RED, GREEN, BLUE]
        Color[] values = Color.values();
        System.out.println(Arrays.toString(values));
    }

    /**
     * set 和 map 的枚举类型
     */
    @Test
    public void test3(){
        /**
         * 枚举set
         * 将Color中定义的所有枚举对象赋值到set集合中, 用来管理枚举元素的集合
         */

        EnumSet<Color> set = EnumSet.allOf(Color.class);
        for (Color c: set){
            System.out.println(c);
        }

        /**
         * 枚举map
         * 泛型key要用枚举类型
         */
        EnumMap<Color,String> map = new EnumMap<>(Color.class);
        map.put(Color.RED,"red");
        map.put(Color.GREEN,"green");
        map.put(Color.BLUE,"blue");
    }

}
