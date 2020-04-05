package lxf.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AsyncServlet
 */
@WebServlet("/AsyncServlet")
public class AsyncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Servlet执行开始时间："+new Date());
		AsyncContext context = request.startAsync();
		new Thread(new Exector(context)).start();
		System.out.println("Servlet执行结束时间："+new Date());
//		context.getRequest();
//		context.getResponse();
	}
	
	public class Exector implements Runnable{
		private AsyncContext context;
		public Exector(AsyncContext context){
			this.context = context;
		}
		public void run(){
			//执行相关的复杂业务
			try {
				Thread.sleep(1000*10);//睡眠时间为10秒
				System.out.println("业务执行完毕时间："+new Date());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
