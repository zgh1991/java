package org.ssm.dufy.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.ssm.dufy.entity.TUser;
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
		TUser user = userService.getUserById(uid);
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
		TUser user = new TUser();
		user.setAge(Integer.valueOf(age));
		user.setUserName(userName);
		user.setPassword(password);
		user.setCreateTime(new Date());
		userService.addUser(user);
		List<TUser> userList = userService.selectAllUser();
		model.addAttribute("userList", userList);
		return "userList";
	}
	
	@RequestMapping(value="/userList")
	public String userList(HttpServletRequest request,Model model){
		return "userList";
	}
}
