package lxf.entity;

import java.util.Date;

/*
 * 用户实体类
 */
public class Users {
	private String uname;             //用户名
	private String pass;				  //密码
	private String[] favorites;       //爱好
	private Date birthday;
	private boolean flat;   //是否接收协议
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String[] getFavorites() {
		return favorites;
	}
	public void setFavorites(String[] favorites) {
		this.favorites = favorites;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public boolean isFlat() {
		return flat;
	}
	public void setFlat(boolean flat) {
		this.flat = flat;
	}
}
