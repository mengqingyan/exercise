/**
 * 
 */
package com.revencoft.httpconnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.revencoft.httpconnection.JDKConnection.ActionWithConnection;
import com.revencoft.httpconnection.JDKConnection.ConnectionPreProcess;
import com.revencoft.httpconnection.trust.MyX509TrustManager;

/**
 * @author mengqingyan
 * @version 
 */
public class HttpsJDKConnection {

	private static final Logger log = Logger.getLogger(HttpsJDKConnection.class.getSimpleName());
	
	public static <T> T httpRequest(final String url, final String requestMethod, String outputStr) throws Exception {
		
		return openUrl(url, requestMethod, new ConnectionPreProcess() {

			@Override
			public void preProcess(HttpURLConnection httpUrlConn) throws Exception {
				TrustManager[] tm = { new MyX509TrustManager() };
				SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
				sslContext.init(null, tm, new java.security.SecureRandom());
				// 从上述SSLContext对象中得到SSLSocketFactory对象
				SSLSocketFactory ssf = sslContext.getSocketFactory();
				
				((HttpsURLConnection) httpUrlConn).setSSLSocketFactory(ssf);

				httpUrlConn.setDoOutput(true);
				httpUrlConn.setDoInput(true);
				httpUrlConn.setUseCaches(false);
				// 设置请求方式（GET/POST）
				httpUrlConn.setRequestMethod(requestMethod);
			}
			
		},
		new ActionWithConnection<T>() {

			@Override
			public T action(HttpURLConnection httpUrlConn) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
	
	public static <T> T openUrl(String url, String requestMethod, ConnectionPreProcess preConnect, ActionWithConnection<T> action) throws Exception {
		URL reqUrl = new URL(url);
		HttpsURLConnection httpUrlConn = (HttpsURLConnection) reqUrl.openConnection();
		
		preConnect.preProcess(httpUrlConn);
		
		if ("GET".equalsIgnoreCase(requestMethod))
			httpUrlConn.connect();
		
		return action.action(httpUrlConn);
	}
	
	
}
