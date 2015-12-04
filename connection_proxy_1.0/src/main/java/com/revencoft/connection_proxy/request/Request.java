package com.revencoft.connection_proxy.request;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revencoft.connection_proxy.exception.HttpProxyException;

/**
 * @author mengqingyan
 * 
 */
public interface Request {
	
	String RET_NEXT_LINE = "\r\n";
	String POST = "POST";
	String GET = "GET";
	String HTTP = "http";
	String HTTPS = "https";
	String SEPARATOR = ":";
	String CONNECT = "CONNECT";

	String getReqLine();
	
	String getHost();
	
	int getPort();
	
	String getMethod();
	
	String getProtocal();
	
	/**
	 * 获取代理服务器已处理的行
	 * @return
	 */
	List<String> getHeadLines();
	
	InputStream getReqStream();
	
	String getPath();
	
	byte[] getBodyBytes();
	
	byte[] getHeadBytes();
	
	public static final class Builder {
		
		private static final Logger log = Logger.getLogger(Builder.class);
		
		private static final String SPACE = " ";
		
		
		public static Request buildRequest(InputStream in) {
			
			HttpRequest req = new HttpRequest();
			
			Scanner scan = new Scanner(in);
			
//			while(scan.hasNextLine()) {
//				String line = scan.nextLine();
//				System.out.println(line);
//			}
//			
			boolean statusLine = true;
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				log.debug(line);
				if("".equals(line)) {
					break;
				}
				
				if(statusLine) {
					statusLine = false;
					
					if(line.startsWith(CONNECT)) {
//						line = GET + SPACE + req.getPath() + "/" + SPACE + "HTTP/1.1";
						String[] split = line.split(SPACE);
						if(split.length != 3) {
							throw new HttpProxyException("获取请求头失败！");
						}
						line = split[0] + SPACE + HTTPS + "://" + split[1] + SPACE + split[2];
					}
					req.setReqLine(line);
				} else {
					String[] kvs = line.split(SEPARATOR + SPACE);
					req.setHeadAttribute(kvs[0], kvs[1]);
				}
				req.addHeadLine(line + RET_NEXT_LINE);
			}
			req.addHeadLine(RET_NEXT_LINE);
			
			if(!POST.equals(req.getMethod())) {
				return req;
			}
			
			//获取POST数据
			StringBuilder sb = new StringBuilder();
			
			while(scan.hasNextLine()) {
				String postLine = scan.nextLine();
				if(!"".equals(postLine)) {
					sb.append(postLine).append(RET_NEXT_LINE);
				}
			}
			
//				req.addProcessedLine(RET_NEXT_LINE);
			if(sb.length() > 0) {
				sb.append(RET_NEXT_LINE);
				req.setDatas(sb.toString().getBytes());
			}
			return req;
			
		}
	}
}
