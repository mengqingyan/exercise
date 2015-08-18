/**
 * 
 */
package com.revencoft.sample.dao.task;

import org.springframework.stereotype.Repository;

import com.revencoft.sample.dao.BaseDao;
import com.revencoft.sample.dao.MyBatisRepository;
import com.revencoft.sample.entity.Task;

/**
 * @author mengqingyan
 * @version 
 */
@MyBatisRepository
@Repository
public interface TaskDao extends BaseDao<Task> {

	/**
	 * @param taskQueryParam
	 * @return
	 */
//	List<Task> getUserTask(TaskQueryParam taskQueryParam);

	/**
	 * @param userId
	 * @param search_LIKE_title
	 * @return
	 */
//	int getUserTaskCount(TaskQueryParam taskQueryParam);

//	void save(Task task);

//	void deleteById(int taskId);

//	List<Task> getUserTask2(CustomQueryParams qParams);

//	int getUserTaskCount2(CustomQueryParams qParams);
}
