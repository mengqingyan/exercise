/**
 * 
 */
package com.revencoft.basic_access.listener;

import javax.servlet.http.HttpSession;

/**
 * 清除session相关缓存
 * @author mengqingyan
 * @version 
 */
public interface SessionContextRefreshor {

	
	/**
	 * 用于session失效时，清除session相关缓存
	 * @param session 
	 */
	void refresh(HttpSession session);
}
