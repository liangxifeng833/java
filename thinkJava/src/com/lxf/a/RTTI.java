package src.com.lxf.a;

/**
 * 向下转型demo
 */
class Useful{
    public void f(){}
    public void g(){}
}

class MoreUseful extends Useful {
    public void f(){}
    public void g(){}
    public void u(){
        System.out.println("I am MoreUsefue.u()");
    }
}

public class RTTI {
    public static void main(String[] args) {
        Useful[] x = {new Useful(),new MoreUseful()};
        x[0].f();
        x[1].g();
        //x[1].u();//编译时，u()方法找不到
        ((MoreUseful)x[1]).u(); //向下转型/运行时类型识别
        ((MoreUseful)x[0]).u(); //运行时会抛出ClassCastException异常
    }
}
