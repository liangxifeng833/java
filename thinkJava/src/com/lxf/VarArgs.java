package src.com.lxf;

/**
 * 可变参数练习
 */
public class VarArgs {
    /**
     * 可变参数的第一种写法,使用Object类型的数组
     * @param args
     */
    static void printArray(Object[] args) {
        for (Object arg : args) {
            System.out.print(arg+" ");
        }
        System.out.println();
    }

    /**
     * 可变参数的第二种写法 Java5开始提供支持的
     * @param args
     */
    static void printArrNew(int a,Object... args) {
        System.out.print(args.getClass());
        System.out.println("args length:"+args.length);
        for (Object arg : args) {
            System.out.print(arg+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printArray(new Object[]{
                new Integer(47),new Float(3.14),new Double(1.11)
        });
        printArray(new Object[]{"one","two","three"});
        printArray(new Object[]{1,2,3});

        //可变参数传递
        printArrNew(1,2,3);
        //向可变参数传递数组
        printArrNew(2,new Integer[]{1,2,3});
        //不可参数可不传递任何值
        printArrNew(3);
    }
}

