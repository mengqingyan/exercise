package com.revencoft.connection_proxy.request;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.revencoft.connection_proxy.exception.HttpProxyException;

/**
 * @author mengqingyan
 * 
 */
class HttpRequest implements Request {
	
	private static final String SPACE = " ";
	
	private Map<String, String> head;
	
	private byte[] datas;
	
	private String reqLine;

	private String method;

	private URL reqUrl;

	private List<String> headLines;
	
	public HttpRequest() {
		this.head = new HashMap<String, String>();
		this.datas = "".getBytes();
		this.headLines = new ArrayList<String>();
	}
	

	@Override
	public String getReqLine() {
		return this.reqLine;
	}
	

	public void setReqLine(String reqLine) {
		this.reqLine = reqLine;
		initReqInfo(reqLine);
	}
	
	public void addHeadLine(String line) {
		this.headLines.add(line);
	}


	/**
	 * @param reqLine
	 */
	private void initReqInfo(String reqLine) {
		StringTokenizer tokenizer = new StringTokenizer(reqLine, SPACE);
		if(tokenizer.countTokens() != 3) {
			throw new HttpProxyException("客户端请求错误");
		}
		
		this.method = tokenizer.nextToken();
		String url = tokenizer.nextToken();
//		if("CONNECT".equals(this.method)) {
//			url = Request.HTTPS + "://" + url;
//			this.method = GET;
//		}
		try {
			this.reqUrl = new URL(url);
		} catch (MalformedURLException e) {
			throw new HttpProxyException(e);
		}
	}


	public void setDatas(byte[] datas) {
		this.datas = datas;
	}
	
	public void setHeadAttribute(String key, String value) {
		this.head.put(key, value);
	}


	@Override
	public String getHost() {
		if(this.reqUrl == null) {
			return null;
		}
		if(!"".equals(reqUrl.getHost()))
			return this.reqUrl.getHost();
		String path = reqUrl.getPath();
		int ind = path.indexOf(":");
		if(ind > 0) {
			return path.substring(0, ind);
		}
		return path;
	}

	@Override
	public int getPort() {
		int port = this.reqUrl.getPort();
		if (port == -1) {
			if(Request.HTTP.equals(getProtocal())) {
				port = 80;
			} else if(Request.HTTPS.equals(getProtocal())) {
				port = 443;
			} else {
				throw new HttpProxyException("根据协议[" + getProtocal() + "]无法获取端口号！");
			}
		}
		return port;
	}

	@Override
	public String getMethod() {
		return this.method;
	}

	@Override
	public String getProtocal() {
		return this.reqUrl.getProtocol();
	}

	@Override
	public List<String> getHeadLines() {
		return this.headLines;
	}

	@Override
	public InputStream getReqStream() {
		return null;
	}

	@Override
	public byte[] getBodyBytes() {
		return datas;
	}

	@Override
	public byte[] getHeadBytes() {
		StringBuilder sb = new StringBuilder();
		int size = headLines.size();
		for(int i = 0; i < size; i++) {
			sb.append(headLines.get(i));
		}
		return sb.toString().getBytes();
	}


	@Override
	public String getPath() {
		return this.reqUrl.getPath();
	}

}
