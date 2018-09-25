package org.ssm.dufy.service;

import org.ssm.dufy.entity.TUser;

public interface IUserService {

	public TUser getUserById(String id);
	void addUser(TUser user);
}

