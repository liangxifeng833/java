package domain.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 展位签约表
 * @author @author 宁亚超 2018-02-07
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExhibitionSigned implements Serializable {

    /**
     * 主键
     */
    private int  id;

    /**
     * 展位号
     */
    private String exhibitionName;

    /**
     * 租赁合同号，租赁合同表以租赁合同为主键
     */
    private int leaseContract;

    /**
     * '物业资源编号'
     */
    private int estateId;

    /**
     * 关联商户主表
     */
    private int merchantNumber;

    /**
     * 状态，0未签约  1 预租  2已签约
     */
    private int state;

    /**
     * 有效期开始
     */
    private Date validStart;

    /**
     * 有效期结束
     */
    private Date validCutoff;

}
