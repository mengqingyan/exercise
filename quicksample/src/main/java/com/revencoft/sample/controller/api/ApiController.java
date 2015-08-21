/**
 * 
 */
package com.revencoft.sample.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author mengqingyan
 * @version 
 */
@Controller
@RequestMapping("/api")
public class ApiController {

	@RequestMapping(method=RequestMethod.GET)
	public String api() {
		return "api/list";
	}
}
