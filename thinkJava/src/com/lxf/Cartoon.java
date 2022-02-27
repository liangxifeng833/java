package src.com.lxf;

class Art {
    Art(int i){
        System.out.println("Art constructor");
    }
}

public class Cartoon extends Art{
    Cartoon(){
        super(1);
        System.out.println("Cartoon constructor");
    }
    public static void main(String[] args) {
        Cartoon x = new Cartoon();
    }
}
