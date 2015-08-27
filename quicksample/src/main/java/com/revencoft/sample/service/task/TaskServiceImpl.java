/**
 * 
 */
package com.revencoft.sample.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revencoft.basic_access.dao.BaseDao;
import com.revencoft.basic_access.param.CustomQueryParams;
import com.revencoft.basic_access.service.BaseServiceImpl;
import com.revencoft.sample.dao.task.TaskDao;
import com.revencoft.sample.entity.PageEntity;
import com.revencoft.sample.entity.Task;

/**
 * @author mengqingyan
 * @version 
 */
@Service
//@CacheConfig(cacheNames="data")
@Transactional
public class TaskServiceImpl extends BaseServiceImpl<Task> implements TaskService {

	@Autowired
	private TaskDao taskDao;
	
	/*@Override
	@Transactional(readOnly=true)
	public List<Task> getUserTask(Long userId, String search_LIKE_title,
			int iDisplayStart, int iDisplayLength) {
		TaskQueryParam param = new TaskQueryParam(userId,
				search_LIKE_title);
		param.setStart(iDisplayStart);
		param.setPageSize(iDisplayLength);
		return taskDao.getUserTask(param);
	}

	@Override
	@Transactional(readOnly=true)
	public int getUserTaskCount(Long userId, String search_LIKE_title) {
		return taskDao.getUserTaskCount(new TaskQueryParam(userId, search_LIKE_title));
	}
*/
/*	@Override
	public void save(Task task) {
		taskDao.save(task);
	}

	@Override
	public void deleteTaskById(int taskId) {
		taskDao.deleteById(taskId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Task> getUserTask(CustomQueryParams qParams) {
		return taskDao.getUserTask2(qParams);
	}

	@Override
	@Transactional(readOnly=true)
	public int getUserTaskCount(CustomQueryParams qParams) {
		return taskDao.getUserTaskCount2(qParams);
	}*/

	@Override
	protected BaseDao<Task> getBaseDao() {
		return taskDao;
	}

	@Override
//	@Cacheable
	@Transactional(readOnly=true)
	public PageEntity<Task> getTaskPageAndCount(CustomQueryParams qParams) {
		log.info("begin getting task page and count...");
		List<Task> tasks = getEntityByQParams(qParams);
		int taskCount = getEntityCountByQParams(qParams);
		return new PageEntity<Task>(taskCount, tasks);
//		List<Task> tasks = taskDao.queryByQParams(qParams);
//		int taskCount = taskDao.queryCountByQParams(qParams);
//		return new PageEntity<Task>(taskCount, tasks);
	}

}
