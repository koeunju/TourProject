package com.t4er.point.mapper;

import java.util.List;

import com.t4er.point.model.PointPagingVO;
import com.t4er.point.model.PointVO;

public interface PointMapper {

	// 회원번호로 포인트 부여
	int firstPoint(Integer idx);

	String myTotalPoint(Integer idx);

	List<PointVO> mypoint_old(Integer idx);

	//글 작성 포인트 부여 - 자유 게시판
	int writePoint(Integer idx);
	//여행지 추천
	String tourWritePoint(Integer idx);

	List<PointVO> mypoint(PointPagingVO paging);

	int myTotalCountPoint(PointPagingVO paging);

}
