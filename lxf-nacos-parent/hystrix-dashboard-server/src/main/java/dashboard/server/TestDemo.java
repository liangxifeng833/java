package dashboard.server;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import order.openfeign.MemberServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestDemo {
    //@Autowired
    //private MemberServiceFeign memberServiceFeign;

    @HystrixCommand
    public String testGet() {
        int num = (int) (Math.random()*10);
        if(num < 5){
            throw new RuntimeException("测试熔断");
        }
        //String res = memberServiceFeign.getUser(12);
        //return "调用会员服务接口测试,返回结果="+res;
        return "123";
    }
}
