/**
 * 
 */
package com.revencoft.sample.utils;


/**
 * @author mengqingyan
 * @version 
 */
public abstract class Exceptions {

	/**
	 * @param e
	 * @return
	 */
	public static RuntimeException unchecked(Exception e) {
		if(e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException(e);
	}

	
}
