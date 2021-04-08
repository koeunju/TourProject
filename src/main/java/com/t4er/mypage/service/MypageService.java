package com.t4er.mypage.service;

import com.t4er.board.model.BoardPagingVO;
import com.t4er.board.model.BoardVO;
import com.t4er.point.model.PointPagingVO;
import com.t4er.point.model.PointVO;
import com.t4er.tour.model.TourVO;
import com.t4er.user.model.UserVO;

import java.util.List;

public interface MypageService {

    UserVO selectMy(Integer idx);

    int updateUser(UserVO user);

    int leaveMember(Integer idx);

    List<PointVO> mypoint_old(Integer idx);

    List<BoardVO> selMyBoard_old(Integer idx);
    List<BoardVO> selMyBoard(BoardPagingVO paging);

    boolean pwdCheck(Integer idx,String pwd);

    String myTotalPoint(Integer idx);

	int myTotalCountPoint(PointPagingVO paging);

	List<PointVO> mypoint(PointPagingVO paging);

	int myTotalCountBoard(BoardPagingVO paging);

	List<TourVO> myTour(Integer idx);

}
