package org.ssm.dufy.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssm.dufy.dao.TUserMapper;
import org.ssm.dufy.entity.TUser;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	public TUserMapper udao;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		List<TUser> userList = udao.selectByUserName(token.getUsername());
		if(userList != null && !userList.isEmpty()) {
			TUser user = userList.get(0);
			return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
		} else {
			return null;
		}
	}

}
