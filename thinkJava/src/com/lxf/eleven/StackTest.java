package src.com.lxf.eleven;

import java.util.LinkedList;

/**
 * 栈
 */
class Stack<T> {
    private LinkedList<T> storage = new LinkedList<T>();
    //向栈顶压入元素
    public void push(T v) { storage.addFirst(v); }
    //获取栈顶元素，注意不删除
    public T peek() { return storage.getFirst(); }
    //栈顶元素出栈
    public T pop() { return storage.removeFirst(); }
    //判断栈是否为空
    public boolean empty() { return storage.isEmpty(); }
    public String toString() { return storage.toString(); }
}
public class StackTest {
    public static void main(String[] args) {
        java.util.Stack<String> stack = new java.util.Stack<String>();
        String st = "My dog has fleas";
        for(String s : "My dog has fleas".split(" ")) {
            stack.push(s);
        }
        while ( !stack.empty() ) {
            System.out.print(stack.pop()+" ");
        }
    }
}
