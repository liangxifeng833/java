package src.com.lxf.sevenChapter;

/**
 * 练习继承相关的初始化全过程
 */
class Insect {
    private int i = 9;
    protected int j;
    Insect(){
        System.out.println("Insect.i = "+i+",Insect.j=" + j);
        j = 39;
    }

    private static int x1 = printInit("static Insect.x1 initialized");
    static int printInit(String s) {
        System.out.println(s);
        return 47;
    }

}

class Beetle extends Insect {
    private int k = printInit("Beetle.k initialized");
    public Beetle(){
        System.out.println("Beetle.k = " + k);
        System.out.println("Beetle.j = " + j);
    }
    private static int x2 = printInit("static Beetle.x2 initialized");

    /**
     * 基类：Insect 子类Beetle
     * 在Beetle上运行Java时，首先会访问Beetle.main()（一个静态方法）
     * 加载器开始启动找到Beetle类的编译代码（在Beetle.class文件中）
     * 在对其加载过程中，编译器发现它有一个基类（关键字extends得知），
     * 于是它继续对基类进行加载（无论是否打算产生基类对象），如果基类还有基类，那么以此类推。
     * 基类中的static域初始化。
     * 子类中的static域初始化，到此位为止需要的类都已经被加载完毕，接下来是对象的创建过程：
     * 对象中所有基本类型被设为默认值，引用类型设为null.
     * 调用基类的构造器
     * 子类构造器被执行
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Beetle constructor");
        Beetle b = new Beetle();
    }
}
