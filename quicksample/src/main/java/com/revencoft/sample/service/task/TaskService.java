/**
 * 
 */
package com.revencoft.sample.service.task;

import com.revencoft.sample.entity.PageEntity;
import com.revencoft.sample.entity.Task;
import com.revencoft.sample.service.BaseService;
import com.revencoft.sample.support.CustomQueryParams;

/**
 * @author mengqingyan
 * @version 
 */
public interface TaskService extends BaseService<Task> {

	/**
	 * @param qParams
	 */
	PageEntity<Task> getTaskPageAndCount(CustomQueryParams qParams);

	/**
	 * @param userId
	 * @param search_LIKE_title
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 */
//	List<Task> getUserTask(Long userId, String search_LIKE_title,
//			int iDisplayStart, int iDisplayLength);

	/**
	 * @param userId
	 * @param search_LIKE_title
	 * @return
	 */
//	int getUserTaskCount(Long userId, String search_LIKE_title);

	/*void save(Task task);

	void deleteTaskById(int taskId);

	List<Task> getUserTask(CustomQueryParams qParams);

	int getUserTaskCount(CustomQueryParams qParams);*/

}
