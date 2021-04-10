package com.t4er.tour.mapper;

import com.t4er.tour.model.TourVO;

import java.util.List;

public interface TourMapper {

    int insertSaveTour(TourVO tvo);

    //내 여행지 조회
    List<TourVO> myTour(Integer idx);
}
