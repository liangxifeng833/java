package service.member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IMemberService {
    @GetMapping("/getUser")
    String getUser(Integer userId);

    @PostMapping("/postTest")
    String postTest(@RequestParam Integer userId);
}
