package org.treeleaf.thirdchannel.wechat.pay;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.treeleaf.common.safe.Sha;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leaf on 2016/3/17 0017.
 */
public abstract class WechatMerchantInterface {

    /**
     * 对微信事件推送验签
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param token
     * @return
     */
    public static boolean valid(String signature, String timestamp, String nonce, String token) {
        String s = sign(timestamp, nonce, token);
        return s.equals(signature);
    }

    /**
     * 微信事件通知签名
     *
     * @param timestamp 时间戳
     * @param nonce     返回的随机数
     * @param token     校验token
     * @return
     */
    public static String sign(String timestamp, String nonce, String token) {
        String[] array = new String[]{token, timestamp, nonce};
        Arrays.sort(array);
        StringBuffer sb = new StringBuffer();
        for (String s : array) {
            sb.append(s);
        }

        String src = sb.toString();
        return Sha.bytes2Hex(Sha.sha1(src));
    }

    /**
     * 将map对象转为微信需要的xml格式
     *
     * @param map
     * @return
     */
    public static String mapToXml(Map<String, String> map) {
        //将数据转为xml
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("xml");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (StringUtils.isNotEmpty(entry.getValue())) {
                root.addElement(entry.getKey()).addText(entry.getValue());
            }
        }

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setIndent(true);
        format.setSuppressDeclaration(true);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XMLWriter output;
        try {
            output = new XMLWriter(outputStream, format);
            output.write(document);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return outputStream.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("微信返回的数据转为UTF-8失败", e);
        }
    }

    /**
     * 将微信返回的xml解析成map对象
     *
     * @param xml
     * @return
     */
    public static Map<String, String> xmlToMap(String xml) {
        Document document;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            throw new RuntimeException("解析的xml格式不正确:\n" + xml, e);
        }
        Element root = document.getRootElement();
        List<Element> list = root.elements();
        Map<String, String> map = new HashMap<>();
        for (Element item : list) {
            map.put(item.getName(), item.getText());
        }
        return map;
    }
}
