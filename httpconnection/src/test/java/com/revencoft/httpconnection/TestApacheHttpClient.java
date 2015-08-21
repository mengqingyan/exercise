/**
 * 
 */
package com.revencoft.httpconnection;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;

import com.revencoft.httpconnection.HttpClientConnection.ActionWithHttpResponse;

/**
 * @author mengqingyan
 * @version 
 */
public class TestApacheHttpClient {
	
	
	@Test
	public void testHttpClient() throws IOException {
		
		final HttpClientConnection httpClient = new HttpClientConnection();
		final String uri = "http://central.maven.org/maven2/commons-io/commons-io/2.4/commons-io-2.4.jar";
		HttpUriRequest request = new HttpGet(uri);
		
		httpClient.openUrl(request , new ActionWithHttpResponse<Boolean>() {

			@Override
			public Boolean action(CloseableHttpResponse response)
					throws IOException {
				HttpEntity entity = response.getEntity();
				long fileLen = entity.getContentLength();
				InputStream in = entity.getContent();
				
				String fileName = uri.substring(uri.lastIndexOf("/"));
				
				try {
					RevencoftIOUtils.downLoadFile(in, fileName, fileLen);
				} finally {
					RevencoftIOUtils.closeStream(in);
					httpClient.destroyApacheHttpClient();
				}
				return true;
			}
		});
	}
}
