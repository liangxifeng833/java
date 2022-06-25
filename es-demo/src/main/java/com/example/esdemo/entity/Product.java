package com.example.esdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(indexName = "ljyun_share_product", shards = 1, replicas = 0)
@Accessors(chain = true)
public class Product {
    @Id
    @Field(name="product_id",type = FieldType.Integer)
    private Integer productId;

    /**
     * 商品名称
     */
    // product_name 是es中的字段，productName映射为java的字段
    @Field(name = "product_name",type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String productName;

    /**
     * 商品型号
     */
    @Field(name="product_model",type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String productModel;

    /**
     * 商品原始价
     */
    @Field(name="product_price",type = FieldType.Double)
    private Double productPrice;

    /**
     * 商品单位主键
     */
    @Field(name="product_prounit",type = FieldType.Integer)
    private Integer productProunit;

    /**
     * 商品品牌主键
     */
    @Field(name="product_brandid",type = FieldType.Integer)
    private Integer productBrandid;

    /**
     * 逻辑删除状态, 1正常 0删除
     */
    @Field(name="product_status",type = FieldType.Integer)
    private Integer productStatus;

    /**
     * 商品规格
     */
    @Field(name="product_specification", type = FieldType.Text)
    private String productSpecification;

    @Field(name="old_id", type = FieldType.Integer)
    private Integer oldId;

    /**
     * 商户id
     */
    @Field(name="product_merchantid", type = FieldType.Integer)
    private Integer productMerchantid;

    /**
     * 商品新增时间
     */
    //JsonFormat (作用于读) 代表SpringBoot 返给给前端的时候才会具体起作用，也就是通过定义controllerc的方式，单元测试中查询出来的时间类型依然会多一个T
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd HH:mm:ss",timezone = "GMT+8")
    //Field （作用于写）注解是es创建索引使用的
    @Field(name = "product_addtime",type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd HH:mm:ss")
    private LocalDateTime productAddtime;

    /**
     * 商品分类id
     */
    @Field(name="product_typeid",type = FieldType.Integer)
    private Integer productTypeid;
}
