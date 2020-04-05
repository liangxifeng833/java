package lxf.spring.aop.impl;
/**
 * 计算器接口
 * @author lxf
 */
public interface CalculatorInterface {
    //加法
    public int add(int i, int j);
    //减法
    public int sub(int i, int j);
    //乘法
    public int mul(int i, int j);
    //除法
    public int div(int i, int j);
}