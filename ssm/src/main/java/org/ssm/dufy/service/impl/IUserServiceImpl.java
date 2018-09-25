package org.ssm.dufy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssm.dufy.dao.IUserDao;
import org.ssm.dufy.entity.User;
import org.ssm.dufy.service.IUserService;

@Service("userService")
@Transactional
public class IUserServiceImpl  implements IUserService{

	@Autowired
	public IUserDao udao;
	
	@Override
	public User getUserById(String id) {
		return udao.selectByPrimaryKey(id);
	}

	@Override
	public void addUser(User user) {
		udao.insert(user);
	}

}
