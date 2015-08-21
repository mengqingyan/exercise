/**
 * 
 */
package com.revencoft.httpconnection;

import java.io.IOException;

/**
 * @author mengqingyan
 * @version 
 * @param <T>
 * @param <S>
 */
public interface ConnectionAction<T, S> {

	/**
	 * no need to close the source manually
	 * @param response
	 * @param source must be guaranteed not null
	 * @return
	 */
	public abstract T action(S source) throws IOException;

}