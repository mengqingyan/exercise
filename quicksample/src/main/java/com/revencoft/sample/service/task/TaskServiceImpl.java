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
		
		return taskDao.getUserTask(new TaskQueryParam(userId,
				search_LIKE_title, iDisplayStart, iDisplayLength));
	}

	@Override
	@Transactional(readOnly=true)
	public int getUserTaskCount(Long userId, String search_LIKE_title) {
		return taskDao.getUserTaskCount(new TaskQueryParam(userId, search_LIKE_title, null, null));
	}

}
