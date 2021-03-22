package com.t4er.mypage.service;

import com.t4er.board.mapper.BoardMapper;
import com.t4er.board.model.BoardVO;
import com.t4er.point.model.PointVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t4er.user.mapper.UserMapper;
import com.t4er.user.model.UserVO;

import java.util.List;

@Service("mypageService")
public class MypageServiceImpl implements MypageService {
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private BoardMapper boardMapper;
	
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

	// 내 포인트 조회
	@Override
	public List<PointVO> mypoint(String idx) {
		return this.userMapper.mypoint(idx);
	}
	// 내가 쓴 글 조회
	@Override
	public List<BoardVO> selMyBoard(String idx) {
		return this.boardMapper.selMyBoard(idx);
	}
	
	
}
