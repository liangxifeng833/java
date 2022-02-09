package order.infrastructure.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础字段Model
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel implements Serializable {

    /**
     * 创建人Id
     */
    private Integer createUserId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT) // 插入时自动填充,只对po起作用
    private LocalDateTime createTime;

    /**
     * 修改人Id
     */
    private Integer updateUserId;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)// 插入/更新时自动填充,只对po起作用
    private LocalDateTime updateTime;

    /**
     * 删除状态 0：未删除  1：已删除
     */
    private Integer deleted;

    /**
     * 版本号
     */
    private Integer version;

}
