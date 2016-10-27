package org.treeleaf.thirdchannel.wechat.pay.entity;

/**
 * 微信订单查询结果
 *
 * @author leaf
 * @date 2016-10-27 10:19
 */
public class OrderQueryResult {

    /**
     * 返回状态码, SUCCESS/FAIL, 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    private String return_code;

    /**
     * 返回信息，如非空:为错误原因,签名失败,参数格式校验错误
     */
    private String return_msg;

    /**
     * 应用APPID,是否必填:是	微信开放平台审核通过的应用APPID
     */
    private String appid;

    /**
     * 商户号,是否必填:是	 微信支付分配的商户号
     */
    private String mch_id;

    /**
     * 随机字符串,是否必填:是	String(32)	随机字符串，不长于32位。推荐随机数生成算法
     */
    private String nonce_str;

    /**
     * 签名,是否必填:是	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法
     */
    private String sign;

    /**
     * 业务结果,是否必填:是	SUCCESS	SUCCESS/FAIL
     */
    private String result_code;

    /**
     * 错误代码,是否必填:否	SYSTEMERROR	错误码
     */
    private String err_code;

    /**
     * 错误代码描述,是否必填:否	系统错误	结果信息描述
     */
    private String err_code_des;

    /**
     * 设备号, 是否必填:否	String(32)	013467007045764	微信支付分配的终端设备号，
     */
    private String device_info;

    /**
     * 用户标识, 是否必填:是	String(128)	oUpF8uMuAJO_M2pxb1Q9zNjWeS6o	用户在商户appid下的唯一标识
     */
    private String openid;

    /**
     * 是否关注公众账号, 是否必填:否	String(1)	Y	用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
     */
    private String is_subscribe;

    /**
     * 交易类型, 是否必填:是	String(16)	APP	调用接口提交的交易类型
     */
    private String trade_type;

    /**
     * 交易状态, 是否必填:是	String(32)	SUCCESS SUCCESS—支付成功,REFUND—转入退款,NOTPAY—未支付,CLOSED—已关闭,REVOKED—已撤销（刷卡支付）,USERPAYING--用户支付中,PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    private String trade_state;

    /**
     * 付款银行, 是否必填:是	String(16)	CMC	银行类型，采用字符串类型的银行标识
     */
    private String bank_type;

    /**
     * 总金额, 是否必填:是	Int	100	订单总金额，单位为分
     */
    private String total_fee;

    /**
     * 货币种类, 是否必填:否	String(8)	CNY	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    private String fee_type;

    /**
     * 现金支付金额, 是否必填:是	Int	100	现金支付金额订单现金支付金额，详见支付金额
     */
    private String cash_fee;

    /**
     * 现金支付货币类型, 是否必填:否	String(16)	CNY	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    private String cash_fee_type;

    /**
     * 代金券或立减优惠金额, 是否必填:否	Int	100	“代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额，详见支付金额
     */
    private String coupon_fee;

    /**
     * 代金券或立减优惠使用数量, 是否必填:否	Int	1	代金券或立减优惠使用数量
     */
    private String coupon_count;

    /**
     * 代金券或立减优惠批次ID, 是否必填:否	String(20)	100	代金券或立减优惠批次ID ,$n为下标，从0开始编号
     */
    private String coupon_batch_id_$n;

    /**
     * 代金券或立减优惠ID, 是否必填:否	String(20)	10000 	代金券或立减优惠ID, $n为下标，从0开始编号
     */
    private String coupon_id_$n;

    /**
     * 单个代金券或立减优惠支付金额, 是否必填:否	Int	100	单个代金券或立减优惠支付金额, $n为下标，从0开始编号
     */
    private String coupon_fee_$n;

    /**
     * 微信支付订单号, 是否必填:是	String(32)	1009660380201506130728806387	微信支付订单号
     */
    private String transaction_id;

    /**
     * 商户订单号, 是否必填:是	String(32)	20150806125346	商户系统的订单号，与请求一致。
     */
    private String out_trade_no;

