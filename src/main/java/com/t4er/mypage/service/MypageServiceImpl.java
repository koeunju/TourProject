package com.t4er.mypage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t4er.user.mapper.UserMapper;
import com.t4er.user.model.UserVO;

@Service("mypageService")
public class MypageServiceImpl implements MypageService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserVO selectMy(String idx) {
		return this.userMapper.selectMy(idx);
	}
	
	@Override
	public int updateUser(UserVO user) {
		return this.userMapper.updateUser(user);
	}

	@Override
	public int leaveMember(String idx) {
		return this.userMapper.leaveMember(idx);
	}
	
	
	
}
