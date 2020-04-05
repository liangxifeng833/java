package lxf.spring.hibernate.entiries;
/**
 * 图书实体类
 * @author lxf
 */
public class Books {
    //图书表主键
    private int book_id;
    //图书名称
    private String book_name;
    //图书价格
    private double price;
    //图书库存
    private int stock;
    public int getBook_id() {
        return book_id;
    }
    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
    public String getBook_name() {
        return book_name;
    }
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
