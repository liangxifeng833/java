package entity;
/**
 * 地址类( 作为组件属性映射到student类中的address属性，将本类三个属性放入数据库 )
 * @author lxf
 *
 */

public class Address {
	private String postcode; //邮编
	private String phone;      //电话
	private String address;    //地址
	
	public Address()
	{
		
	}

	public Address(String postcode, String phone, String address) {
		super();
		this.postcode = postcode;
		this.phone = phone;
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	
}
