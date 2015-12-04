package com.revencoft.connection_proxy.trust;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * @author mengqingyan
 * 
 */
public class ProxyTrustManager implements X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		System.out.println("检查客户端的可信任状态...");

	}

	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		System.out.println("检查服务器的可信任状态");

	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		System.out.println("获取接受的发行商数组...");
		return null;
	}

}
