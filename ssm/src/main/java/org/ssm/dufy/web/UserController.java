package org.ssm.dufy.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.ssm.dufy.entity.User;
import org.ssm.dufy.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/showname")
	public String showUserName(@RequestParam(value="uid", required = false) String uid,HttpServletRequest request,Model model){
		if(StringUtils.isEmpty(uid)) {
			return "error";
		}
		User user = userService.getUserById(uid);
		if(user != null){
			request.setAttribute("name", user.getUserName());
			model.addAttribute("mame", user.getUserName());
			return "showName";
		}
		request.setAttribute("error", "没有找到该用户！");
		return "error";
	}
	
	@RequestMapping(value="/gotoAddUser")
	public String gotoAddUser(String uid,HttpServletRequest request,Model model){
		
		return "gotoAddUser";
	}
	
	@RequestMapping(value="/addUser")
	public String addUser(String uid,HttpServletRequest request,Model model){
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String age = request.getParameter("age");
		User user = new User();
		user.setAge(Integer.valueOf(age));
		user.setUserName(userName);
		user.setPassword(password);
		userService.addUser(user);
		return "userList";
	}
}
