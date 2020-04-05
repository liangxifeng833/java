package model;
/**
 * 数据库对应的实体类，goodess数据表
 */

import java.util.Date;

public class Goodess {
	private Integer id;
	private String user_name;
	private Integer sex;
	private Date birthday;
	private String email;
	private Date add_time;
	private Date update_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	@Override
	public String toString() {
		return "Goodess [id=" + id + ", user_name=" + user_name + ", sex="
				+ sex + ", birthday=" + birthday + ", email=" + email
				+ ", add_time=" + add_time + ", update_time=" + update_time
				+ "]";
	}
	
}
