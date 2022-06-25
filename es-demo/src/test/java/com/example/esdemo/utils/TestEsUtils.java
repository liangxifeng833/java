package com.example.esdemo.utils;

import com.example.esdemo.ApplicationTests;
import com.example.esdemo.Utils.EsUtils;
import com.example.esdemo.entity.EsSearchParams;
import com.example.esdemo.entity.EsSearchResult;
import com.example.esdemo.entity.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * es 工具类单元测试
 */
public class TestEsUtils extends ApplicationTests {
    @Autowired
    private EsUtils esUtils;

    //新增
    @Test
    public void testAdd()
    {
        Product product = new Product();
        for (int i = 0; i < 10 ; i++) {
            product.setProductId(i).setProductName("测试商品名称"+i).setProductPrice(1000D).setProductModel("商品型号1").setProductProunit(1)
                    .setOldId(1).setProductAddtime(LocalDateTime.now())
                    .setProductBrandid(1)
                    .setProductMerchantid(512)
                    .setProductSpecification("规格")
                    .setProductStatus(1)
                    .setProductTypeid(1);
            Product res = esUtils.add(product);
            System.out.println(res);
        }
    }

    /**
     * 通过主键查询一条数据
     */
    @Test
    public void getById() {
        Product  product = esUtils.getById(5,Product.class);
        System.out.println(product);
    }

    /**
     * 通过主键修改
     */
    @Test
    public void testUpdate() throws IllegalAccessException {
        Product product = new Product();
        product.setProductId(1);
        product.setProductName("我的商品");
        product.setProductModel("I love you china");
        product.setProductPrice(999D);
        esUtils.updateByBean(product);
    }

    /**
     * 组合查询
     */
    @Test
    public void testGetLists() {
        EsSearchParams esSearchParams = new EsSearchParams();
        esSearchParams.setSearch("商品").setProductStatus(1).setProductMerchantId(512);
        //esSearchParams.setSortField("product_id").setSortValue("desc");
        EsSearchResult<Product> list = esUtils.getList(Product.class,esSearchParams);
        System.out.println(list);
    }

    /**
     * 通过主键删除
     */
    @Test
    public void testDelById() throws IllegalAccessException {
        Product product = new Product();
        product.setProductId(5);
        esUtils.deleteById(product);
    }

    /**
     * 通过条件删除
     */
    @Test
    public void testDel() {
        esUtils.delete(Product.class,"product_merchantid",512);
    }
}
