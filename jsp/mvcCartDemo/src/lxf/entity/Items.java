package lxf.entity;
/*
 * 商品类
 */
public class Items {
	//商品id
	private int id;
	//商品名称
	private String  goods_name;
	//商品单价
	private double price;
	//商品图片路径
	private String img_path;
	
	//构造方法
	public Items(int id,String goods_name,double price,String img_path){
		setId(id);
		setGoods_name(goods_name);
		setPrice(price);
		setImg_path(img_path);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	@Override
	public String toString() {
		return "Items [id=" + id + ", goods_name=" + goods_name + ", price=" + price + ", img_path=" + img_path + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goods_name == null) ? 0 : goods_name.hashCode());
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Items other = (Items) obj;
		if (goods_name == null) {
			if (other.goods_name != null)
				return false;
		} else if (!goods_name.equals(other.goods_name))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
	
}
