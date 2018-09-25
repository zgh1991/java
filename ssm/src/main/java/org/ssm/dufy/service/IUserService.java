package org.ssm.dufy.service;

import org.ssm.dufy.entity.User;

public interface IUserService {

	public User getUserById(String id);
	void addUser(User user);
}

