package com.revencoft.connection_proxy.processor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.revencoft.connection_proxy.exception.HttpProxyException;
import com.revencoft.connection_proxy.util.SocketUtil;

/**
 * @author mengqingyan
 * 
 */
public class CopyStreamTask implements Runnable {

	private static final Logger log = Logger.getLogger(CopyStreamTask.class);
	
	private final InputStream srcIn;
	private final OutputStream destOut;
	
	public CopyStreamTask(InputStream srcIn, OutputStream destOut) {
		this.srcIn = srcIn;
		this.destOut = destOut;
	}
	
	
	@Override
	public void run() {
		try {
			SocketUtil.copyServerResponse(srcIn, destOut);
		} catch (IOException e) {
			SocketUtil.closeStream(srcIn, destOut);
			log.error(e.getMessage());
		}
	}
}
