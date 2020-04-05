package beans;
/*
 * 数据模型 - 买家用户信息表
 */

public class User {
	//用户主键
	private int userId;
	//用户名称
	private String uname;
	//用户年龄
	private int age;
	//所在地区
	private String area;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
}
