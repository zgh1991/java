package org.ssm.dufy.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ssm.dufy.entity.TUser;

public class IUserServiceTest2 {

	
	public static void main(String[] args) {
		ApplicationContext application = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserService uService = (IUserService) application.getBean("userService");
		TUser user = uService.getUserById("1");
		System.out.println(user.getUserName());
	}
}
