package com.t4er.mypage.service;

import java.util.List;

import com.t4er.board.model.BoardVO;
import com.t4er.point.model.PointVO;
import com.t4er.user.model.UserVO;

public interface MypageService {

	UserVO selectMy(String idx);

	int updateUser(UserVO user);

	int leaveMember(String idx);
	
	// 내 포인트 조회
    List<PointVO> mypoint(String idx);

    // 내가 쓴 글 조회
    List<BoardVO> selMyBoard(String idx);


}
