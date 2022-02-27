package src.com.lxf.eleven;

import java.util.LinkedList;

public class LinkedListFeatures {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++)
            linkedList.add("item"+i);
        //取出列表中第一个元素,有三种方法，不同的是当列表无元素时，peek()返回null,而getFirst和element会抛异常
        System.out.println("peek():"+linkedList.peek());        //item0
        System.out.println("getFirst():"+linkedList.getFirst()); //item0
        System.out.println("element():"+linkedList.element());  //item0

        //删除list队头第一个元素，并将其返回,以下三种方式效果相同
        //不同的是当列表为空时，poll()返回null, remove()和removeFirst()抛空指针异常
        System.out.println("remove():"+linkedList.remove()); //item0
        System.out.println("removeFirst():"+linkedList.removeFirst()); //item1
        System.out.println("poll():"+linkedList.poll()); //item2

        //想list头新增元素
        linkedList.addFirst("item6");
        System.out.println("After addFirst():"+linkedList); //[item6, item3, item4]

        //向list尾新增元素 offer()
        linkedList.offer("item7");
        System.out.println("After offer():"+linkedList); //[item6, item3, item4, item7]
        //向list尾新增元素 add()
        linkedList.add("item8");
        System.out.println("After add():"+linkedList); //[item6, item3, item4, item7,item8]
        //向list尾新增元素 addLast()
        linkedList.addLast("item9");
        System.out.println("After addLast():"+linkedList); //[item6, item3, item4, item7,item8,item9]

        //删除list尾部最后一个元素，并将其返回
        System.out.println("removeLast():"+linkedList.removeLast()); //item9
    }
}
