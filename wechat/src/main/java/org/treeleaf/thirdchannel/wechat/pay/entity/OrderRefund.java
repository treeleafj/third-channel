package org.treeleaf.thirdchannel.wechat.pay.entity;

/**
 * 订单退款申请
 *
 * @author leaf
 * @date 2016-10-27 10:58
 */
public class OrderRefund {

    /**
     * 应用ID, 是否必填:是	String(32)	微信开放平台审核通过的应用APPID
     */
    private String appid;

    /**
     * 商户号, 是否必填:是	String(32)	微信支付分配的商户号
     */
    private String mch_id;

    /**
     * 设备号, 是否必填:否	String(32)	终端设备号
     */
    private String device_info;

    /**
     * 随机字符串, 是否必填:是	String(32)	随机字符串，不长于32位。推荐随机数生成算法
     */
    private String nonce_str;

    /**
     * 签名, 是否必填:是	String(32)	签名，详见签名生成算法
     */
    private String sign;

    /**
     * 微信订单号, 是否必填:二选一	String(28)	微信生成的订单号，在支付通知中有返回
     */
    private String transaction_id;

    /**
     * 商户订单号, 是否必填:String(32)		商户侧传给微信的订单号
     */
    private String out_trade_no;

    /**
     * 商户退款单号, 是否必填:是	String(32)	商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
     */
    private String out_refund_no;

    /**
     * 总金额, 是否必填:是	Int	100	订单总金额，单位为分，只能为整数，详见支付金额
     */
    private String total_fee;

    /**
     * 退款金额, 是否必填:是	Int	100	退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
     */
    private String refund_fee;

    /**
     * 货币种类, 是否必填:否	String(8)	CNY	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    private String refund_fee_type;

    /**
     * 操作员, 是否必填:是	String(32)	操作员帐号, 默认为商户号
     */
    private String op_user_id;

    /**
     * 退款资金来源, 是否必填:否	String(30)	REFUND_SOURCE_RECHARGE_FUNDS 仅针对老资金流商户使用,REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）,REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
     */
    private String refund_account;

    public String getAppid() {
        return appid;
    }

    public OrderRefund setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getMch_id() {
        return mch_id;
    }

    public OrderRefund setMch_id(String mch_id) {
        this.mch_id = mch_id;
        return this;
    }

    public String getDevice_info() {
        return device_info;
    }

    public OrderRefund setDevice_info(String device_info) {
        this.device_info = device_info;
        return this;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public OrderRefund setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public OrderRefund setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public OrderRefund setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public OrderRefund setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
        return this;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public OrderRefund setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
        return this;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public OrderRefund setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
        return this;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public OrderRefund setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
        return this;
    }

    public String getRefund_fee_type() {
        return refund_fee_type;
    }

    public OrderRefund setRefund_fee_type(String refund_fee_type) {
        this.refund_fee_type = refund_fee_type;
        return this;
    }

    public String getOp_user_id() {
        return op_user_id;
    }

    public OrderRefund setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
        return this;
    }

    public String getRefund_account() {
        return refund_account;
    }

    public OrderRefund setRefund_account(String refund_account) {
        this.refund_account = refund_account;
        return this;
    }
}
