package com.t4er.tour.mapper;

import java.util.List;

import com.t4er.tour.model.TourVO;

public interface TourMapper {
	
	//여행지 찜 등록
    int insertSaveTour(TourVO tvo);
    
    //내 여행지 조회
	List<TourVO> myTour(Integer idx);

}
