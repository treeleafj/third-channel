package org.treeleaf.thirdchannel.wechat.pay.entity;

/**
 * 微信订单查询请求
 *
 * @author leaf
 * @date 2016-10-27 10:17
 */
public class OrderQuery {

    /**
     * 微信开放平台审核通过的应用APPID
     */
    private String appid;

    /**
     * 微信支付分配的商户号
     */
    private String mch_id;

    /**
     * 微信的订单号，优先使用
     */
    private String transaction_id;

    /**
     * 商户系统内部的订单号，当没提供transaction_id时需要传这个。
     */
    private String out_trade_no;

    /**
     * 随机字符串，不长于32位。推荐随机数生成算法
     */
    private String nonce_str;

    /**
     * 签名，详见签名生成算法
     */
    private String sign;

    public String getAppid() {
        return appid;
    }

    public OrderQuery setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getMch_id() {
        return mch_id;
    }

    public OrderQuery setMch_id(String mch_id) {
        this.mch_id = mch_id;
        return this;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public OrderQuery setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public OrderQuery setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
        return this;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public OrderQuery setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public OrderQuery setSign(String sign) {
        this.sign = sign;
        return this;
    }
}
