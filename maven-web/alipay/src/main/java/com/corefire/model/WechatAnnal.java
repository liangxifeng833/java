package com.corefire.model;
/**
 * 微信收款表实例类
 * @author liangxifeng
 * @date 2017-04-15
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WechatAnnal implements Serializable{
    private int id;                                       //主键
    private String appid;                           //中信支付平台分配的appid
    private String mch_appid;                 //中信支付平台分配的商户号
    private String wx_appid;                    //微信appid
    private String openid;                         //微信用户在商户appid下的唯一标识
    private int consume_id;                     //关联product_conume主键
    private String contract_number;       //销售合同号
    private String out_trade_no;              //支付号(我们生成)--唯一索引
    private String transaction_id;             //微信订单号--唯一索引
    private String pass_trade_no;            //通道统一订单号(微信支付通知中的商户单号)--唯一索引
    private BigDecimal value;                  //支付金额
    private BigDecimal charge;                //手续费
    private short pay_result;                   //支付结果 1成功,0失败,默认1
    private String out_refund_no;            //退款单号(我们生成)--唯一索引
    private String refund_id;                     //微信退款单号--唯一索引
    private String pass_refund_no;          //通道的统一退款单号(用户客户手机显示的)--唯一索引
    private short is_subscribe;                //是否关注公众号 1关注,0未关注,默认0
    private String bank_type;                   // 付款银行
    private String refund_channel;          //退款渠道 ORIGINAL:原路退款 BALANCE:退回到余额
    private Date add_time;                       //记录生成时间
    private Date modify_time;                 //记录最新修改时间
    
    
    public WechatAnnal() {
    }
    
    public int getId() {
        return id;
    }
    public short getPay_result() {
        return pay_result;
    }

    public void setPay_result(short pay_result) {
        this.pay_result = pay_result;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getPass_refund_no() {
        return pass_refund_no;
    }

    public void setPass_refund_no(String pass_refund_no) {
        this.pass_refund_no = pass_refund_no;
    }

    public short getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(short is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getRefund_channel() {
        return refund_channel;
    }

    public void setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getMch_appid() {
        return mch_appid;
    }
    public void setMch_appid(String mch_appid) {
        this.mch_appid = mch_appid;
    }
    public String getWx_appid() {
        return wx_appid;
    }
    public void setWx_appid(String wx_appid) {
        this.wx_appid = wx_appid;
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public int getConsume_id() {
        return consume_id;
    }
    public void setConsume_id(int consume_id) {
        this.consume_id = consume_id;
    }
    public String getContract_number() {
        return contract_number;
    }
    public void setContract_number(String contract_number) {
        this.contract_number = contract_number;
    }
    public String getOut_trade_no() {
        return out_trade_no;
    }
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
    public String getTransaction_id() {
        return transaction_id;
    }
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    public String getPass_trade_no() {
        return pass_trade_no;
    }
    public void setPass_trade_no(String pass_trade_no) {
        this.pass_trade_no = pass_trade_no;
    }
    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public BigDecimal getCharge() {
        return charge;
    }
    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "AlipayAnnal [id=" + id + ", appid=" + appid + ", mch_appid=" + mch_appid + ", wx_appid=" + wx_appid
                + ", openid=" + openid + ", consume_id=" + consume_id + ", contract_number=" + contract_number
                + ", out_trade_no=" + out_trade_no + ", transaction_id=" + transaction_id + ", pass_trade_no="
                + pass_trade_no + ", value=" + value + ", charge=" + charge + "]";
    }    
}
