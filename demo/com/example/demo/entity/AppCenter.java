package com.example.demo.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (app_center)实体类
 *
 * @author kancy
 * @since 2022-06-30 15:43:47
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("app_center")
public class AppCenter extends Model<AppCenter> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * appId
     */
    @TableId
	private Integer appId;
    /**
     * catId
     */
    private Integer catId;
    /**
     * appName
     */
    private String appName;
    /**
     * appType
     */
    private Object appType;
    /**
     * appDesc
     */
    private String appDesc;
    /**
     * appIcon
     */
    private String appIcon;
    /**
     * appCtime
     */
    private Date appCtime;
    /**
     * appFile
     */
    private String appFile;
    /**
     * appIsvalid
     */
    private Integer appIsvalid;
    /**
     * appSort
     */
    private Integer appSort;

}