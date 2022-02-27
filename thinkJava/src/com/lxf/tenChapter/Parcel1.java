package src.com.lxf.tenChapter;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import sun.security.krb5.internal.crypto.Des;

import javax.activation.DataContentHandler;
import javax.management.Descriptor;
import java.lang.invoke.ConstantCallSite;
import java.util.stream.Stream;

/**
 * 内部类demo
 */
public class Parcel1 {
    class Contents {
        private int i = 11;
        public int value() {return i;}
    }
    class Destination {
        private String label;
        Destination(String whereTo) {
            label = whereTo;
        }
        String readLabel(){return label;}
    }
    public Destination to (String s) {
        return new Destination(s);
    }
    public Contents contents(){
        return new Contents();
    }

    public void ship(String dest) {
        Contents c = new Contents();
        Destination d = new Destination(dest);
        System.out.println(d.readLabel());
    }

    public static void main(String[] args) {
        Parcel1 p = new Parcel1();
        p.ship("Tasmania");

        Parcel1 p2 = new Parcel1();
        Parcel1.Contents c = p2.contents();
        Parcel1.Destination d = p2.to("hello");
    }
}
