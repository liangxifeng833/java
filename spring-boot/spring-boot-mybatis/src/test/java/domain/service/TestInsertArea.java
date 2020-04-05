package domain.service;

import domain.domain.Area;
import domain.domain.DomainResponse;
import domain.service.interfaces.area.InsertAreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试新增区域
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestInsertArea {
    @Autowired
    private InsertAreaService insertService;

    @Test
    public void testAddArea()
    {
        Area area = new Area();
        area.setAreaName("建材区域-3");
        DomainResponse res = insertService.insert(area);
        System.out.println("测试结果 = "+res);
    }
}
