package src.com.lxf.tenChapter;

/**
 * 继承内部类demo
 */
class WithInner{
    class Inner{}
}
public class InheritInner extends WithInner.Inner {
    InheritInner(WithInner wi){
        wi.super();
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
}
