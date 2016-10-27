package org.treeleaf.thirdchannel.wechat.pay;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.treeleaf.common.safe.Assert;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * @author leaf
 * @date 2016-09-27 16:29
 */
public class HttpClientFactory {

    private static Logger log = LoggerFactory.getLogger(HttpClientFactory.class);

    private static HttpClient httpClient = null;

    public static void init(String certPath, String password) {
        SSLConnectionSocketFactory sslConnectionSocketFactory = buildSSLConnectionSocketFactory(certPath, password);
        DefaultHostnameVerifier defaultHostnameVerifier = new DefaultHostnameVerifier();

        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();

        Registry<ConnectionSocketFactory> socketFactoryRegistry =
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.INSTANCE)
                        .register("https", sslConnectionSocketFactory).build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        httpClient = HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(config)
                .setSSLHostnameVerifier(defaultHostnameVerifier)
                .build();
    }

    private static SSLConnectionSocketFactory buildSSLConnectionSocketFactory(String certPath, String password) {

        Assert.hasText(certPath, "证书路径不能为空");
        Assert.hasText(password, "证书密码不能为空");

        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");

            KeyStore e = KeyStore.getInstance("PKCS12");
            e.load(new FileInputStream(certPath), password.toCharArray());
            KeyManagerFactory tmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            tmf.init(e, password.toCharArray());
            KeyManager[] keymanagers = tmf.getKeyManagers();
            ctx.init(keymanagers, null, null);
        } catch (Exception e) {
            log.error("初始化证书验证器失败", e);

        }
        return new SSLConnectionSocketFactory(ctx);
    }

    public static HttpClient get(String certPath, String password) {
        if (httpClient == null) {
            synchronized (HttpClientFactory.class) {
                if (httpClient == null) {
                    init(certPath, password);
                }
            }
        }
        return httpClient;
    }
}
