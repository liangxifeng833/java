package com.example.demo.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (offer)实体类
 *
 * @author kancy
 * @since 2022-06-30 15:44:48
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("offer")
public class Offer extends Model<Offer> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * offerId
     */
    @TableId
	private Integer offerId;
    /**
     * offerProid
     */
    private Integer offerProid;
    /**
     * offerStoreid
     */
    private Integer offerStoreid;
    /**
     * offerLjmartid
     */
    private Integer offerLjmartid;
    /**
     * offerMerchantid
     */
    @TableId
	private Integer offerMerchantid;
    /**
     * offerLjmartproid
     */
    private String offerLjmartproid;
    /**
     * offerPrice
     */
    private BigDecimal offerPrice;
    /**
     * offerAddtime
     */
    private Date offerAddtime;
    /**
     * offerVerify
     */
    private Integer offerVerify;
    /**
     * offerLjyunstyleId
     */
    private Integer offerLjyunstyleId;
    /**
     * offerPricetagId
     */
    private Integer offerPricetagId;
    /**
     * offerRefuseRemark
     */
    private String offerRefuseRemark;
    /**
     * offerChangeTime
     */
    private Date offerChangeTime;
    /**
     * offerType
     */
    private Integer offerType;
    /**
     * offerIsFotn
     */
    private Integer offerIsFotn;
    /**
     * status
     */
    private Integer status;
    /**
     * oldId
     */
    private Integer oldId;

}