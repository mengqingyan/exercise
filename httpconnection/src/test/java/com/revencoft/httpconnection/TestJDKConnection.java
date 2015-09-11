/**
 * 
 */
package com.revencoft.httpconnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.revencoft.httpconnection.JDKConnection.ConnectionPreProcess;
import com.revencoft.httpconnection.JDKConnection.ActionWithConnection;
import com.revencoft.httpconnection.JDKConnection.ActionWithInputStream;


/**
 * @author mengqingyan
 * @version 
 */
public class TestJDKConnection {
	

	@Test
	public void testInputStreamAction() throws Exception {
		
		String url = "http://www.baidu.com";
		String result = JDKConnection.openUrl(url , 5, new ActionWithInputStream<String>() {

			@Override
			public String action(InputStream in) throws IOException {
				
				return IOUtils.toString(in);
			}
		});
		System.out.println(result);
	}
	
	@Test
	public void testConnectionAction() throws Exception {
		String downUrl = "http://central.maven.org/maven2/commons-io/commons-io/2.4/commons-io-2.4.jar";
		Map<String, String> results = JDKConnection.openUrl(downUrl, 5, new ActionWithConnection<Map<String,String>>() {

			@Override
			public Map<String, String> action(HttpURLConnection connection)
					throws IOException {
				String contentType = connection.getContentType();
				int length = connection.getContentLength();
				Map<String, String> results = new HashMap<String, String>();
				results.put("contentType", contentType);
				results.put("contentLen", String.valueOf(length));
				return results;
			}
		});
		
		Set<Entry<String, String>> entrySet = results.entrySet();
		for (Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey() + "==" + entry.getValue());
		}
	}
	
	@Test
	public void testDownLoadWithInputStreamAction() throws Exception {
		
		final String downUrl = "http://central.maven.org/maven2/commons-io/commons-io/2.4/commons-io-2.4.jar";
		
		JDKConnection.openUrl(downUrl, 30, new ActionWithInputStream<Boolean>() {

			@Override
			public Boolean action(InputStream in) throws IOException {
				
				String fileName = downUrl.substring(downUrl.lastIndexOf("/"));
				
				RevencoftIOUtils.downLoadFile(in, fileName, 0);
				
				return true;
			}
		});
	}
	
	@Test
	public void testDownLoadWithConnectionAction() throws Exception {
		final String downUrl = "http://central.maven.org/maven2/commons-io/commons-io/2.4/commons-io-2.4.jar";
		
		JDKConnection.openUrl(downUrl, 30, new ActionWithConnection<Boolean>() {

			@Override
			public Boolean action(HttpURLConnection connection)
					throws IOException {
				int fileLen = connection.getContentLength();
				String fileName = downUrl.substring(downUrl.lastIndexOf("/"));
				
				InputStream in = connection.getInputStream();
				
				try {
					RevencoftIOUtils.downLoadFile(in, fileName, fileLen);
				} finally {
					RevencoftIOUtils.closeStream(in);
				}
				return true;
			}
		});
	}
	
	@Test
	public void testJDKConnectionWeather() throws Exception {
		final String url = "http://m.weather.com.cn/atad/101280101.html";
		
		ConnectionPreProcess preConnect = new ConnectionPreProcess() {
			
			@Override
			public void preProcess(HttpURLConnection connection) throws Exception {
				connection.setRequestMethod("GET");
				connection.setReadTimeout(30000);
				connection.setConnectTimeout(30000);
				String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";//模拟浏览器
				connection.setRequestProperty("User-agent",userAgent);
			}
		};
		
		ActionWithInputStream<String> action = new ActionWithInputStream<String>() {

			@Override
			public String action(InputStream in) throws IOException {
				return IOUtils.toString(in);
			}
		};
		String result = JDKConnection.openUrl(url, preConnect, action );
		
		System.out.println(result);
		
	}

	
}
