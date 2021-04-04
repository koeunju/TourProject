package com.t4er.mypage.service;

import com.t4er.board.model.BoardVO;
import com.t4er.point.model.PointVO;
import com.t4er.user.model.UserVO;

import java.util.List;

public interface MypageService {

    UserVO selectMy(Integer idx);

    int updateUser(UserVO user);

    int leaveMember(Integer idx);

    List<PointVO> mypoint(Integer idx);

    List<BoardVO> selMyBoard(Integer idx);

    boolean pwdCheck(Integer idx,String pwd);

    String myTotalPoint(Integer idx);

}
