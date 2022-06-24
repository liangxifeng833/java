package com.example.esdemo.repository;

import com.example.esdemo.ApplicationTests;
import com.example.esdemo.entity.Product;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.Date;

/**
 * 测试 es repository 方式操作es
 */
public class TestEsRepository extends ApplicationTests {
    @Autowired
    private ProductRepository productRepository;

    /**
     * 常见索引
     */
    @Test
    public void testAddIndex() {
        Product product = new Product(500000,"烂苹果-1","型号1" ,160D,1,1,1,"规格12*12",15,512,1);
        Product res = productRepository.save(product);
        System.out.println(res);
    }

    /**
     * 查询所有 按价格排序 asc
     */
    @Test
    public void testGetAll() {
        // 查询全部，并安装价格降序排序
        Iterable<Product> items = productRepository.findAll(Sort.by(Sort.Direction.DESC, "product_price"));
        items.forEach(item-> System.out.println(item));
    }

    @Test
    public void testGetById() {
        System.out.println(productRepository.findById(1318));
    }

    /**
     * 分页查询
     */
    @Test
    public void testGetPage() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本的分词查询

        // 初始化分页参数
        int page = 0;
        int size = 3;
        // 设置分页参数
        queryBuilder.withPageable(PageRequest.of(page, size));

        // 执行搜索，获取结果
        Page<Product> items = this.productRepository.search(queryBuilder.build());
        // 打印总条数
        System.out.println(items.getTotalElements());
        // 打印总页数
        System.out.println(items.getTotalPages());
        // 每页大小
        System.out.println(items.getSize());
        // 当前页
        System.out.println(items.getNumber());
        items.forEach(System.out::println);
    }


}
