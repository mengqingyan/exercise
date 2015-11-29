package com.revencoft.connection_proxy.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author mengqingyan
 *
 */
public class ResponseReader {

	private final InputStream in;

	private static final int RETURN = 13;
	private static final int NEXT_LINE = 10;

	public static final String STATUS_LINE = "statusLine";
	
	public static final String CONTENT_LENGTH = "Content-Length";
	public static final String TRANSFER_ENCODING = "Transfer-Encoding";
	public static final String CHUNKED = "chunked";
	
	public static final String RET_NEXT_LINE = "\r\n";
	
	private Map<String, String> headMap;

	// private StringBuilder bufferedLines;

	public ResponseReader(InputStream in) {
		this.in = in;
		// bufferedLines = new StringBuilder();
	}

	public String readLine() throws IOException {
		StringBuilder sb = new StringBuilder();
		int temp;
		boolean beginBreak = false;
		while (true) {
			temp = in.read();
			if(temp == -1) {
				break;
			}
			sb.append((char) temp);
			if (!beginBreak && (temp == RETURN)) {
				beginBreak = true;
				continue;
			}

			if (beginBreak && (temp == NEXT_LINE)) {
				break;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 获取所有响应头
	 * @return
	 * @throws IOException
	 */
	public Map<String, String> getResponseHead() throws IOException {
		if(this.headMap == null) {
			this.headMap = getResponseHeadMap();
		}
		return this.headMap;
	}
	
	/**
	 * 获取状态行
	 * @return
	 * @throws IOException
	 */
	public String getStatusLine() throws IOException {
		return getResponseHead().get(STATUS_LINE);
	}
	
	/**
	 * 是否分块
	 * @return
	 * @throws IOException
	 */
	public boolean isChunked() throws IOException {
		return (CHUNKED + RET_NEXT_LINE).equals(getResponseHead().get(TRANSFER_ENCODING));
	}
	
	/**
	 * 获取数据长度
	 * @return
	 * @throws IOException
	 */
	public int getContentLength() throws IOException {
		String length = getResponseHead().get(CONTENT_LENGTH);
		
		if(length == null) {
			return -1;
		}
		return parseStrInt(length, 10);
	}
	
	public static int parseStrInt(String webLine, int radix) {
		if(StringUtils.isBlank(webLine)) {
			return 0;
		}
		return Integer.parseInt(StringUtils.replace(webLine, RET_NEXT_LINE, ""), radix);
	}
	

	private Map<String, String> getResponseHeadMap() throws IOException {

		Map<String, String> res = new HashMap<String, String>();

		boolean statusLine = true;
		String temp = null;
		while (true) {
			temp = readLine();
			if (StringUtils.isBlank(temp)) {
				break;
			}
			if (statusLine) {
				statusLine = false;
				res.put(STATUS_LINE, temp);
			} else {
				res.put(StringUtils.substringBefore(temp, ":"),
						StringUtils.substringAfter(temp, ": "));
			}
		}
		return res;
	}

	public InputStream getIn() {
		return in;
	}

}
