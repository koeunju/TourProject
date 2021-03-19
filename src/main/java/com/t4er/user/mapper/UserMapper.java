package com.t4er.user.mapper;

import com.t4er.user.model.UserVO;

public interface UserMapper {

    Integer idCheck(String id);

    UserVO findUser(UserVO findUser);

    int createUser(UserVO user);

    UserVO selectMy(String idx);

    int updateUser(UserVO user);

    int leaveMember(String idx);

    String checkState(String id);
}
