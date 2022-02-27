package src.com.lxf.tenChapter;

/**
 * 内部类默认不能被覆盖
 */
class Egg {
    private Yolk y;
    protected class Yolk {
        public Yolk(){ System.out.println("Egg.Yolk()"); }
    }
    public Egg(){
        System.out.println("New Egg();");
        y = new Yolk();
    }
}
public class BigEgg extends Egg {
    public class Yolk {
        public Yolk(){ System.out.println("BigEgg.Yolk()"); }
    }
    public static void main(String[] args) {
        new BigEgg();
    }
}
