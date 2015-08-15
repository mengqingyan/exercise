/**
 * 
 */
package com.revencoft.sample.service.task;

import java.util.List;

import com.revencoft.sample.entity.Task;

/**
 * @author mengqingyan
 * @version 
 */
public interface TaskService {

	/**
	 * @param userId
	 * @param search_LIKE_title
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 */
	List<Task> getUserTask(Long userId, String search_LIKE_title,
			int iDisplayStart, int iDisplayLength);

	/**
	 * @param userId
	 * @param search_LIKE_title
	 * @return
	 */
	int getUserTaskCount(Long userId, String search_LIKE_title);

}
