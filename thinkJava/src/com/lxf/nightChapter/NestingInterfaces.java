package src.com.lxf.nightChapter;

import javax.management.openmbean.CompositeType;

/**
 * 接口嵌套demo
 */
class A{
    interface B{void f();}
    public class BImp implements B {
        public void f(){}
    }
    interface C {void f();}
    public class CImp implements C {
        public void f(){}
    }
    public class CImp2 implements C{
        public void f(){}
    }
    private interface D{void f();}
    private class DImp implements D{
        public void f(){}
    }
    public class DImp2 implements D{
        public void f(){}
    }
    public D getD(){return new DImp2();}
    private D dRef;
    public void receiveD(D d){
        dRef = d;
        dRef.f();
    }
}

interface E{
    interface G{
        void f();
    }
    interface H{
        void f();
    }
    void g();
    //嵌套接口中定义接口，这里的接口不能是私有的
    //private interface I{}
}
public class NestingInterfaces {
    public class BImp implements A.B {
        public void f(){}
    }
    class CImp implements A.C {
        public void f(){}
    }
    //Ａ类中的私有接口D不可在A类外部实现
    //class DImp implements A.D
    class EImp implements E {
        public void g(){}
    }
    class EGimp2 implements E.G {
        public void f() { }
    }
    class Eimp2 implements E {
        public void g(){}
        class EG implements E.G {
            public void f(){}
        }
    }

    public static void main(String[] args) {
        A a = new A();
        //这里不能使用A.D接口类型
        //A.D ad = a.getD();

        //如果想使用A.D类型，那么只有以下这一种方式可以
        A a2 = new A();
        a2.receiveD(a.getD());
    }
}
