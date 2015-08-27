/**
 * 
 */
package com.revencoft.httpconnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mengqingyan
 * @version 
 */
public class JDKConnection {
	
	

	private static final Logger log = Logger.getLogger(JDKConnection.class.getSimpleName());
	
	/**
	 * 
	 * @param url
	 * @param action
	 * @param timeout s
	 * @return
	 * @throws IOException
	 */
	public static <T> T openUrl(String url, final int timeout, ActionWithInputStream<T> action) throws Exception {
		
		ConnectionPreProcess preConnect = new ConnectionPreProcess() {
			
			@Override
			public void process(HttpURLConnection connection) throws Exception {
				connection.setReadTimeout(timeout);
			}
		};
		return openUrl(url, preConnect , action);
	}
	
	
	public static <T> T openUrl(String url, ConnectionPreProcess preConnect, ActionWithInputStream<T> action) throws Exception {
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
			preConnect.process(connection);
			connection.connect();
			
			InputStream in = null;
			try {
				in = connection.getInputStream();
				return action.action(in);
			} catch (IOException e) {
				if(in == null) {
					log.log(Level.SEVERE, "open input stream from connection error.", e);
				} else {
					log.log(Level.SEVERE, "action executed error with inputstream!");
				}
				throw e;
			} finally {
				RevencoftIOUtils.closeStream(in);
			}
		} finally {
			connection.disconnect();
		}
	}
	
	/**
	 * 
	 * @param url
	 * @param action
	 * @param timeout s
	 * @return
	 * @throws IOException
	 */
	public static <T> T openUrl(String url, final int timeout, ActionWithConnection<T> action) throws Exception {
		
		ConnectionPreProcess preConnect = new ConnectionPreProcess() {
			
			@Override
			public void process(HttpURLConnection connection) throws Exception {
				connection.setReadTimeout(timeout * 1000);
			}
		};
		return openUrl(url, preConnect , action);
	}
	
	public static <T> T openUrl(String url, ConnectionPreProcess preConnect, ActionWithConnection<T> action) throws Exception {
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
			preConnect.process(connection);
			return action.action(connection);
		} finally {
			connection.disconnect();
		}
	}
	
	public interface ConnectionPreProcess {

		/**
		 * 对connection预处理
		 * @param connection
		 * @throws Exception 
		 */
		void process(HttpURLConnection connection) throws Exception;

	}

	public interface ActionWithInputStream<T> extends
			ConnectionAction<T, InputStream> {
	}

	public interface ActionWithConnection<T> extends
			ConnectionAction<T, HttpURLConnection> {

	}

}
