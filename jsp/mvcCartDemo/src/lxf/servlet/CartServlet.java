package lxf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lxf.dao.ItemsDao;
import lxf.entity.Cart;
import lxf.entity.Items;

/**
 * @author lxf
 */
public class CartServlet extends HttpServlet {
	//购物车的动作　add添加　delete删除，show查看
	private String action;
	//商品业务逻辑类的对象
	private ItemsDao iDao = new ItemsDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		if(req.getParameter("action")!=null){
			this.action = req.getParameter("action");
			//新增购物车
			if(action.equals("add")){
				if(addToCard(req,resp))
				{
					//重构跳转到成功提示页面
					req.getRequestDispatcher("/success.jsp").forward(req, resp);
				}else
				{
					//失败跳转到失败页面
					req.getRequestDispatcher("/failure.jsp").forward(req, resp);
				}
			//显示购物车
			}else if(action.equals("show")){
				req.getRequestDispatcher("/cart.jsp").forward(req, resp);
			//删除购物车
			}else if(action.equals("delete")){
				if(delCard(req,resp)){
					out.println("<strong>删除购物车商品"+req.getParameter("id")+"成功</strong>");
				}else
				{
					out.println("<strong>删除购物车商品"+req.getParameter("id")+"失败</strong>");
				}
			}
		}
	}

	//添加商品到购物车
	private boolean addToCard(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		System.out.println("add-start");
		boolean returnVal  = false;
		try{
			//获取id
			int id = Integer.parseInt(req.getParameter("id"));
			System.out.println("param id = "+id);
			//获取购买数量
			int number = Integer.parseInt(req.getParameter("number"));
			System.out.println("param number = "+number);
			Items item = iDao.getItemsById(id);
			System.out.println("get from iDao item="+item);
			//如果购物车为空，则需要创建空购物车对象，并放进session中
			if(req.getSession().getAttribute("cart") == null){
				System.out.println("cart is null");
				Cart cart = new Cart();
				req.getSession().setAttribute("cart", cart);
				System.out.println("put cart in Session success");
			//否则直接从session中获取购物车信息
			}else{
				System.out.println("cart is  not null");
				Cart cart = (Cart)req.getSession().getAttribute("cart");
				System.out.println("modify cart success");
				if(cart.addGoodsInCart(item,number )){
					returnVal =  true;
				}
				System.out.println("success add cart ="+cart);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnVal;
	}
	//显示购物车商品
	private void showCard(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
	//删除购物车商品
	private boolean delCard(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		//接收删除购物车商品id
		int id = Integer.parseInt(req.getParameter("id"));
		Items  item = iDao.getItemsById(id);
		//获取购物车中
		Cart cart = (Cart)req.getSession().getAttribute("cart");
		if(cart.delGoodsFromCart(item))
		{
			return true;
		}else
		{
			return false;
		}	
	}
	
	

}
