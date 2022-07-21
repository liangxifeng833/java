package com.example.demo.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (ljmis_fotn_style_set)实体类
 *
 * @author kancy
 * @since 2022-06-30 15:44:38
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("ljmis_fotn_style_set")
public class LjmisFotnStyleSet extends Model<LjmisFotnStyleSet> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * styleId
     */
    @TableId
	private Integer styleId;
    /**
     * styleName
     */
    private String styleName;
    /**
     * styleCapId
     */
    private Integer styleCapId;

}