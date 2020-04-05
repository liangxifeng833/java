package lxf.springmvc.views;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
/**
 * 自定义视图
 * @author lxf
 */
@Component
public class HelloView  implements View{
    /**
     * 视图输出类型
     */
    @Override
    public String getContentType() {
        // TODO Auto-generated method stub
        return "text/html"  ;
    }
    /**
     * 渲染视图
     */
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        response.getWriter().print("hello view , time " + new Date());   
    }
}
