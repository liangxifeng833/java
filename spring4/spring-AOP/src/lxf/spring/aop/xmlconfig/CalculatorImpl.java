package lxf.spring.aop.xmlconfig;
import org.springframework.stereotype.Component;
/**
 * 计算器接口实现类
 * @author lxf
 */
public class CalculatorImpl implements CalculatorInterface {
    @Override
    public int add(int i, int j) {
        return i+j;
    }
    @Override
    public int sub(int i, int j) {
        return i-j;
    }
    @Override
    public int mul(int i, int j) {
        return i*j;
    }
    @Override
    public int div(int i, int j) {
        // TODO Auto-generated method stub
        return i/j;
    }
}