/**
 * 
 */
package com.revencoft.sample.dao.account;

import com.revencoft.basic_access.dao.BaseDao;
import com.revencoft.basic_access.dao.MyBatisRepository;
import com.revencoft.sample.entity.User;

/**
 * @author mengqingyan
 * @version 
 */

@MyBatisRepository
public interface UserDao extends BaseDao<User>{

	/**
	 * @param loginName
	 * @return
	 */
	User findUserByLoginName(String loginName);
	
	void insert(User user);
	
	User findUserById(int id);

}
