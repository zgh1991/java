package org.ssm.dufy.dao;

import org.ssm.dufy.entity.User;

public interface IUserDao {
	String deleteByPrimaryKey(String id);

    int insert(User record);

    String insertSelective(User record);

    User selectByPrimaryKey(String id);

    String updateByPrimaryKeySelective(User record);

    String updateByPrimaryKey(User record);
}