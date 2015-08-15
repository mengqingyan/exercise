/**
 * 
 */
package com.revencoft.sample.controller.account;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.revencoft.sample.entity.User;
import com.revencoft.sample.service.account.AccountService;

/**
 * @author mengqingyan
 * @version 
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public String toRegister() {
		return "account/register";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid User user, RedirectAttributes redirectAttributes) {
		accountService.registerUser(user);
		redirectAttributes.addAttribute("username", user.getLoginName());
		return "redirect:/login";
	}
	
	@RequestMapping(value="/checkLoginName", method = RequestMethod.GET)
	@ResponseBody
	public String checkLoginName(@RequestParam String loginName) {
		User user = accountService.findUserByLoginName(loginName);
		if(user == null) {
			return "true";
		}
		return "false";
	}
	
}
