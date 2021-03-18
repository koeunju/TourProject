package com.t4er.mypage.service;

import com.t4er.user.model.UserVO;

public interface MypageService {

	UserVO selectMy(String idx);

	int updateUser(UserVO user);

	int leaveMember(String idx);

}
