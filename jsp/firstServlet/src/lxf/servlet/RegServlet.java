package lxf.servlet;
/*
 * servlet接收表单数据demo
 */

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lxf.entity.Users;

public class RegServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//实例化实体user类
		Users user=new Users();
		System.out.println("post提交成功");
		
		try{
			//声明表单传递过来的参数
			String uname,pass;
			Date birthday = null;
			String[] favorites;
			boolean flat; 
			
			//将request数据转码
			req.setCharacterEncoding("utf-8");
			uname = req.getParameter("uname") !=null ? req.getParameter("uname") :"" ;
			pass = req.getParameter("pass") !=null ? req.getParameter("pass")  : "" ;
			SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
			//将接收到生日的字符串类型日期转换为日期类型
			String birth = req.getParameter("birthday") != null ? req.getParameter("birthday") : "";
			birthday = sdf.parse(birth);
			//爱好
			favorites = req.getParameterValues("favorites") != null ? req.getParameterValues("favorites") : null;
			
			//将从表单接收过来的值赋值给user实体对象属性
			user.setUname(uname);
			user.setPass(pass);
			user.setBirthday(birthday);
			user.setFavorites(favorites);
			
			//将赋值后的实体对象注册到session中
			req.getSession().setAttribute("myUser", user);
			if(req.getParameterValues("isAccept") != null)
			{
				user.setFlat(true);
			}else
			{
				user.setFlat(false);
			}
			
			/*
			 * 重定向路径
			 */
//			resp.sendRedirect("/userInfo.jsp");
			resp.sendRedirect(req.getContextPath()+"/userInfo.jsp");
			
			/*
			 * 服务器内部转发下三种跳转路径方式效果相同
			 */
			System.out.println(req.getContextPath()); //输出/firstServlet
			/*----------------1. 绝对路径------------------------------*/
			//req.getRequestDispatcher("/userInfo.jsp").forward(req, resp);
			/*----------------2. 相对路径------------------------------*/
			req.getRequestDispatcher("../userInfo.jsp").forward(req, resp);
			/*----------------3. 获得上下文对象---------------------*/
			req.getRequestDispatcher(req.getContextPath()+"/userInfo.jsp").forward(req, resp);
		}catch(Exception ex){
			ex.printStackTrace();	
		}
	}

}
