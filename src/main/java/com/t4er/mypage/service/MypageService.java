package com.t4er.mypage.service;

import com.t4er.board.model.BoardVO;
import com.t4er.point.model.PointVO;
import com.t4er.user.model.UserVO;

import java.util.List;

public interface MypageService {

    UserVO selectMy(Integer idx);

    int updateUser(UserVO user);

    int leaveMember(Integer idx);

    // 내 포인트 조회
    List<PointVO> mypoint(Integer idx);

    // 내가 쓴 글 조회
    List<BoardVO> selMyBoard(Integer idx);

}
