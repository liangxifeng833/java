package com.example.esdemo.controller;

import com.example.esdemo.entity.Product;
import com.example.esdemo.entity.User;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    @GetMapping("getUserOne/{id}")
    public User getUserOne(@PathVariable Integer id) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("id", id))
                .build();
        SearchHits<User> res =  elasticsearchRestTemplate.search(
                nativeSearchQuery,User.class);
        System.out.println(res);
        res.getSearchHits().forEach(System.out::println);
        return res.getSearchHit(0).getContent();
    }
    @GetMapping("getProductOne/{id}")
    public Product getProductOne(@PathVariable Integer id) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("product_id", id))
                .build();
        SearchHits<Product> res =  elasticsearchRestTemplate.search(
                nativeSearchQuery,Product.class);
        System.out.println(res);
        res.getSearchHits().forEach(System.out::println);
        return res.getSearchHit(0).getContent();
    }
}
