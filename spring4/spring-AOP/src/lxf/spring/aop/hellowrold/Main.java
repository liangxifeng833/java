package lxf.spring.aop.hellowrold;

public class Main {
    public static void main(String[] args) {
        CalculatorImpl cal = new CalculatorImpl();
        CalculatorInterface calProxy = new CalculatorLogProxy(cal).getLogProxy();
        int i=4, j=2;
        System.out.println("main方法输出：" + calProxy.add(i, j));
        System.out.println("main方法输出：" + calProxy.sub(i, j));
    }
}
