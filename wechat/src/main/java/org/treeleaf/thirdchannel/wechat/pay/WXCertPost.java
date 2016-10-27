package org.treeleaf.thirdchannel.wechat.pay;

import org.apache.http.client.HttpClient;
import org.treeleaf.common.http.httpclient.Post;

/**
 * 专门用于微信需要证书的请求
 *
 * @author leaf
 * @date 2016-09-27 16:37
 */
public class WXCertPost extends Post {

    /**
     * 证书路径
     */
    private String certPath;

    /**
     * 证书密码
     */
    private String password;

    /**
     * 构建Get请求对象
     *
     * @param address 请求地址,可以是http或者https
     */
    public WXCertPost(String address) {
        super(address);
    }

    @Override
    protected HttpClient buildHttpClient() throws Exception {
        return HttpClientFactory.get(this.certPath, this.password);
    }

    public WXCertPost certPath(String certPath) {
        this.certPath = certPath;
        return this;
    }

    public WXCertPost password(String password) {
        this.password = password;
        return this;
    }
}
