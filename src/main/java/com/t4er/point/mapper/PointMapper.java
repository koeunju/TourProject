package com.t4er.point.mapper;

import java.util.List;

import com.t4er.point.model.PointVO;

public interface PointMapper {

	// 회원번호로 포인트 부여
	int firstPoint(Integer idx);

	String myTotalPoint(Integer idx);

	List<PointVO> mypoint(Integer idx);

}
