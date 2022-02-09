package order.openfeign;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestMemberAppService extends ApplicationTests {
    @Autowired
    private MemberServiceFeign memberServiceFeign;

    @Test
    public void orderToMember(){
        String string = memberServiceFeign.getUser(1);
        System.out.println(string);
    }


}