    /**
     * 附加数据, 是否必填:否	String(128)	深圳分店	附加数据，原样返回
     */
    private String attach;

    /**
     * 支付完成时间, 是否必填:是	String(14)	20141030133525	订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     */
    private String time_end;

    /**
     * 交易状态描述, 是否必填:是	String(256)	支付失败，请重新下单支付	对当前查询订单状态的描述和下一步操作的指引
     */
    private String trade_state_desc;

    public String getReturn_code() {
        return return_code;
    }

    public OrderQueryResult setReturn_code(String return_code) {
        this.return_code = return_code;
        return this;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public OrderQueryResult setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
        return this;
    }

    public String getAppid() {
        return appid;
    }

    public OrderQueryResult setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getMch_id() {
        return mch_id;
    }

    public OrderQueryResult setMch_id(String mch_id) {
        this.mch_id = mch_id;
        return this;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public OrderQueryResult setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public OrderQueryResult setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String getResult_code() {
        return result_code;
    }

    public OrderQueryResult setResult_code(String result_code) {
        this.result_code = result_code;
        return this;
    }

    public String getErr_code() {
        return err_code;
    }

    public OrderQueryResult setErr_code(String err_code) {
        this.err_code = err_code;
        return this;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public OrderQueryResult setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
        return this;
    }

    public String getDevice_info() {
        return device_info;
    }

    public OrderQueryResult setDevice_info(String device_info) {
        this.device_info = device_info;
        return this;
    }

    public String getOpenid() {
        return openid;
    }

    public OrderQueryResult setOpenid(String openid) {
        this.openid = openid;
        return this;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public OrderQueryResult setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
        return this;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public OrderQueryResult setTrade_type(String trade_type) {
        this.trade_type = trade_type;
        return this;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public OrderQueryResult setTrade_state(String trade_state) {
        this.trade_state = trade_state;
        return this;
    }

    public String getBank_type() {
        return bank_type;
    }

    public OrderQueryResult setBank_type(String bank_type) {
        this.bank_type = bank_type;
        return this;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public OrderQueryResult setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
        return this;
    }

    public String getFee_type() {
        return fee_type;
    }

    public OrderQueryResult setFee_type(String fee_type) {
        this.fee_type = fee_type;
        return this;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public OrderQueryResult setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
        return this;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public OrderQueryResult setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
        return this;
    }

    public String getCoupon_fee() {
        return coupon_fee;
    }

    public OrderQueryResult setCoupon_fee(String coupon_fee) {
        this.coupon_fee = coupon_fee;
        return this;
    }

    public String getCoupon_count() {
        return coupon_count;
    }

    public OrderQueryResult setCoupon_count(String coupon_count) {
        this.coupon_count = coupon_count;
        return this;
    }

    public String getCoupon_batch_id_$n() {
        return coupon_batch_id_$n;
    }

    public OrderQueryResult setCoupon_batch_id_$n(String coupon_batch_id_$n) {
        this.coupon_batch_id_$n = coupon_batch_id_$n;
        return this;
    }

    public String getCoupon_id_$n() {
        return coupon_id_$n;
    }

    public OrderQueryResult setCoupon_id_$n(String coupon_id_$n) {
        this.coupon_id_$n = coupon_id_$n;
        return this;
    }

    public String getCoupon_fee_$n() {
        return coupon_fee_$n;
    }

    public OrderQueryResult setCoupon_fee_$n(String coupon_fee_$n) {
        this.coupon_fee_$n = coupon_fee_$n;
        return this;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public OrderQueryResult setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public OrderQueryResult setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
        return this;
    }

    public String getAttach() {
        return attach;
    }

    public OrderQueryResult setAttach(String attach) {
        this.attach = attach;
        return this;
    }

    public String getTime_end() {
        return time_end;
    }

    public OrderQueryResult setTime_end(String time_end) {
        this.time_end = time_end;
        return this;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public OrderQueryResult setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
        return this;
    }
}
