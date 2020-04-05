package com.lxf.spring.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态工厂方法：直接调用某一个类的静态方法可以返回Bean实例
 * @author lxf
 *
 */
public class StaticFactory {
    private static Map<String,Car> cars = new HashMap<String,Car>();
    static{
        cars.put("audi", new Car("audi"));
        cars.put("ford", new Car("ford"));
    }
    /**
     * 静态工厂方法返回对应Bean实例
     * @param name
     * @return
     */
    public static Car getCar(String name)
    {
        return cars.get(name);
    }
}
