package lxf.spring.jdbc.bean;
/**
 * 商品实体类
 * @author lxf
 *
 */
public class Goods {
    private int goods_id;
    private String goods_name;
    private double cost_price;
    private double selling_price;
    private String manufactuer;
    public int getGoods_id() {
        return goods_id;
    }
    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }
    public String getGoods_name() {
        return goods_name;
    }
    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }
    public double getCost_price() {
        return cost_price;
    }
    public void setCost_price(double cost_price) {
        this.cost_price = cost_price;
    }
    public double getSelling_price() {
        return selling_price;
    }
    public void setSelling_price(double selling_price) {
        this.selling_price = selling_price;
    }
    public String getManufactuer() {
        return manufactuer;
    }
    public void setManufactuer(String manufactuer) {
        this.manufactuer = manufactuer;
    }
    @Override
    public String toString() {
        return "Goods [goods_id=" + goods_id + ", goods_name=" + goods_name +  ", cost_price=" + cost_price + ", selling_price=" + selling_price + ", manufactuer=" + manufactuer
                + "]";
    }
}
