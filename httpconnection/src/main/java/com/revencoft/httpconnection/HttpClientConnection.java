/**
 * 
 */
package com.revencoft.httpconnection;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author mengqingyan
 * @version 
 */
public class HttpClientConnection {
	
	private static final Logger log = Logger.getLogger(HttpClientConnection.class.getName());

	private static final int DEF_TIME_OUT_SECONDS = 10;
	private static final int DEF_MAX_CONN_TOTAL = 10;
	private CloseableHttpClient client;
	
	/**
	 * 
	 */
	public HttpClientConnection() {
		super();
		initApacheHttpClient();
	}
	
	public <T> T openUrl(HttpUriRequest request, ActionWithHttpResponse<T> action) throws IOException {
		
		CloseableHttpResponse response = client.execute(request);
		
		try {
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode >= 400) {
				log.log(Level.SEVERE, String.format("remote request status[%s] error!", statusCode));
				return null;
			}
			return action.action(response);
		} finally {
			response.close();
		}
	}




	private void initApacheHttpClient() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(DEF_TIME_OUT_SECONDS * 1000)
				.setConnectTimeout(DEF_TIME_OUT_SECONDS *1000).build();
		client = HttpClientBuilder.create()
				.setMaxConnTotal(DEF_MAX_CONN_TOTAL)
				.setMaxConnPerRoute(DEF_MAX_CONN_TOTAL)
				.setDefaultRequestConfig(requestConfig)
				.build();
	}
	
	public void destroyApacheHttpClient() {
		if(client != null) {
			try {
				client.close();
			} catch (IOException e) {
				log.log(Level.SEVERE, "exception occurred when try to close apache http client! ", e);
			}
		}
	}
	
	public interface ActionWithHttpResponse<T> extends ConnectionAction<T, CloseableHttpResponse> {
		
	}
}
