package com.t4er.user.service;

import com.t4er.user.exception.NotUserException;
import com.t4er.user.model.UserVO;

import javax.validation.Valid;

public interface UserService {

    UserVO loginCheck(String id, String pwd) throws NotUserException;

    UserVO findUser(UserVO findUser) throws NotUserException;

    int createUser(@Valid UserVO user);

    boolean idCheck(String id);

    String checkState(String id);
    
    int setPoint(@Valid UserVO user);

}
