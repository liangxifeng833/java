package order;

import order.openfeign.MemberServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderService {
    @Autowired
    private MemberServiceFeign memberServiceFeign;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private Integer port;
    /**
     * 基于feign客户端形式去实现调用
     * @return
     */
    @RequestMapping("/orderToMember")
    public String orderToMember() {
        //String res = memberServiceFeign.getUser(1);
        //通过服务名去注册中心获取接口地址列表
        //List<ServiceInstance> res = discoveryClient.getInstances("member-service");
        //ServiceInstance serviceInstance = res.get(0);
        //System.out.println(serviceInstance.getHost()+"-"+serviceInstance.getUri()+"-"+serviceInstance.getPort());
        //通过feign的方式远程访问member服务
        String res = memberServiceFeign.getUser(12);
        return "我是订单服务调用会员服务接口,返回结果="+res;
    }
}
