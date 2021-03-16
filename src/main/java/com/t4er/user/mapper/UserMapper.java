package com.t4er.user.mapper;

import com.t4er.user.model.UserVO;

public interface UserMapper {
	
	Integer idCheck(String id);

	UserVO findUser(UserVO findUser);

	int createUser(UserVO user);
	

}


