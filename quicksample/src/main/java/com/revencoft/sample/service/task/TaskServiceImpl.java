/**
 * 
 */
package com.revencoft.sample.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revencoft.sample.dao.task.TaskDao;
import com.revencoft.sample.dao.task.TaskQueryParam;
import com.revencoft.sample.entity.Task;
import com.revencoft.sample.support.CustomQueryParams;

/**
 * @author mengqingyan
 * @version 
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;
	
	@Override
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

	@Override
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
	}

}
