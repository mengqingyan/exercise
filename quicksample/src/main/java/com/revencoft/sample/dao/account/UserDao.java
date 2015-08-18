/**
 * 
 */
package com.revencoft.sample.dao.account;

import org.springframework.stereotype.Repository;

import com.revencoft.sample.dao.MyBatisRepository;
import com.revencoft.sample.entity.User;

/**
 * @author mengqingyan
 * @version 
 */

@MyBatisRepository
@Repository
public interface UserDao {

	/**
	 * @param loginName
	 * @return
	 */
	User findUserByLoginName(String loginName);
	
	void insert(User user);

}
