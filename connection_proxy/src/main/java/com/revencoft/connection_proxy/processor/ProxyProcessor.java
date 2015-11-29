package com.revencoft.connection_proxy.processor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.revencoft.connection_proxy.reader.ResponseReader;
import com.revencoft.connection_proxy.util.SocketUtil;

/**
 * 
 * @author mengqingyan
 *
 */
public class ProxyProcessor implements Runnable {
	
	private final Socket cSocket;

	private static final Logger log = Logger.getLogger(ProxyProcessor.class.getSimpleName());

	private static final String POST = "POST";
	
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
			this.cSocket.setSoTimeout(1500);
			cIn = this.cSocket.getInputStream();
			cOut = this.cSocket.getOutputStream();
			
			List<String> reqLines = SocketUtil.getReqLines(cIn);
			if(reqLines.size() == 0) {
				log.debug("skipped for empty request!");
				return;
			}
			
			headLine = reqLines.get(0);
			
			String host = SocketUtil.getHost(headLine);
			int port = SocketUtil.getPort(headLine);
			String method = SocketUtil.getMethod(headLine);
			if(POST.equals(method)) {
				log.debug("skipped for post!");
				return;
			}
			if(log.isInfoEnabled()) {
				log.info(headLine.replaceFirst("\r\n", ""));
			}
			
			sSocket = new Socket(InetAddress.getByName(host), port);
			sSocket.setSoTimeout(20000);
			
			sOut = sSocket.getOutputStream();
			sIn = sSocket.getInputStream();
			String wrapedHeadLine = SocketUtil.wrapHeadLine(headLine, host);
			
			SocketUtil.writeServerReqLines(sOut, reqLines, wrapedHeadLine);
			ResponseReader responseReader = new ResponseReader(sIn);
			Map<String, String> responseHead = responseReader.getResponseHead();
			
			log.debug(responseHead);
			if(responseHead.containsKey(ResponseReader.STATUS_LINE)) {
				SocketUtil.writeClientResponseLines(cOut, responseHead);
			}
			
			boolean chunked = responseReader.isChunked(); 
			log.debug("===========begin res=============");
			if(!chunked) {
				SocketUtil.copy(sIn, cOut, responseReader.getContentLength());
			} else {
				SocketUtil.copyChunked(responseReader, cOut);
			}
			log.debug("===========res over！============");
			
		} catch (IOException e) {
			log.error("falied for: " + headLine, e);
		} finally {
			SocketUtil.closeStream(cIn, sIn, cOut, sOut);
			SocketUtil.closeSocket(this.cSocket, sSocket);
		}

	}
	

	



	

	

//	private String getHeadLine(InputStream cIn) throws IOException {
//		BufferedReader reader = new BufferedReader(new InputStreamReader(cIn));
//		return reader.readLine();
//	}

	
	
	
	
	

}
