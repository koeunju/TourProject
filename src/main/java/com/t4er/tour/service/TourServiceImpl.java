package com.t4er.tour.service;

import com.t4er.tour.mapper.TourMapper;
import com.t4er.tour.model.TourVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tourService")
public class TourServiceImpl implements TourService{

    @Autowired
    private TourMapper tourMapper;

    @Override
    public int insertSaveTour(TourVO tvo) {
        return this.tourMapper.insertSaveTour(tvo);
    }
}
