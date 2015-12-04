package com.revencoft.connection_proxy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.junit.Test;

import com.revencoft.connection_proxy.exception.HttpProxyException;
import com.revencoft.connection_proxy.trust.ProxyTrustManager;

/**
 * @author mengqingyan
 * 
 */
public class TestHttps {
	static final int HTTPS_PORT = 443;

	@Test
	public void getHttps() throws IOException {

		String host = "www.sogou.com";
		// 受访的页面
//		String url = "/s?ver=1.0.4.1&enifr=1&showid=7OQu63&type=1&of=1&refer=http%3A%2F%2Fwww.163.com%2F&uid=14491117877302510598070220182475";

		String fu = "";
		
		Socket socket = buildSSLSocket(host, HTTPS_PORT);
		socket.setSoTimeout(5000);
OutputStream os = socket.getOutputStream();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(os));
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		out.write("GET " + url + " HTTP/1.1\r\n");

//		out.write("User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0\r\n");
//		out.write("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
//		out.write("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
//		out.write("Accept-Encoding: gzip, deflate\r\n");
//		out.write(
//				"Cookie: v=c[.Y2BX).ZB#+Jpm+j.S; ckmts=brPdrsXV,PUJFlRHV,P6PdrsXV,-GPdrsXV,RGPdrsXV,R6PdrsXV,U6PdrsXV,JGPdrsXV,J6bZ5ReV,bUPdrsXV\r\n");
//		out.write("Connection: keep-alive\r\n");
//		out.write("Pragma: no-cache\r\n");
//		out.write("Cache-Control: no-cache\r\n");
//		out.write("");
//		out.flush();
		
		os.write(("GET " + fu + " HTTP/1.1\r\n").getBytes());
		
		os.write("Host: www.sogou.com:443\r\n".getBytes());
		os.write("User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0\r\n".getBytes());
//		os.write("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n".getBytes());
//		os.write("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n".getBytes());
//		os.write("Accept-Encoding: gzip, deflate\r\n".getBytes());
////		os.write(
////				"Cookie: v=c[.Y2BX).ZB#w!xoZcU8; ckmts=brPdrsXV,PUbwHe0V,P6PdrsXV,-GPdrsXV,RGPdrsXV,R6PdrsXV,U6PdrsXV,JGPdrsXV,J6bZ5ReV,bUPdrsXV; route=ebac130411de7f6073e99bb4521bd09a\r\n".getBytes());
////		os.write("Connection: keep-alive\r\n".getBytes());
//		os.write("Pragma: no-cache\r\n".getBytes());
//		os.write("Cache-Control: no-cache\r\n".getBytes());
		os.write("\r\n".getBytes());
		
		os.write(" ".getBytes());
		
		System.out.println("start   work!");
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = in.readLine()) != null) {
			sb.append(line + "\n");
		}
		out.close();
		in.close();
		System.out.println(sb.toString());
	}
	
	@Test
	public void testURl() throws MalformedURLException {
		URL u = new URL("https://show-a.mediav.com:443");
		System.out.println(u.getProtocol());
		System.out.println(u.getHost());
		System.out.println(u.getPort());
	}

	private Socket buildSSLSocket(String host, int port) {
		X509TrustManager xtm = new ProxyTrustManager();
		TrustManager mytm[] = { xtm };
		try {
			SSLContext ctx = SSLContext.getInstance("SSL", "SunJSSE");
			ctx.init(null, mytm, new java.security.SecureRandom());
			SSLSocketFactory factory = ctx.getSocketFactory();
			return factory.createSocket(host, port);
		} catch (Exception e) {
			throw new HttpProxyException(e);
		}
	}
}
