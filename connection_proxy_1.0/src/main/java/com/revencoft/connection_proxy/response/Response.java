package com.revencoft.connection_proxy.response;

import java.io.IOException;
import java.util.Map;

/**
 * 
 * @author mengqingyan
 *
 */
public interface Response {
	
	String PROXY_CONNECT_OK = "HTTP/1.1 200 Connection established\r\n";
	
	String RET_NEXT_LINE = "\r\n";

	public String getStatusLine() throws IOException;
	
	public String getHeadValue(String name) throws IOException;
	
	public Map<String, String> getResponseHead() throws IOException;
	
	public boolean isChunked() throws IOException;
	
	public int getContentLength() throws IOException;
	
}
