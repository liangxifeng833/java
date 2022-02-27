package src.com.lxf.a;

import java.util.Arrays;
import java.util.List;

public class TestA {
    protected int a = 1;
     protected void printTestA()
    {
        System.out.println("TestA.printTestA");
    }

    void printTestA1()
    {
        System.out.println("TestA.printTestA1");
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3);
        System.out.println(list);
    }
}
