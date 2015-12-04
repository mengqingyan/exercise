package com.revencoft.connection_proxy.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengqingyan
 * 
 */
public abstract class Reader {

	public static final int RETURN = 13;
	public static final int NEXT_LINE = 10;
	
	protected final InputStream in;
	
	protected final List<String> hasReadLines;
	
	public Reader(InputStream in) {
		this.in = in;
		hasReadLines = new ArrayList<String>();
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
	
	public List<String> gethasReadLines() {
		return this.hasReadLines;
	}
	
	public InputStream getIn() {
		return in;
	}
	
}
