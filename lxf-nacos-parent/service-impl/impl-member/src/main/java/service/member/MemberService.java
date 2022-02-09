package service.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MemberService implements IMemberService{
    @Value("${server.port}")
    private Integer port;
    public String getUser(@RequestParam Integer userId){
        log.info("会员userId="+userId);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "我是会员服务,端口="+port;
    }

    @Override
    public String postTest(Integer userId) {
        log.info("post 会员userId="+userId);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "我是会员服务post测试,端口="+port;
    }

    @GetMapping("/GetTest")
    public String getTest(){
        return "I am Test...";
    }
}
