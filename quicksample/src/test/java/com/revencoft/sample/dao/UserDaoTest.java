/**
 * 
 */
package com.revencoft.sample.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.revencoft.sample.SpringTransactionTest;
import com.revencoft.sample.dao.account.UserDao;
import com.revencoft.sample.entity.User;

/**
 * @author mengqingyan
 * @version 
 */

@ContextConfiguration("/spring/application-context.xml")
public class UserDaoTest extends SpringTransactionTest {

	@Autowired
	private UserDao userDao;
	
	
	@Test
	public void testFindUserByLoginName() {
		String loginName = "admin";
		
		User user = userDao.findUserByLoginName(loginName);
		
		System.out.println("user: " + user);
	}
	
}
