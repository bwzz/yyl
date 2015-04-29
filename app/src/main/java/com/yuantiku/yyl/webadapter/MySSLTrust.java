package com.yuantiku.yyl.webadapter;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author lirui
 * @date 15/4/28.
 */
public class MySSLTrust {
    @SuppressWarnings("null")
    public static OkHttpClient configureClient(final OkHttpClient client) {
        final TrustManager[] certs = new TrustManager[] {
                new X509TrustManager() {

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkServerTrusted(final X509Certificate[] chain,
                            final String authType) throws CertificateException {}

                    @Override
                    public void checkClientTrusted(final X509Certificate[] chain,
                            final String authType) throws CertificateException {}
                }
        };

        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, certs, new SecureRandom());
        } catch (final java.security.GeneralSecurityException ex) {}

        try {
            final HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(final String hostname,
                        final SSLSession session) {
                    return true;
                }
            };
            client.setHostnameVerifier(hostnameVerifier);
            client.setSslSocketFactory(ctx.getSocketFactory());
        } catch (final Exception e) {}

        return client;
    }

    public static OkHttpClient trustcert(Context context) {
        final OkHttpClient client = new OkHttpClient();
        return configureClient(client);
    }
}
