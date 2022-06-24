package com.example.esdemo;

import com.example.esdemo.entity.Product;
import com.example.esdemo.entity.User;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

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
        Product product = new Product(500000,"烂苹果-1","型号1" ,160D,1,1,1,"规格12*12",15,512,1);
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
        Product product = new Product(500000,"烂苹果-1","型号1" ,160D,1,1,1,"规格12*12",15,512,1);
        list.add(product);
        Product product2 = new Product(500001,"烂苹果-2","型号2" ,170D,1,1,1,"规格12*12",15,512,1);
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
        User user = new User();
        user.setName("nihao");
        user.setAge(22);
        user.setSex("男");
        User user1 = elasticsearchRestTemplate.save(user);
        System.out.println(user1);
    }
}