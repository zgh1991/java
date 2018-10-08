package org.ssm.dufy.service;

import java.util.List;

import org.ssm.dufy.entity.TUser;

public interface IUserService {

	public TUser getUserById(String id);
	void addUser(TUser user);
	List<TUser> selectAllUser();
}

