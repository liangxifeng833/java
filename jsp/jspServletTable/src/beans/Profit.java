package beans;
/*
 * 数据模型 - 最终导出表格所需数据字段
 */
public class Profit {
	//商品名称
	private String goodsName;
	//商品id
	private int goodsId;
	//成本价格
	private float costPrice;
	//销售价
	private float sellingPrice;
	//卖出数量
	private int number;
	//交易笔数
	private int times;
	//盈利额
	private float profit;
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public Float getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(float f) {
		this.costPrice = f;
	}
	public float getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(float f) {
		this.sellingPrice = f;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public float getProfit() {
		return profit;
	}
	public void setProfit(float profit2) {
		this.profit = profit2;
	}
	
	
}
