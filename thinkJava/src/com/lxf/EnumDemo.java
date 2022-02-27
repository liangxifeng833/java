package src.com.lxf;

/**
 * 枚举类型demo
 */
enum Spiciness{
    NOT,MILD,MEDIUM,HOT,FLAMING
}
public class EnumDemo {
    public static void main(String[] args) {
        Spiciness howHot = Spiciness.MEDIUM;
        System.out.println(howHot);
        //Spiciness.values() = 获取常量值构成的数组
        for (Spiciness value : Spiciness.values()) {
            System.out.println(value + ".枚举常量的声明顺序=" + value.ordinal());
        }

        EnumDemo plain = new EnumDemo(Spiciness.MILD);
        EnumDemo greenChild = new EnumDemo(Spiciness.MEDIUM);
        EnumDemo jalapeno = new EnumDemo(Spiciness.HOT);
        plain.describe();
        greenChild.describe();
        jalapeno.describe();
    }

    Spiciness degree;
    EnumDemo(Spiciness degree) {
        this.degree = degree;
    }

    /**
     * 和switch一起配合使用
     */
    public void describe() {
        System.out.print("This Burrito is");
        switch (degree) {
            case NOT:
                System.out.println("not spicy at all");
                break;
            case MILD:
            case MEDIUM:
                System.out.println("a little hot");
                break;
            case HOT:
            case FLAMING:
            default:
                System.out.println("maybe to hot");
        }
    }
}
