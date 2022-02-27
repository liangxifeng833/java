package src.com.lxf.tenChapter;

/**
 * 匿名内部类demo
 */
interface A {
    void value();
}
interface B {
    String readLabel();
}
public class NiMingClass {
    class myA implements A {
        @Override
        public void value() {
            System.out.println("我是内部类myA实现类A接口");
        }
    }
    public A getA2(){
        return new myA();
    }
    public A getA1(){
        return new A(){
            @Override
            public void value() {
                System.out.println("我是匿名内部类A,在此自动实现A接口");
            }
        };
    }
    public B getB(String s,float price) {
        return new B() {
            private String label = s;
            private int cost;
            {
                cost = Math.round(price);
                if(cost > 100)
                {
                    System.out.println("cost = "+cost+",Over budget!");
                }
            }
            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        NiMingClass n = new NiMingClass();
        B label = n.getB("hello",101.395F);
        String s = label.readLabel();
        System.out.println(s);
    }
}
