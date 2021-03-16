package com.t4er.user.service;

import javax.validation.Valid;

import com.t4er.user.model.NotUserException;
import com.t4er.user.model.UserVO;

public interface UserService {

	UserVO loginCheck(String id, String pwd) throws NotUserException;

	UserVO findUser(UserVO findUser) throws NotUserException;

	int createUser(@Valid UserVO user);

	boolean idCheck(String id);


}
