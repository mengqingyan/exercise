package com.revencoft.connection_proxy.exception;

/**
 * @author mengqingyan
 * 
 */
public class HttpProxyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HttpProxyException() {
		super();
	}

	public HttpProxyException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpProxyException(String message) {
		super(message);
	}

	/**
	 * @param e
	 */
	public HttpProxyException(Throwable e) {
		super(e);
	}

}
