package lxf.entity;
/*
 * 练习监听User对象绑定到HttpSession域中的事件状态
 */

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

//session要活化必须实现Serializable接口
public class User implements HttpSessionBindingListener, HttpSessionActivationListener, Serializable{
	private String uName;
	private String passWord;
	
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	//将本类实例化后getSession().setAttibute()时触发
	public void valueBound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("User对象被绑定session中,key="+arg0.getName());
	}
	@Override
	//将本类实例化后getSession().removeAttibute()时触发
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("User对象在session中被解除绑定,key="+arg0.getName());
	}

	@Override
	//session钝化触发（也就是数据从内存 -> 硬盘）,也就是session中已setAttribute值后，关闭tomcat会触发
	//输出：session钝化org.apache.catalina.session.StandardSessionFacade@58443af6
	//同时会在/usr/local/tomcat/work/Catalina/localhost/firstListener中生成SESSION.ser文件
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("session钝化"+arg0.getSource());	
	}
	
	@Override
	//session活化触发（也就是数据从硬盘 ->内存 ）,以上关闭tomcat后再启动tomcat会触发,
	//输出:session活化org.apache.catalina.session.StandardSessionFacade@689ac6da
	//同时/usr/local/tomcat/work/Catalina/localhost/firstListener/SESSION.ser文件销毁
	public void sessionDidActivate(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("session活化"+arg0.getSource());
		
	}
}
