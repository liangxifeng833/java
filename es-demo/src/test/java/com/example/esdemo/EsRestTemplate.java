package com.example.esdemo;

import com.example.esdemo.entity.Product;
import com.example.esdemo.entity.User;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.apache.commons.io.IOUtils;

import javax.activation.DataHandler;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.*;

/**
 * elasticsearch RestTemplate 操作 es 7.6.2
 */
public class EsRestTemplate extends ApplicationTests{

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 创建索引测试
     */
    @Test
    public void testCreateIndex() {
        //创建第一种方式
        boolean res = elasticsearchRestTemplate.indexOps(Product.class).create();
        System.out.println(res);
        //创建第二种方式
        //boolean isCreate = elasticsearchRestTemplate.indexOps(IndexCoordinates.of("member_index")).create();
        //System.out.println(isCreate);
    }

    /**
     * 判断索引是否存在
     */
    @Test
    public void testExist()  {
        boolean exists = elasticsearchRestTemplate.indexOps(IndexCoordinates.of("member_index")).exists();
        System.out.println(exists);
    }

    /**
     * 查询索引 mapping
     */
    @Test
    public void testGetMapping() {
        Map mappings = elasticsearchRestTemplate.indexOps(IndexCoordinates.of("member_index")).getMapping();
        System.out.println(mappings);
    }

    /**
     * 删除索引
     */
    @Test
    public void testDelete() {
        boolean res = elasticsearchRestTemplate.indexOps(IndexCoordinates.of("product")).delete();
        System.out.println(res);
    }

    /**
     * 新增文档
     */
    @Test
    public void testAddDoc(){
        //保存第一种方式
        Product product = new Product();
        product.setProductId(1).setProductName("测试商品名称1").setProductPrice(1000D).setProductModel("商品型号1").setProductProunit(1)
                .setOldId(1).setProductAddtime(LocalDateTime.now())
                .setProductBrandid(1)
                .setProductMerchantid(512)
                .setProductSpecification("规格")
                .setProductStatus(1)
                .setProductTypeid(1);
        System.out.println(product);
        Product product1 = elasticsearchRestTemplate.save(product);
        System.out.println(product1);
        /*保存 第二种方式
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(product.getProductId().toString())
                .withObject(product)
                .build();
        elasticsearchRestTemplate.index(indexQuery, IndexCoordinates.of("product"));*/
    }
    /**
     * 批量新增
     */
    @Test
    public void testBatchAdd() {
        List list = new ArrayList();
        Product product = new Product();
        product.setProductId(1).setProductName("测试商品名称,爬高").setProductPrice(1000D).setProductModel("商品型号1").setProductProunit(1)
                .setOldId(1).setProductAddtime(LocalDateTime.now())
                .setProductBrandid(1)
                .setProductMerchantid(512)
                .setProductSpecification("规格")
                .setProductStatus(1)
                .setProductTypeid(1);
        list.add(product);
        Product product2 = new Product();
        product.setProductId(2).setProductName("测试商品名称2").setProductPrice(2000D).setProductModel("商品型号,爬高").setProductProunit(1)
                .setOldId(1).setProductAddtime(LocalDateTime.now())
                .setProductBrandid(1)
                .setProductMerchantid(512)
                .setProductSpecification("规格")
                .setProductStatus(1)
                .setProductTypeid(1);
        list.add(product2);
        Product product3 = new Product();
        product.setProductId(3).setProductName("上山爬高，真淘气").setProductPrice(3000D).setProductModel("商品型号,爬高").setProductProunit(1)
                .setOldId(1).setProductAddtime(LocalDateTime.now())
                .setProductBrandid(1)
                .setProductMerchantid(512)
                .setProductSpecification("规格")
                .setProductStatus(1)
                .setProductTypeid(1);
        list.add(product2);
        elasticsearchRestTemplate.save(list);
    }
    /**
     * 组合查询文档
     */
    @Test
    public void testGetDoc() {
        //自定义查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        nativeSearchQueryBuilder.withPageable(PageRequest.of(0,20));
        //排序
        FieldSortBuilder order = new FieldSortBuilder("product_price").order(SortOrder.ASC);
        nativeSearchQueryBuilder.withSort(order);
        //组合多条件 类似mysql
        // WHERE product_merchantid = 512 AND product_status = 1
        // AND (product_name like or product_model like ) order by product_price desc limit 0,20
        nativeSearchQueryBuilder.withQuery(QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery("product_merchantid",3))
                .filter(QueryBuilders.termQuery("product_status",0))
                .should(QueryBuilders.multiMatchQuery( "爬高","product_name","product_model")
                ).minimumShouldMatch(1)
        );
        //条件查询
        SearchHits<Product> res = elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(),Product.class);
        System.out.println(res);
        res.getSearchHits().forEach(System.out::println);
    }


    @Test
    public void testAddUser() {
        //elasticsearchRestTemplate.indexOps(User.class).create();
        User user = new User();
        user.setId(2);
        user.setName("中国的中国");
        user.setCreateTime(LocalDateTime.now());
        User user1 = elasticsearchRestTemplate.save(user);
        System.out.println(user1);
    }

    @Test
    public void testgetById() {
        Integer id = 1;
        Product res = elasticsearchRestTemplate.queryForObject(GetQuery.getById(String.valueOf(id)), Product.class);
        System.out.println(res);
    }

    @Test
    public void testUpdateById() {
        Document document = Document.create();
        // 将id为1023539082200的name列的值更为update by wfd
        document.put("product_name", "update by wfd");
        UpdateResponse response = elasticsearchRestTemplate.update(
                UpdateQuery.builder("1")
                        .withDocument(document).build(), IndexCoordinates.of("ljyun_share_product")
        );
        System.out.println(response);
    }
}