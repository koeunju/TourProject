package com.t4er.user.mapper;

import java.util.List;

import com.t4er.point.model.PointVO;
import com.t4er.user.model.UserVO;

public interface UserMapper {

    Integer idCheck(String id);

    UserVO findUser(UserVO findUser);

    int createUser(UserVO user);

    UserVO selectMy(String idx);

    int updateUser(UserVO user);

    int leaveMember(String idx);

    String checkState(String id);

    //내포인트조회
	List<PointVO> mypoint(String idx);
	//초기 회원가입시 포인트 부여
	int setPoint(String idx);
}
