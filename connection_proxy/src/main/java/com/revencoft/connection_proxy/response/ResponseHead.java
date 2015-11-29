package com.revencoft.connection_proxy.response;

import java.util.Map;

/**
 * 
 * @author mengqingyan
 *
 */
public class ResponseHead {

	public static final String CONTENT_LENGTH = "Content-Length";
	private String statusLine;
	
	private Map<String, String> keys;

	public String getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}
	
	public void putHeadKey(String name, String value) {
		this.keys.put(name, name);
	}
	
	public String getHeadValue(String name) {
		return this.keys.get(name);
	}
	
}
