/**
 * 
 */
package com.revencoft.sample.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.revencoft.sample.SpringContextTestCase;
import com.revencoft.sample.entity.Task;
import com.revencoft.sample.entity.User;
import com.revencoft.sample.service.task.TaskService;
import com.revencoft.sample.support.CustomQueryParams;
import com.revencoft.sample.support.QueryCondition;
import com.revencoft.sample.support.QueryCondition.Operation;

/**
 * @author mengqingyan
 * @version 
 */
@ContextConfiguration("/spring/application-context.xml")
public class TestTaskService extends SpringContextTestCase{

	@Autowired
	private TaskService taskService;
	
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
		List<Task> tasks = taskService.getEntityByQParams(params );
		System.out.println(tasks);
	}
}
