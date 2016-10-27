package org.treeleaf.thirdchannel.wechat.pay.entity;

/**
 * 退款结果
 *
 * @author leaf
 * @date 2016-10-27 11:02
 */
public class RefundResult {

    /**
     * 返回状态码, SUCCESS/FAIL, 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    private String return_code;

    /**
     * 返回信息，如非空:为错误原因,签名失败,参数格式校验错误
     */
    private String return_msg;

    /**
     * 业务结果	是否必填:是	String(16)	SUCCESS/FAIL,SUCCESS退款申请接收成功，结果通过退款查询接口查询,FAIL 提交业务失败
     */
    private String result_code;

    /**
     * 错误代码	是否必填:否	String(32)	SYSTEMERROR	列表详见错误码列表
     */
    private String err_code;

    /**
     * 错误代码描述	是否必填:否	String(128)	系统超时	结果信息描述
     */
    private String err_code_des;

    /**
     * 应用ID	是否必填:是	String(32)	微信开放平台审核通过的应用APPID
     */
    private String appid;

    /**
     * 商户号	是否必填:是	String(32)	微信支付分配的商户号
     */
    private String mch_id;

    /**
     * 设备号	是否必填:否	String(32)	微信支付分配的终端设备号，与下单一致
     */
    private String device_info;

    /**
     * 随机字符串	是否必填:是	String(32)	随机字符串，不长于32位
     */
    private String nonce_str;

    /**
     * 签名	是否必填:是	String(32)	签名，详见签名算法
     */
    private String sign;

    /**
     * 微信订单号	是否必填:是	String(28)	微信订单号
     */
    private String transaction_id;

    /**
     * 商户订单号	是否必填:是	String(32)	商户系统内部的订单号
     */
    private String out_trade_no;

    /**
     * 商户退款单号	是否必填:是	String(32)	商户退款单号
     */
    private String out_refund_no;

    /**
     * 微信退款单号	是否必填:是	String(28)	微信退款单号
     */
    private String refund_id;

    /**
     * 退款渠道	是否必填:否	String(16)	ORIGINAL,ORIGINAL—原路退款,BALANCE—退回到余额
     */
    private String refund_channel;

    /**
     * 退款金额	是否必填:是	Int	100	退款总金额,单位为分,可以做部分退款
     */
    private String refund_fee;

    /**
     * 订单总金额	是否必填:是	Int	100	订单总金额，单位为分，只能为整数，详见支付金额
     */
    private String total_fee;

    /**
     * 订单金额货币种类	是否必填:否	String(8)	CNY	订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    private String fee_type;

    /**
     * 现金支付金额	是否必填:是	Int	100	现金支付金额，单位为分，只能为整数，详见支付金额
     */
    private String cash_fee;

    /**
     * 现金退款金额	是否必填:否	Int	100	现金退款金额，单位为分，只能为整数，详见支付金额
     */
    private String cash_refund_fee;

    /**
     * 代金券或立减优惠退款金额	是否必填:否	Int	100	代金券或立减优惠退款金额=订单金额-现金退款金额，注意：立减优惠金额不会退回
     */
    private String coupon_refund_fee;

    /**
     * 代金券或立减优惠使用数量	是否必填:否	Int	1	代金券或立减优惠使用数量
     */
    private String coupon_refund_count;

    /**
     * 代金券或立减优惠ID	是否必填:否	String(20)	10000 	代金券或立减优惠ID
     */
    private String coupon_refund_id;

    public String getReturn_code() {
        return return_code;
    }

    public RefundResult setReturn_code(String return_code) {
        this.return_code = return_code;
        return this;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public RefundResult setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
        return this;
    }

    public String getResult_code() {
        return result_code;
    }

    public RefundResult setResult_code(String result_code) {
        this.result_code = result_code;
        return this;
    }

    public String getErr_code() {
        return err_code;
    }

    public RefundResult setErr_code(String err_code) {
        this.err_code = err_code;
        return this;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public RefundResult setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
        return this;
    }

    public String getAppid() {
        return appid;
    }

    public RefundResult setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getMch_id() {
        return mch_id;
    }

    public RefundResult setMch_id(String mch_id) {
        this.mch_id = mch_id;
        return this;
    }

    public String getDevice_info() {
        return device_info;
    }

    public RefundResult setDevice_info(String device_info) {
        this.device_info = device_info;
        return this;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public RefundResult setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public RefundResult setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public RefundResult setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public RefundResult setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
        return this;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public RefundResult setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
        return this;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public RefundResult setRefund_id(String refund_id) {
        this.refund_id = refund_id;
        return this;
    }

    public String getRefund_channel() {
        return refund_channel;
    }

    public RefundResult setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
        return this;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public RefundResult setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
        return this;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public RefundResult setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
        return this;
    }

    public String getFee_type() {
        return fee_type;
    }

    public RefundResult setFee_type(String fee_type) {
        this.fee_type = fee_type;
        return this;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public RefundResult setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
        return this;
    }

    public String getCash_refund_fee() {
        return cash_refund_fee;
    }

    public RefundResult setCash_refund_fee(String cash_refund_fee) {
        this.cash_refund_fee = cash_refund_fee;
        return this;
    }

    public String getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public RefundResult setCoupon_refund_fee(String coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
        return this;
    }

    public String getCoupon_refund_count() {
        return coupon_refund_count;
    }

    public RefundResult setCoupon_refund_count(String coupon_refund_count) {
        this.coupon_refund_count = coupon_refund_count;
        return this;
    }

    public String getCoupon_refund_id() {
        return coupon_refund_id;
    }

    public RefundResult setCoupon_refund_id(String coupon_refund_id) {
        this.coupon_refund_id = coupon_refund_id;
        return this;
    }
}
