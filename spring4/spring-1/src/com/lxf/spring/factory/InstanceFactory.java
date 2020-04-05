package com.lxf.spring.factory;
import java.util.HashMap;
import java.util.Map;
/**
 * 实例工厂方法：实例工厂的方法，即先需要创建工厂本身实例，
 *                             再调用工厂的实例方法来返回bean的实例．
 * @author lxf
 */
public class InstanceFactory {
    private Map<String,Car> cars = null;
    public InstanceFactory()
    {
        cars = new HashMap<String,Car>();
        cars.put("audi", new Car("audi"));
        cars.put("ford", new Car("ford"));
    }
    public Car getCar(String name)
    {
        return cars.get(name);
    }
}
