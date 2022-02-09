package order.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import order.infrastructure.model.BaseModel;
import order.infrastructure.util.TransitionUtil;
import org.apache.tomcat.util.modeler.BaseModelMBean;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 商户合同签订表PO
 * @author liangxifeng
 * @date 2021-12-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("merchant_contract_signed")
@Accessors(chain = true)
public class MerchantContractSignedPO {
    /**
     * 主键
     */
    @TableId
    private Integer	contractCid;

    /**
     * 商户 k 代码
     */
    private String	conMerchantId;

    /**
     * 租赁类型（无用）
     */
    private Integer	conType;

    /**
     * 租赁合同号
     */
    @TableField("con_ID")
    private String	conID;

    /**
     * 操作人员主键
     */
    private String	conSysId;

    /**
     * 操作时间
     */
    private LocalDate	conDate;

    /**
     * 租金
     */
    private BigDecimal conValue;

    /**
     * 备注
     */
    private String	conNotes;

    /**
     * 商户名称
     */

    @TableField("A_name")
    private String	aName;

    /**
     * 负责人
     */
    @TableField("A_dute")
    private String	aDute;

    /**
     * 委托人
     */
    @TableField("A_agent")
    private String	aAgent;

    /**
     * 负责人住址
     */
    @TableField("A_add")
    private String	aAdd;

    /**
     * 负责人邮箱
     */
    @TableField("A_post")
    private String	aPost;

    /**
     * 负责人身份证号
     */
    @TableField("A_dute_ID")
    private String	aDuteID;

    /**
     * 商户电话
     */
    @TableField("A_dute_tel")
    private String	aDuteTel;

    /**
     * 委托人身份证号
     */
    @TableField("A_agent_ID")
    private String	aAgentID;

    /**
     * 委托人电话
     */
    @TableField("A_agent_tel")
    private String	aAgentTel;

    /**
     * 关联物业信息表主键
     */
    private Integer	conResourceId;

    /**
     * 展位号
     */
    private String	conResource;

    /**
     * 合同开始时间
     */
    private LocalDate	stDate;

    /**
     * 合同结束时间
     */
    private LocalDate endDate;

    /**
     * 合同状态,1:在租 2:预租 3:过期 4:作废
     */
    private Integer	contractState;

    /**
     * 合同修改时间
     */
    private LocalDate editDate;

    /**
     * 修改人员
     */
    private String	editSysMan;

    /**
     *
     */
    private String	hid;

    //public void setaName(String aName) {
    //    this.aName = TransitionUtil.tst(aName);
    //}
}
