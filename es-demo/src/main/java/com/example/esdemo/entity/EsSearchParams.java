package com.example.esdemo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Es 复合查询入参实体
 * @author lianxifeng
 * @date 2022-06-25
 */
@Data
@Accessors(chain = true)
public class EsSearchParams {
    /**
     * 查询内容
     */
    private String search;
    /**
     * 分页offset, 默认0
     */
    private Integer page = 0;
    /**
     * 每页查询多少条数据 默认10
     */
    private Integer size = 10;
    /**
     * 商户id
     */
    private Integer productMerchantId;

    /**
     * 物理删除状态
     */
    private Integer productStatus;

    /**
     * 排序字段, 如果为null 则按评分倒叙
     */
    private String sortField;

    /**
     * 正序还是倒叙 asc 正序 desc 倒叙,如果为null 则按评分倒叙
     */
    private String sortValue;
}
