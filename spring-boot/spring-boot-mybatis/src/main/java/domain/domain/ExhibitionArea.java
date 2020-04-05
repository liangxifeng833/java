package domain.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 展位和区域关系表
 * @author 宁亚超 2018-02-07
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExhibitionArea implements Serializable {

    /**
     * 主键
     */
    private long  id;

    /**
     * 展位号
     */
    private String exhibitionName;

    /**
     * 区域表主键
     */
    private long areaId;

    /**
     * '有效期开始'
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date validStart;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getValidStart()
    {
        return this.validStart;
    }

    /**
     * '有效期截止'
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date validCutoff;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getValidCutoff()
    {
        return this.validCutoff;
    }

    /**
     * 状态，1为生效   0为无效
     */
    private int state;


}
