package beans;
/*
 * 数据模型 - 交易信息表
 */

public class Trading {
	//交易id
	private int tradingId;
	//交易商品id
	private int goodsId;
	//交易买家id
	private int userId;
	//交易数量
	private int number;
	public int getTradingId() {
		return tradingId;
	}
	public void setTradingId(int tradingId) {
		this.tradingId = tradingId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}
