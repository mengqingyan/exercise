/**
 * 
 */
package com.revencoft.sample.dao.task;

import java.util.List;

import com.revencoft.sample.dao.MyBatisRepository;
import com.revencoft.sample.entity.Task;

/**
 * @author mengqingyan
 * @version 
 */
@MyBatisRepository
public interface TaskDao {

	/**
	 * @param taskQueryParam
	 * @return
	 */
	List<Task> getUserTask(TaskQueryParam taskQueryParam);

	/**
	 * @param userId
	 * @param search_LIKE_title
	 * @return
	 */
	int getUserTaskCount(TaskQueryParam taskQueryParam);
}
