package com.revencoft.connection_proxy.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.revencoft.connection_proxy.request.Request;

/**
 * @author mengqingyan
 * 
 */
/*public class RequestReader extends Reader implements Request {
	
	private static final Logger log = Logger.getLogger(RequestReader.class);
	
	private static final int HEAD_LINE_NUM = 0;
	private static final int HOST_LINE_NUM = 1;
	
	private static final String HOST_LINE_PREFIX = "Host: ";
	private static final String SEPARATOR = ":";
	private static final String SPACE = " ";
	
	*//**
	 * @param in
	 *//*
	public RequestReader(InputStream in) {
		super(in);
		init();
	}
	
	private void init() {
		try {
			hasReadLines.add(HEAD_LINE_NUM, readLine());
			hasReadLines.add(HOST_LINE_NUM, readLine());
		} catch (IOException e) {
			log.error("构建RequestReader失败！");
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getHost() {
		String hostLine = hasReadLines.get(HOST_LINE_NUM);
		String hostAndPort = StringUtils.substringAfter(hostLine, HOST_LINE_PREFIX);
		if(hostAndPort.indexOf(SEPARATOR) > 0) {
			return StringUtils.substringBefore(hostAndPort, SEPARATOR);
		}
		return StringUtils.substringBefore(hostAndPort, RET_NEXT_LINE);
	}

	@Override
	public int getPort() {
		String hostLine = hasReadLines.get(HOST_LINE_NUM);
		String hostAndPort = StringUtils.substringAfter(hostLine, HOST_LINE_PREFIX);
		if(hostAndPort.indexOf(SEPARATOR) > 0) {
			String portStr = StringUtils.substringBetween(hostAndPort, SEPARATOR, HOST_LINE_PREFIX);
			return Integer.parseInt(portStr);
		}
		return 80;
	}

	@Override
	public String getMethod() {
		String headLine = hasReadLines.get(HEAD_LINE_NUM);
		return StringUtils.substringBefore(headLine, SPACE);
	}

	@Override
	public String getProtocal() {
		String headLine = hasReadLines.get(HEAD_LINE_NUM);
		return StringUtils.substringBetween(headLine, SPACE, SEPARATOR);
	}

	@Override
	public String getReqLine() {
		String headLine = hasReadLines.get(HEAD_LINE_NUM);
		return StringUtils.substringBefore(headLine, RET_NEXT_LINE);
	}

	@Override
	public List<String> getHeadLines() {
		return gethasReadLines();
	}

	@Override
	public InputStream getReqStream() {
		return in;
	}
	

}*/
