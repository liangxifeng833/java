package src.com.lxf.eleven;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SetOperations {
    public static void main(String[] args) {
        Set<String> set1 = new HashSet<String>();
        Collections.addAll(set1,"A,B,C,D,F,G".split(","));
        set1.add("H");
        System.out.println(set1.contains("H"));
        Set<String> set2 = new HashSet<String>();
        Collections.addAll(set2,"A,B".split(","));
        set1.remove("H");
        System.out.println(set1.containsAll(set2));
    }
}
