package com.t4er.review.service;

import com.t4er.review.model.ReviewPagingVO;
import com.t4er.review.model.ReviewVO;

import java.util.List;

public interface ReviewService {

    int insertReview(ReviewVO review);

    int getReviewTotalCount(ReviewPagingVO paging);

    List<ReviewVO> selectReview(ReviewPagingVO paging);
}
