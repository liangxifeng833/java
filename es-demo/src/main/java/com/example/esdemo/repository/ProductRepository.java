package com.example.esdemo.repository;

import com.example.esdemo.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ES 操作类
 */
public interface  ProductRepository extends ElasticsearchRepository<Product,Integer> {
}
