package lxf.servlet;
import java.io.IOException;
import java.util.Date;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AsyncServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Servlet执行开始时间:"+new Date());
		//异步开启一个线程
		AsyncContext context = req.startAsync();
		new Thread(new Excutor(context)).start();
		System.out.println("Servlet执行结束时间:"+new Date());
	}
	
	// 开启一个线程
	public class Excutor implements Runnable{
		private AsyncContext context;
		public Excutor( AsyncContext context){
			this.context = context;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//执行相关的复杂业务
			try {
				//停留十秒
				Thread.sleep(1000*10);
				System.out.println("业务执行完成时间:"+new Date());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
