package com.soufang.esproject.service.search;

import com.soufang.esproject.ApplicationTests;
import com.soufang.esproject.service.ServiceMultiResult;
import com.soufang.esproject.web.form.RentSearch;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description: es-project
 * Create by liangxifeng on 19-5-24
 */
public class SearchServiceTest extends ApplicationTests {
    @Autowired
    private ISearchService searchService;

    /**
     * 测试es新增索引
     */
    @Test
    public void testIndex() {
         searchService.index(15L);
        //Assert.assertTrue(success);
    }
    /**
     * 测试es删除索引
     */
    @Test
    public void testRemove() {
        Long targetHouseId = 15L;
        searchService.remove(targetHouseId);
    }

    /**
     * 测试从es中做搜索获取房屋主键
     */
    @Test
    public void testQuery() {
        RentSearch rentSearch = new RentSearch();
        //rentSearch.setCityEnName("bj");
        rentSearch.setRegionEnName("hdq");
        rentSearch.setStart(0);
        rentSearch.setSize(10);
        ServiceMultiResult<Long> serviceResult = searchService.query(rentSearch);
        System.out.println(serviceResult.toString());
        Assert.assertEquals(8,serviceResult.getTotal());
    }
}
