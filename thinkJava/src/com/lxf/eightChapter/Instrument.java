package src.com.lxf.eightChapter;

enum Note{
    MIDDLE_C,C_SHARP,B_FLAT;
}
/**
 * 多态的练习
 */
public class Instrument {
    public void play(Note n) {
        System.out.println("Instrument.play()");
    }
}

class Wind extends Instrument {
    @Override
    public void play(Note n) {
        System.out.println("Wind.play() "+n);
    }
}

class Music {
    public static void tune(Instrument i)
    {
        i.play(Note.MIDDLE_C);
    }

    public static void main(String[] args) {
        Wind flute = new Wind();
        tune(flute);
    }
}
