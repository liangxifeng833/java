package src.com.lxf.tenChapter;

/**
 * 在方法内部定义的类称为局部内部类
 */
interface Dest {
    String getLabel();
}
public class FunctionInClass {
    public Dest getDest(String s){
        //在方法中定义类PDest
        class PDest implements Dest {
            private String label;
            PDest(String whereTo) {label = whereTo;}
            public String getLabel() { return label; }
        }
        return new PDest(s);
    }
    public static void main(String[] args) {
        FunctionInClass f = new FunctionInClass();
        Dest d = f.getDest("Beijing");
        String label = d.getLabel();
        System.out.println(label); //输出Beijing
    }
}
