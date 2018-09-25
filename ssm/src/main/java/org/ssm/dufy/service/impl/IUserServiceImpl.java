package org.ssm.dufy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssm.dufy.dao.TUserMapper;
import org.ssm.dufy.entity.TUser;
import org.ssm.dufy.service.IUserService;

@Service("userService")
@Transactional
public class IUserServiceImpl  implements IUserService{

	@Autowired
	public TUserMapper udao;
	
	@Override
	public TUser getUserById(String id) {
		return udao.selectByPrimaryKey(id);
	}

	@Override
	public void addUser(TUser user) {
		udao.insert(user);
	}

}
