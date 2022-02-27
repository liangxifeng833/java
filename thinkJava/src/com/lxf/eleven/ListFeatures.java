package src.com.lxf.eleven;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListFeatures {
    public static void main(String[] args) {
        List<String> pets = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            pets.add("item"+i);
        }
        Iterator<String> it = pets.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            it.remove();
        }

        System.out.println("++++++++++++++++++++++");
        ListIterator<String> it2 = pets.listIterator();
        while (it2.hasNext()) {
            System.out.println(it2.next()+","+it2.nextIndex()+","+it2.previousIndex());
        }

        while (it2.hasPrevious())
        {
            System.out.println(it2.previous());
        }

    }
}
