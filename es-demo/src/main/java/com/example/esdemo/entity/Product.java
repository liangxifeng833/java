package com.example.esdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@AllArgsConstructor
@Document(indexName = "ljyun_share_product", shards = 1, replicas = 0)
public class Product {
    @Id
    @Field(type = FieldType.Integer)
    private Integer product_id;

    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String product_name;

    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String product_model;

    @Field(type = FieldType.Double)
    private Double product_price;

    @Field(type = FieldType.Integer)
    private Integer product_prounit;

    @Field(type = FieldType.Integer)
    private Integer product_brandid;

    @Field(type = FieldType.Integer)
    private Integer product_status;

    @Field(type = FieldType.Text)
    private String product_specification;

    @Field(type = FieldType.Integer)
    private Integer old_id;

    @Field(type = FieldType.Integer)
    private Integer product_merchantid;

    //@Field(type = FieldType.Date)
    //private Date product_addtime;

    @Field(type = FieldType.Integer)
    private Integer product_typeid;

}
