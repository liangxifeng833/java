package beans;
/*
 * 数据模型 - 商品实体类
 */

public class Goods {
	//商品id
	private int goodsId;
	//商品名称
	private String goodsName;
	//成本价格
	private int costPrice;
	//销售价
	private int sellingPrice;
	//制造商
	private String manufactuer;
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}
	public int getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getManufactuer() {
		return manufactuer;
	}
	public void setManufactuer(String manufactuer) {
		this.manufactuer = manufactuer;
	}
}
