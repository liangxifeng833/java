package com.example.esdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * es 查询结果
 * @author liangxifeng
 * @date 2022-06-25
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class EsSearchResult<T> {
    /**
     * 总数据条数
     */
    private Integer total;
    /*
     * 每页显示的数据条数
     */
    private List<T> list;
}
