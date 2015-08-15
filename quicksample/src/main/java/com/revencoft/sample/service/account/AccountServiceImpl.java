/**
 * 
 */
package com.revencoft.sample.service.account;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revencoft.sample.constant.Role;
import com.revencoft.sample.dao.account.UserDao;
import com.revencoft.sample.entity.User;
import com.revencoft.sample.utils.Digests;
import com.revencoft.sample.utils.Encodes;

/**
 * @author mengqingyan
 * @version 
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private static final int SALT_SIZE = 8;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional(readOnly=true)
	public User findUserByLoginName(String loginName) {
		
		return userDao.findUserByLoginName(loginName);
	}

	@Override
	public void registerUser(User user) {
		encryptPassword(user);
		user.setRoles(Role.user.toString());
		user.setRegisterDate(new Date());
		userDao.insert(user);
	}

	/**
	 * @param user
	 */
	private void encryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));
		byte[] sha1Pass = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_ITERATIONS);
		user.setPassword(Encodes.encodeHex(sha1Pass));
	}

}
