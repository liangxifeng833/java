package src.com.lxf.tenChapter;

import sun.security.krb5.internal.crypto.Des;

/**
 * 内部类向上转型demo
 */
interface Destination{
    String readLabel();
}
interface Contents {
    int value();
}
class Parcel4 {
    public class PContents implements Contents {
        private int i = 11;
        public int value(){return i;}
    }
    protected class PDestination implements Destination {
        private String label;
        private PDestination (String whereTo) {
            label = whereTo;
        }
        public String readLabel() {return label;}
    }
    public Destination destination (String s) {
        return new PDestination(s);
    }
    public Contents contents() {
        return new PContents();
    }

}
public class TestParcel {
    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Contents c = p.contents();
        //因为内部类是私有的，所以不可以使用如下方式访问
        Parcel4.PContents a= new Parcel4().new PContents();
    }
}
