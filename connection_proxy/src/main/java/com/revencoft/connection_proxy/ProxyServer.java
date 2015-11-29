package com.revencoft.connection_proxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.revencoft.connection_proxy.processor.ProxyProcessor;

/**
 * 
 * @author mengqingyan
 *
 */
public class ProxyServer {
	
	public static volatile boolean AGAIN = true;

	public static void main(String[] args) throws IOException {
		
		ExecutorService threadPool = Executors.newFixedThreadPool(200);
		ServerSocket server = new ServerSocket(8888);
		while(AGAIN) {
			Socket cSocket = server.accept();
			
			threadPool.execute(new ProxyProcessor(cSocket));
		}
		
		server.close();
		
	}
}
