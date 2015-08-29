/**
 * 
 */
package com.revencoft.sample.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.revencoft.basic_access.param.CustomQueryParams;
import com.revencoft.basic_access.param.QueryCondition;
import com.revencoft.basic_access.param.QueryCondition.Operation;
import com.revencoft.basic_access.test.SpringContextTestCase;
import com.revencoft.sample.entity.Task;
import com.revencoft.sample.entity.User;
import com.revencoft.sample.service.account.AccountService;
import com.revencoft.sample.service.task.TaskService;

/**
 * @author mengqingyan
 * @version 
 */
@ContextConfiguration("/spring/application-context.xml")
public class TestTaskService extends SpringContextTestCase{

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private AccountService accountService;
	
	@Test
	public void testSaveTask() {
		Task task = new Task();
		task.setTitle("test");
		task.setDescription("test taskService");
		task.setUser(new User(3L));
		taskService.saveEntity(task );
	}
	
	@Test
	public void testQueryTaskByUserId() {
		CustomQueryParams params = new CustomQueryParams();
		params.addQueryCondition(new QueryCondition("user_id", Operation.eq, String.valueOf(2)));
//		List<Task> tasks = taskService.getEntityByQParams(params );
//		if(tasks != null) {
//			for (Task task : tasks) {
//				System.out.println(task);
//			}
//		}
		//两次执行，被不同的事物包含，（无必然联系）statement会preparing两次
		taskService.getEntityByQParams(params );
		taskService.getEntityByQParams(params );
	}
	
	@Test
	public void testGetUserByLoginName() {
		String loginName = "admin";
		//两次执行，被不同的事物包含，所以statement会preparing两次
		accountService.findUserByLoginName(loginName );
		accountService.findUserByLoginName(loginName );
	}
}
