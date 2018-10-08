package org.ssm.dufy.dao;

import java.util.List;

import org.ssm.dufy.entity.TUser;

public interface TUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
    
	List<TUser> selectAllUser();
}