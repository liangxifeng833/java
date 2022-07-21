package com.example.demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2022-07-01 14:37:47
 */
public class Product implements Serializable {
    private static final long serialVersionUID = -89414676688801733L;
    
    private Integer productId;
    
    private String productName;
    
    private Integer productTypeid;
    
    private String productModel;
    
    private String productSpecification;
    
    private Integer productBrandid;
    
    private Double productPrice;
    
    private String productProunit;
    
    private Integer productMerchantid;
    
    private Date productAddtime;
    
    private Integer productType;
    
    private Integer productStatus;
    
    private Integer oldId;
    /**
     * 蓝景分类主键
     */
    private Integer ljyunstyleId;
    /**
     * 商品唯一标识
     */
    private String productSku;
    /**
     * 是否推荐,0:不推荐，1:推荐
     */
    private Integer recommend;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductTypeid() {
        return productTypeid;
    }

    public void setProductTypeid(Integer productTypeid) {
        this.productTypeid = productTypeid;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(String productSpecification) {
        this.productSpecification = productSpecification;
    }

    public Integer getProductBrandid() {
        return productBrandid;
    }

    public void setProductBrandid(Integer productBrandid) {
        this.productBrandid = productBrandid;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductProunit() {
        return productProunit;
    }

    public void setProductProunit(String productProunit) {
        this.productProunit = productProunit;
    }

    public Integer getProductMerchantid() {
        return productMerchantid;
    }

    public void setProductMerchantid(Integer productMerchantid) {
        this.productMerchantid = productMerchantid;
    }

    public Date getProductAddtime() {
        return productAddtime;
    }

    public void setProductAddtime(Date productAddtime) {
        this.productAddtime = productAddtime;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    public Integer getLjyunstyleId() {
        return ljyunstyleId;
    }

    public void setLjyunstyleId(Integer ljyunstyleId) {
        this.ljyunstyleId = ljyunstyleId;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

}

