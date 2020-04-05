package com.lxf.annotationDao;
/*＊
 * User实体类
 */

@Table("user")
public class Filter {
	@Column("id")
	private int id;
	
	@Column("user_name")
	private String user_name;
	
	@Column("age")
	private int age;
	
	@Column("city")
	private String city;
	
	@Column("email")
	private String email;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	

}
