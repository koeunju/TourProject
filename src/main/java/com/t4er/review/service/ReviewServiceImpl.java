package com.t4er.review.service;

import com.t4er.review.mapper.ReviewMapper;
import com.t4er.review.model.ReviewPagingVO;
import com.t4er.review.model.ReviewVO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public int getReviewTotalCount(ReviewPagingVO paging) {
        return this.reviewMapper.getReviewTotalCount(paging);
    }

    @Override
    public List<ReviewVO> selectReview(ReviewPagingVO paging) {
        return this.reviewMapper.selectReview(paging);
    }

}
