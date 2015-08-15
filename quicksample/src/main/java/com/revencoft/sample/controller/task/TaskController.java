/**
 * 
 */
package com.revencoft.sample.controller.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revencoft.sample.entity.Task;
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
	public Map<String, Object> list(@RequestParam String search_LIKE_title,
			@RequestParam String sEcho,
			@RequestParam int iDisplayStart,
			@RequestParam int iDisplayLength) {
		
		Map<String, Object> datas = new HashMap<String, Object>();
		
		Long userId = getCurrentUserId();
		
		List<Task> taskList = taskService.getUserTask(userId, search_LIKE_title, iDisplayStart, iDisplayLength);
		int count = taskService.getUserTaskCount(userId, search_LIKE_title);
		
		datas.put("sEcho", sEcho);
		datas.put("iTotalRecords", count);
		datas.put("iTotalDisplayRecords", count);
		datas.put("aaData", taskList);
		return datas;
	}

	/**
	 * @return
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.getId();
	}
	
}
