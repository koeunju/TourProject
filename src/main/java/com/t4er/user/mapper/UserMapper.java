package com.t4er.user.mapper;

import javax.servlet.http.HttpServletRequest;

import com.t4er.user.model.UserVO;

public interface UserMapper {
	
	Integer idCheck(String id);
	Integer emailCheck(String email);

	UserVO findUser(UserVO findUser);

	int createUser(UserVO user);
	
	String checkState(String id);


}


