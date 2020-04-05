package domain.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 区域历史表
 * @author 宁亚超 2018-02-07
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AreaHistory implements Serializable {

    /**
     * 主键
     */
    private long  id;

    /**
     * 所要修改的字段
     */
    private String field;

    /**
     * 修改之后的值
     */
    private String value;

    /**
     * 区域表主键
     */
    private long areaId;

    /**
     * '有效期开始'
     */
    private Timestamp validStart;
    public void setValidStart() {
        java.util.Date now = new java.util.Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        this.validStart = timestamp;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Timestamp getValidStart()
    {
        return this.validStart;
    }

    /**
     * '有效期截止'
     */
    private Timestamp validCutoff;
    public void setValidCutoff() {
        java.util.Date now = new java.util.Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        System.out.println("修改截止时间 = "+timestamp);
        this.validCutoff = timestamp;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Timestamp getValidCutoff()
    {
        return this.validCutoff;
    }

}
