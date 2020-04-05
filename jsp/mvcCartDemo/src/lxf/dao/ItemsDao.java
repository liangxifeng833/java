package lxf.dao;

import lxf.entity.Items;

/*
 * 商品dao，相当于Model
 */
public class ItemsDao {
	
	//通过商品id查询商品信息
	public Items getItemsById(int id){
		//先创建两个商品对象
		Items i1 = new Items(1,"耐克运动鞋",500,"1.jpg");
		Items i2 = new Items(2,"李宁运动鞋",200,"2.jpg");
		Items i3 = new Items(3,"阿迪运动鞋",600,"3.jpg"); 
		Items returnVal = null;
		if(id==1)
			returnVal = i1;
		if(id==2)
			returnVal = i2;
		if(id==3)
			returnVal = i3;
		return returnVal;
	}
}
