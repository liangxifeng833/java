package src.com.lxf;
/**
 * 静态数据的初始化
 * @author liangxifeng
 * @date 2020-11-23
 */
public class Bowl {
    Bowl(int marker) {
        System.out.println("Bowl("+ marker + ")");
    }
    void f1(int marker) {
        System.out.println("f1(" + marker + ")");
    }
}

class Table {
    static Bowl bowl1 = new Bowl(1);
    Table() {
        System.out.println("Table()");
    }
    void f2(int marker) {
        System.out.println("f2("+ marker+ ")");
    }
    static Bowl bowl2 = new Bowl(2);
}

class Cupboard {
    Bowl bowl3 = new Bowl(3);
    public static Bowl bowl4 = new Bowl(4);
    //.....
    Cupboard(){
        System.out.println("Cupboard");
        bowl4.f1(2);
    }
    void f3(int marker){
        System.out.println("f3("+marker+")");
    }
    static Bowl bowl5 = new Bowl(5);
}

class staticInit{
    public static void main(String[] args) {
        System.out.println("Creating new Cupboard() in main");
        new Cupboard();
        //System.out.println("Creating new Cupboard() in main");
        //new Cupboard();
        //cupboard.f3(1);
        /*
         */
        //table.f2(1);
    }
    //static Table table = new Table();
    //static Cupboard cupboard = new Cupboard();
}
