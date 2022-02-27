package src.com.lxf;

/**
 * 本类有多个构造器
 * 使用this关键字在构造器中调用构造器
 * ＠author: liangxifneg
 * @date 2020-11-23
 */
public class Flower {
    int petalCount = 0;
    String s = "inital value";
    Flower(int petals) {
        petalCount = petals;
        System.out.println("Constructors int arg only petalCount="+petalCount);
    }

    Flower(String ss) {
        s = ss;
        System.out.println("Constructors String arg only s="+s);
    }

    //在构造器中调用其他构造器
    //只能使用this关键字调用一个其他构造器
    Flower(String s, int petals) {
        this(petals);
        //this(s); //这里会包错，因为调用了多个构造器
        //在成员变量名和参数变量名相同的情况下，使用this.s代表成员变量名
        this.s = s;
        System.out.println("Constructors String and int args s="+s+",petalCount="+petalCount);
    }

    Flower() {
       this("hi",47);
       System.out.println("default constructor (no args)");
    }

    void printPetalCount(){
        System.out.println("petalCount = "+petalCount + ",s = "+s);
    }

    public static void main(String[] args) {
        Flower x = new Flower();
        x.printPetalCount();
    }
}
