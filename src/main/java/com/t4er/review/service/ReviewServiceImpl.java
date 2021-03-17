package com.t4er.review.service;

import com.t4er.review.mapper.ReviewMapper;
import com.t4er.review.model.ReviewVO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reviewServiceImpl")
@Log4j
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public int insertReview(ReviewVO review) {
        log.info(reviewMapper);
        return this.reviewMapper.insertReview(review);
    }
}
