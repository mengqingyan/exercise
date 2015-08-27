/**
 * 
 */
package com.revencoft.sample.controller.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revencoft.basic_access.param.CustomQueryParams;
import com.revencoft.basic_access.param.QueryCondition;
import com.revencoft.basic_access.param.QueryCondition.Operation;
import com.revencoft.basic_access.resolver.QueryParamCombine;
import com.revencoft.sample.entity.PageEntity;
import com.revencoft.sample.entity.Task;
import com.revencoft.sample.entity.User;
import com.revencoft.sample.service.task.TaskService;
import com.revencoft.sample.shiro.ShiroDbRealm.ShiroUser;

/**
 * @author mengqingyan
 * @version 
 */
@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;

	@RequestMapping(method=RequestMethod.GET)
	public String toList() {
		return "task/list";
	}
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(@QueryParamCombine CustomQueryParams qParams) {
		
		Map<String, Object> datas = new HashMap<String, Object>();
		
		List<String> sortableColumns = new ArrayList<String>();
		sortableColumns.add("title");
		qParams.setSortableColumns(sortableColumns);
		
		Long userId = getCurrentUserId();
		qParams.addQueryCondition(new QueryCondition("user_id", Operation.eq, String.valueOf(userId)));
		
		PageEntity<Task> taskPageAndCount = taskService.getTaskPageAndCount(qParams);
		
		datas.put("sEcho", qParams.getsEcho());
		datas.put("iTotalRecords", taskPageAndCount.getCount());
		datas.put("iTotalDisplayRecords", taskPageAndCount.getCount());
		datas.put("aaData", taskPageAndCount.getEntities());
		return datas;
	}
	
	@RequestMapping(value="create", method=RequestMethod.GET)
	public String toCreate() {
		return "task/taskForm";
	}
	
	@RequestMapping(value="create", method=RequestMethod.POST)
	public String doCreate(@Valid Task task) {
		Long userId = getCurrentUserId();
		User user = new User(userId);
		task.setUser(user);
		taskService.saveEntity(task);/*save(task)*/;
		
		return "redirect:/task";
	}
	
	@RequestMapping(value="delete/{taskId}", method=RequestMethod.GET)
	public String delete(@PathVariable int taskId) {
//		taskService.deleteEntityById(taskId)/*deleteTaskById(taskId)*/;
		
		CustomQueryParams params = new CustomQueryParams();
		params.addQueryCondition(new QueryCondition("id", Operation.eq, String.valueOf(taskId)));
		taskService.deleteEntityByQParams(params );
		return "redirect:/task";
	}
	

	/**
	 * @return
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.getId();
	}
	
}
