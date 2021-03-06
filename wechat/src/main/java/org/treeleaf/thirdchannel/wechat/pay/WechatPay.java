package org.treeleaf.thirdchannel.wechat.pay;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.treeleaf.common.bean.FastBeanUtils;
import org.treeleaf.common.http.HttpHeader;
import org.treeleaf.common.http.httpclient.Post;
import org.treeleaf.common.safe.Maths;
import org.treeleaf.common.safe.Uuid;
import org.treeleaf.thirdchannel.wechat.pay.entity.JsapiParam;
import org.treeleaf.thirdchannel.wechat.pay.entity.Notice;
import org.treeleaf.thirdchannel.wechat.pay.entity.OrderQuery;
import org.treeleaf.thirdchannel.wechat.pay.entity.OrderQueryResult;
import org.treeleaf.thirdchannel.wechat.pay.entity.OrderRefund;
import org.treeleaf.thirdchannel.wechat.pay.entity.RefundResult;
import org.treeleaf.thirdchannel.wechat.pay.entity.UnifiedOrder;
import org.treeleaf.thirdchannel.wechat.pay.entity.UnifiedOrderResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付
 *
 * @Author leaf
 * 2016/3/11 0011 20:52.
 */
public class WechatPay extends WechatMerchantInterface {

    private static Logger log = LoggerFactory.getLogger(WechatPay.class);

    public static final String DEVICE_INFO = "WEB";

    public static final String TRADE_TYPE_JSAPI = "JSAPI";
    public static final String TRADE_TYPE_NATIVE = "NATIVE";
    public static final String TRADE_TYPE_APP = "APP";
    public static final String TRADE_TYPE_WAP = "WAP";

    /**
     * 应用id
     */
    private String appid;

    /**
     * 微信支付商户号
     */
    private String merchantNo;

    /**
     * 商户密钥
     */
    private String key;

    /**
     * CA证书位置
     */
    private String certPath;


    /**
     * 微信统一下单
     *
     * @param order
     * @return
     */
    public UnifiedOrderResult unifiedorder(UnifiedOrder order) {

        order.setAppid(this.appid);
        order.setMch_id(this.merchantNo);
        order.setNonce_str(Uuid.buildBase64UUID());

        //2.签名
        String sign = WechatPaySignature.sign(order, this.key);
        order.setSign(sign);

        Map<String, String> orderReq;
        try {
            orderReq = FastBeanUtils.describe(order);
            orderReq.remove("class");
        } catch (Exception e) {
            throw new RuntimeException("将java对象转为Map失败", e);
        }

        //3.转xml
        String xml = this.mapToXml(orderReq);

        log.debug("生成微信统一下单接口参数:\n{}", xml);

        //4.发送
        String r = new Post("https://api.mch.weixin.qq.com/pay/unifiedorder")
                .header(HttpHeader.NAME_CONTENT_TYPE, HttpHeader.CONTENT_TYPE_XML)
                .body(xml).send();

        log.debug("调用微信统一下单接口成功,返回:\n{}", r);

        //5.将从API返回的XML数据映射到Java对象
        Map returnMap = this.xmlToMap(r);

        UnifiedOrderResult orderResult = FastBeanUtils.fastPopulate(UnifiedOrderResult.class, returnMap);

        orderResult.setOut_trade_no(order.getOut_trade_no());//特别增加这个

        //7.验签
        String returnSign = (String) returnMap.get("sign");
        if ("SUCCESS".equals(orderResult.getReturn_code()) && "SUCCESS".equals(orderResult.getResult_code())) {
            returnMap.remove("sign");
            String s = WechatPaySignature.sign(returnMap, this.key);
            if (StringUtils.isBlank(returnSign) || !s.equals(returnSign)) {
                throw new WechatPayException("微信统一下单返回数据验签失败:" + returnSign + "," + s);
            }
        }

        return orderResult;
    }

    /**
     * 对微信通知接口传过来的xml进行验签
     *
     * @param xml 微信通知传递过来的xml
     * @return
     */
    public Notice notice(String xml) {
        //1.解析入参
        Map map = this.xmlToMap(xml);

        Notice notice = FastBeanUtils.fastPopulate(Notice.class, map);

        //2.业务逻辑判断
        if ("SUCCESS".equals(notice.getReturn_code()) && "SUCCESS".equals(notice.getResult_code())) {
            //3.验签
            map.remove("sign");

            String s = WechatPaySignature.sign(map, this.key);
            if (StringUtils.isBlank(s) || !s.equals(notice.getSign())) {
                throw new WechatPayException("验签失败:" + notice.getSign() + "," + s);
            }
        }

        //3.返回结果
        return notice;
    }

    /**
     * 生成微信js api支付的参数
     *
     * @param orderResult 统一下单返回的结果
     * @return
     */
    public JsapiParam jsapi(UnifiedOrderResult orderResult) {

        String signType = "MD5";
        String pk = "prepay_id=" + orderResult.getPrepay_id();

        String timestamp = String.valueOf(Maths.divide(Double.valueOf(System.currentTimeMillis()), 1000D).intValue());
        String nonce = Uuid.buildBase64UUID();

        Map<String, String> model = new HashMap<>();
        model.put("appId", this.appid);
        model.put("timeStamp", timestamp);
        model.put("nonceStr", nonce);
        model.put("package", pk);
        model.put("signType", signType);

        String sign = WechatPaySignature.sign(model, this.key);

        JsapiParam jsapiParam = new JsapiParam();
        jsapiParam.setAppId(this.appid);
        jsapiParam.setTimeStamp(timestamp);
        jsapiParam.setNonceStr(nonce);
        jsapiParam.setPk(pk);
        jsapiParam.setSignType(signType);
        jsapiParam.setPaySign(sign);//签名
        jsapiParam.setOrderno(orderResult.getOut_trade_no());
        return jsapiParam;
    }

