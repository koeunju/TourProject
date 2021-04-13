package com.t4er.mypage.service;

import com.t4er.board.mapper.BoardMapper;
import com.t4er.board.model.BoardPagingVO;
import com.t4er.board.model.BoardVO;
import com.t4er.point.mapper.PointMapper;
import com.t4er.point.model.PointPagingVO;
import com.t4er.point.model.PointVO;
import com.t4er.tour.mapper.TourMapper;
import com.t4er.tour.model.TourVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t4er.user.mapper.UserMapper;
import com.t4er.user.model.UserVO;

import java.util.List;
import java.util.Map;

@Service("mypageService")
public class MypageServiceImpl implements MypageService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private PointMapper pointMapper;

	@Autowired
	private TourMapper tourMapper;

	@Override
	public UserVO selectMy(Integer idx) {
		return this.userMapper.selectMy(idx);
	}

	@Override
	public int updateUser(UserVO user) {
		return this.userMapper.updateUser(user);
	}

	@Override
	public int leaveMember(Integer idx) {
		return this.userMapper.leaveMember(idx);
	}

	// 내 포인트 조회
	@Override
	public List<PointVO> mypoint_old(Integer idx) {
		return this.pointMapper.mypoint_old(idx);
	}
	// 내가 쓴 글 조회
	@Override
	public List<BoardVO> selMyBoard_old(Integer idx) {
		return this.boardMapper.selMyBoard_old(idx);
	}

	@Override
	public boolean pwdCheck(Integer idx, String pwd) {
		UserVO user = userMapper.selectMy(idx);
		if (user.getPwd().equals(pwd)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int myTotalCountPoint(PointPagingVO paging) {
		return this.pointMapper.myTotalCountPoint(paging);
	}

	@Override
	public List<PointVO> mypoint(PointPagingVO paging) {
		return this.pointMapper.mypoint(paging);
	}

	@Override
	public String myTotalPoint(Integer idx) {
		return pointMapper.myTotalPoint(idx);
	}

	//내가쓴글 페이징
	@Override
	public int myTotalCountBoard(BoardPagingVO paging) {
		// TODO Auto-generated method stub
		return this.boardMapper.myTotalCountBoard(paging);
	}

	@Override
	public List<BoardVO> selMyBoard(BoardPagingVO paging) {

		return this.boardMapper.selMyBoard(paging);
	}

	//내 여행찜 정보
	@Override
	public List<TourVO> myTour(Integer idx) {
		return this.tourMapper.myTour(idx);
	}

	//여행찜 지우기
	@Override
	public Integer deleteTour(Map<String, Integer> map) {
		return  this.tourMapper.deleteTour(map);
	}

}