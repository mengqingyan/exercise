package com.revencoft.connection_proxy;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void testN() {
		byte[] bytes = "\r\n".getBytes();

		for (int i = 0; i < bytes.length; i++) {
			System.out.println(bytes[i]);
		}
	}

	@Test
	public void testByte2Char() {
		System.out.println((char) 13);
		System.out.println((char) 10);
		System.out.println("haha");
	}

	@Test
	public void testBytesCount() {
		String s = "<html>\r\n" + "<head><title>302 Found</title></head>\r\n"
				+ "<body bgcolor='white'>\r\n"
				+ "<center><h1>302 Found</h1></center>\r\n"
				+ "<hr><center>pr-nginx_1-0-253_BRANCH Branch\r\n"
				+ "Time : Mon Nov 16 15:52:37 CST 2015</center>\r\n" + "</body>\r\n"
				+ "</html>\r\n";
		System.out.println(s.getBytes().length);

	}
	
	@Test
	public void testParseInt() {
		String s = "1093\r\n";
		String replace = StringUtils.replace(s, "\r\n", "");
		System.out.println(Integer.parseInt(replace));
	}
	
	@Test
	public void testParseHexInt() {
		String s = "b1\r\n";
		String replace = StringUtils.replace(s, "\r\n", "");
		System.out.println(Integer.parseInt(replace, 16));
	}
	
	@Test
	public void testStringBuilder() {
		System.out.println(new StringBuilder().toString());
	}
	
}
