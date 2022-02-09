package order.openfeign;

import order.openfeign.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "member-service",configuration = FeignConfiguration.class)
public interface MemberServiceFeign {
    @GetMapping("/getUser")
    String getUser(@RequestParam Integer userId);

    @PostMapping("/postTest")
    String postTest(@RequestParam Integer userId);
}
