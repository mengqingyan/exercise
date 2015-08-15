/**
 * 
 */
package com.revencoft.sample.service.account;

import com.revencoft.sample.entity.User;

/**
 * @author mengqingyan
 * @version 
 */
public interface AccountService {

	String HASH_ALGORITHM = "SHA-1";
	int HASH_ITERATIONS = 1024;
	/**
	 * @param username
	 * @return
	 */
	User findUserByLoginName(String loginName);
	/**
	 * @param user
	 */
	void registerUser(User user);

}
