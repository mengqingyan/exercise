/**
 * 
 */
package com.revencoft.sample.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.revencoft.sample.SpringTransactionTest;
import com.revencoft.sample.dao.account.UserDao;
import com.revencoft.sample.dao.task.TaskDao;
import com.revencoft.sample.entity.Task;
import com.revencoft.sample.entity.User;
import com.revencoft.sample.support.CustomQueryParams;
import com.revencoft.sample.support.QueryCondition;
import com.revencoft.sample.support.QueryCondition.Operation;

/**
 * @author mengqingyan
 * @version 
 */

@ContextConfiguration("/spring/application-context.xml")
public class UserDaoTest extends SpringTransactionTest {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TaskDao taskDao;
	
	
	@Test
	public void testFindUserByLoginName() {
		String loginName = "admin";
		//preparing
		User user = userDao.findUserByLoginName(loginName);
		
		System.out.println("user: " + user);
		//preparing
		User user2 = userDao.findUserByLoginName("mqyqingkong");
		
		System.out.println("user2: " + user2);
	}
	
	@Test
	public void testTaskDao() {
		CustomQueryParams params = new CustomQueryParams();
		QueryCondition cond = new QueryCondition("user_id", Operation.eq, String.valueOf(2));
		params.addQueryCondition(cond);
		List<Task> list1 = taskDao.queryByQParams(params );
		//修改sql的where拼接条件，preparedstatement会重新preparing;查询参数改变，也会重新preparing
//		params.addQueryCondition(new QueryCondition("title", Operation.like, "o"));
		System.out.println(list1);
		cond.setValue(String.valueOf(3));
		List<Task> list2 = taskDao.queryByQParams(params );
		System.out.println(list2);
	}
	
}
