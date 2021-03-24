package com.t4er.user.service;

import com.t4er.user.exception.NotUserException;
import com.t4er.user.model.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface UserService {

    UserVO loginCheck(String id, String pwd) throws NotUserException;

    UserVO findUser(UserVO findUser) throws NotUserException;

    int createUser(@Valid UserVO user); //유저 회원가입

    boolean idCheck(String id);
    boolean emailCheck(String email);
    boolean nickCheck(String nick);
    boolean telCheck(String nick);

    int statAlter(String id); // 유저 stat 1로 바꿔주는 메서드

    void mailSendWithUserKey(String email, String id, HttpServletRequest req);

    String checkState(String id);

    int setPoint(@Valid UserVO user);

}
