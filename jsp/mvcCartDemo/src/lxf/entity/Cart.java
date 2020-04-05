package lxf.entity;
/*
 * 购物车类
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Cart {
	//购买商品的集合<商品对象,购买商品的数量>
	private HashMap<Items,Integer> goods;
	//购买商品的总金额
	private double totalPrice;
	/*
	 * 构造方法
	 */
	public Cart(){
		this.goods = new HashMap<Items,Integer>();
		this.totalPrice = 0.00;
	}

	public HashMap<Items, Integer> getGoods() {
		return goods;
	}

	public void setGoods(HashMap<Items, Integer> goods) {
		this.goods = goods;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	//添加商品进购物车
	public boolean addGoodsInCart(Items item,int number){
		//判断如果新增进来的商品对象存在与map中，则修改数量
		if(goods.containsKey(item)){
			goods.put(item,goods.get(item)+number);
		}else{
			goods.put(item, number);
		}	
		//重新计算购物车的总金额
		calTotalPrice();
		return true;
	}
	//删除购物车商品
	public boolean delGoodsFromCart(Items item){
		goods.remove(item);
		//重新计算购物车的总金额
		calTotalPrice();
		return true;
	}
	//统计购物车的总金额
	public double  calTotalPrice(){
		//定义总金额变量
		double sum = 0.00;
		Set<Items> keys = goods.keySet();//获取键的集合
		Iterator<Items> it = keys.iterator();//获取迭代器对象
		while(it.hasNext()){
			Items i = it.next();
			sum += i.getPrice()*goods.get(i);
		}
		this.setTotalPrice(sum);
		return sum;
	}
	
	
	@Override
	public String toString() {
		return "Cart [goods=" + goods + ", totalPrice=" + totalPrice + "]";
	}

	public static void main(String[] args) {
		//先创建两个商品对象
		Items i1 = new Items(1,"耐克运动鞋",500,"1.jpg");
		Items i3 = new Items(1,"耐克运动鞋",200,"1.jpg");
		Items i2 = new Items(2,"阿迪运动鞋",600,"2.jpg");
		
		Cart c = new Cart();
		c.addGoodsInCart(i1, 2);
		c.addGoodsInCart(i2, 3);
		c.addGoodsInCart(i3, 3);
		
		//通过entrySet方法，获取购物车中商品集合
		Set <Entry<Items,Integer>> items = c.getGoods().entrySet();
		for(Entry<Items,Integer> item:items){
			System.out.println(item);
			System.out.println("成功添加商品"+item.getKey().getGoods_name());
			System.out.println("商品数量为"+item.getValue());
		}
		
		System.out.println("购物车总金额："+c.getTotalPrice());
	}
	
}
