package com.t4er.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;

import com.t4er.user.model.NotUserException;
import com.t4er.user.model.UserVO;

public interface UserService {

	UserVO loginCheck(String id, String pwd) throws NotUserException;
	
	UserVO userCheck(String id, String email) throws NotUserException;

	UserVO findUser(UserVO findUser) throws NotUserException;

	int createUser(@Valid UserVO user); //���� ȸ������

	boolean idCheck(String id);
	boolean emailCheck(String email);
	boolean nickCheck(String nick);
	boolean telCheck(String nick);
	
	int statAlter(String id); // ���� stat 1�� �ٲ��ִ� �޼���

	void mailSendWithUserKey(String email, String id, HttpServletRequest req);

	String checkState(String id);

	String searchId(@Param("nick")String nick, @Param("email")String email);

	void mailSendPwd(String id, String email, HttpServletRequest req);
	
	int searchPwd(String id, String email, String key);


}