    /**
     * 微信退款, orderNo,wxOrderNo二者只需要传一个,如果两个都传,以微信订单号为准
     *
     * @param orderNo   伤害订单号
     * @param wxOrderNo 微信订单号
     * @return
     */
    public OrderQueryResult queryPayOrder(String orderNo, String... wxOrderNo) {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setAppid(this.appid);
        orderQuery.setMch_id(this.merchantNo);
        orderQuery.setNonce_str(Uuid.buildBase64UUID());
        orderQuery.setOut_trade_no(orderNo);
        orderQuery.setTransaction_id(wxOrderNo != null && wxOrderNo.length > 0 ? wxOrderNo[0] : null);

        //2.签名
        String sign = WechatPaySignature.sign(orderQuery, this.key);
        orderQuery.setSign(sign);

        Map<String, String> orderReq;
        try {
            orderReq = FastBeanUtils.describe(orderQuery);
            orderReq.remove("class");
        } catch (Exception e) {
            throw new RuntimeException("将java对象转为Map失败", e);
        }

        //3.转xml
        String xml = this.mapToXml(orderReq);

        log.debug("生成微信订单查询接口参数:\n{}", xml);

        //4.发送
        String r = new Post("https://api.mch.weixin.qq.com/pay/orderquery")
                .header(HttpHeader.NAME_CONTENT_TYPE, HttpHeader.CONTENT_TYPE_XML)
                .body(xml).send();

        log.debug("调用微信订单查询接口成功,返回:\n{}", r);

        //5.将从API返回的XML数据映射到Java对象
        Map returnMap = this.xmlToMap(r);

        //6.验签
        String returnSign = (String) returnMap.get("sign");
        OrderQueryResult orderQueryResult = FastBeanUtils.fastPopulate(OrderQueryResult.class, returnMap);
        if ("SUCCESS".equals(orderQueryResult.getReturn_code()) && "SUCCESS".equals(orderQueryResult.getResult_code())) {
            returnMap.remove("sign");
            String s = WechatPaySignature.sign(returnMap, this.key);
            if (StringUtils.isBlank(returnSign) || !s.equals(returnSign)) {
                throw new WechatPayException("微信订单查询返回数据验签失败:" + returnSign + "," + s);
            }
        }

        return orderQueryResult;
    }

    public RefundResult refund(OrderRefund orderRefund) {
        orderRefund.setAppid(this.appid);
        orderRefund.setMch_id(this.merchantNo);
        orderRefund.setNonce_str(Uuid.buildBase64UUID());
//        外面可以选择设置下面三个
//        orderRefund.setTransaction_id();
//        orderRefund.setOut_trade_no();
//        orderRefund.setTotal_fee();
//        orderRefund.setRefund_fee();
        orderRefund.setRefund_fee_type("CNY");
        if (StringUtils.isBlank(orderRefund.getOp_user_id())) {
            orderRefund.setOp_user_id(this.merchantNo);
        }

        //2.签名
        String sign = WechatPaySignature.sign(orderRefund, this.key);
        orderRefund.setSign(sign);

        Map<String, String> orderReq;
        try {
            orderReq = FastBeanUtils.describe(orderRefund);
            orderReq.remove("class");
        } catch (Exception e) {
            throw new RuntimeException("将java对象转为Map失败", e);
        }

        //3.转xml
        String xml = this.mapToXml(orderReq);

        log.debug("生成微信退款接口参数:\n{}", xml);

        //4.发送
        String r = new WXCertPost("https://api.mch.weixin.qq.com/secapi/pay/refund")
                .certPath(this.certPath)
                .password(this.merchantNo)
                .header(HttpHeader.NAME_CONTENT_TYPE, HttpHeader.CONTENT_TYPE_XML)
                .body(xml).send();

        log.debug("调用微信退款接口成功,返回:\n{}", r);

        //5.将从API返回的XML数据映射到Java对象
        Map returnMap = this.xmlToMap(r);

        //6.验签
        String returnSign = (String) returnMap.get("sign");
        RefundResult orderQueryResult = FastBeanUtils.fastPopulate(RefundResult.class, returnMap);
        if ("SUCCESS".equals(orderQueryResult.getReturn_code()) && "SUCCESS".equals(orderQueryResult.getResult_code())) {
            returnMap.remove("sign");
            String s = WechatPaySignature.sign(returnMap, this.key);
            if (StringUtils.isBlank(returnSign) || !s.equals(returnSign)) {
                throw new WechatPayException("微信退款返回数据验签失败:" + returnSign + "," + s);
            }
        }

        return orderQueryResult;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public WechatPay setCertPath(String certPath) {
        this.certPath = certPath;
        return this;
    }
}
