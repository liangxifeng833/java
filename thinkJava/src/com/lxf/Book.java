package src.com.lxf;

/**
 * 测试finalize()方法
 * 在垃圾回收的时候会触发该方法
 * @author liangxifeng
 * @date 2020-11-23
 */
class Book {
    boolean checkeOut = false;
    Book(boolean checkOut) {
        this.checkeOut = checkOut;
    }

    public void checkIn() {
        checkeOut = false;
    }

    protected void finalize() throws Throwable {
        if (checkeOut) {
            System.out.println("Error: checked out!");
        }
        super.finalize();
    }
}

class TerminationCondition {
    public static void main(String[] args) {
        Book book = new Book(true);
        book.checkIn();
        new Book(true);
        //强制垃圾回收，也就是触发finalize()方法
        System.gc();
    }
}
