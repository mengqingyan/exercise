/**
 * 
 */
package com.revencoft.sample.exception;

/**
 * @author mengqingyan
 * @version 
 */
public class SqlInjectionException extends RuntimeException {

	private final String errorMsg;

	/**
	 * @param format
	 */
	public SqlInjectionException(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	

	public String getErrorMsg() {
		return errorMsg;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
