package com.revencoft.connection_proxy.processor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;

import com.revencoft.connection_proxy.exception.HttpProxyException;
import com.revencoft.connection_proxy.request.Request;
import com.revencoft.connection_proxy.trust.ProxyTrustManager;
import com.revencoft.connection_proxy.util.SocketUtil;

/**
 * 
 * @author mengqingyan
 *
 */
public class ProxyProcessor implements Runnable {
	
	private final Socket cSocket;

	private static final Logger log = Logger.getLogger(ProxyProcessor.class.getSimpleName());
	
	private String headLine = null;

	public ProxyProcessor(Socket cSocket) {
		this.cSocket = cSocket;
		
	}
	
	public void run() {
		
		log.debug(Thread.currentThread().getName());
		
		InputStream cIn = null, sIn = null;
		OutputStream cOut = null, sOut = null;
		Socket sSocket = null;
		try {
			this.cSocket.setKeepAlive(true);
			cIn = this.cSocket.getInputStream();
			cOut = this.cSocket.getOutputStream();
			
			Request req = Request.Builder.buildRequest(cIn);
			
			String host = req.getHost();
			if(null == host) {
				return;
			}
			
			headLine = req.getReqLine();
			if(log.isInfoEnabled()) {
				log.info(headLine);
			}
			
			sSocket = buildSocket(req);
			sSocket.setSoTimeout(15000);
			
			sOut = sSocket.getOutputStream();
			sIn = sSocket.getInputStream();
			
			
			Thread resp = new Thread(new CopyStreamTask(sIn, cOut));
			resp.start();
			
			if(Request.CONNECT.equals(req.getMethod())) {
				SocketUtil.response2Client(cOut, req);
				SocketUtil.copy(cIn, sOut, -1);
				
			} else {
				SocketUtil.transferClientReq2Server(sOut, req);
//				sOut.write(" ".getBytes());
//				SocketUtil.copyServerResponse(sIn, cOut);
			}
			resp.join();
//			log.debug("===========begin res=============");
//			SocketUtil.copyServerResponse(sIn, cOut);
//			log.debug("===========res over！============");
			
		} catch (Exception e) {
			log.error("falied for: " + headLine, e);
		} finally {
			SocketUtil.closeStream(cIn, sIn, cOut, sOut);
			SocketUtil.closeSocket(this.cSocket, sSocket);
		}

	}

	/**
	 * @param req
	 * @return
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	private Socket buildSocket(Request req) throws IOException {
		String host = req.getHost();
		int port = req.getPort();
		String protocal = req.getProtocal();
		return new Socket(InetAddress.getByName(host), port);
//		if(Request.HTTP.equals(protocal)) {
//			return new Socket(InetAddress.getByName(host), port);
//		} else if(Request.HTTPS.equals(protocal)) {
//			
//			 X509TrustManager xtm = new ProxyTrustManager();
//			    TrustManager mytm[] = { xtm };
//			    try {
//			    	SSLContext ctx = SSLContext.getInstance("SSL", "SunJSSE");
//					ctx.init(null, mytm, new java.security.SecureRandom());
//					SSLSocketFactory factory = ctx.getSocketFactory();
//					return factory.createSocket(host, port);
//				} catch (Exception e) {
//					throw new HttpProxyException(e);
//				}
//		}
//		throw new HttpProxyException("根据" + protocal + "无法获取socket！");
	}

}
