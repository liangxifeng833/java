package com.example.esdemo.controller;

import com.example.esdemo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @GetMapping("createIndex")
    public String createIndex() {
        Product product = new Product(500000,"烂苹果-1","型号1" ,160D,1,1,1,"规格12*12",15,512,1);
        Product product1 = elasticsearchRestTemplate.save(product);
        System.out.println(product1);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        //elasticsearchRestTemplate.putMapping(Blog.class);
        return product1.toString();
    }
}
