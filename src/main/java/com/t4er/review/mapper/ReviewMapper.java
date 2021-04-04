package com.t4er.review.mapper;

import com.t4er.review.model.ReviewPagingVO;
import com.t4er.review.model.ReviewVO;

import java.util.List;

public interface ReviewMapper {

    int insertReview(ReviewVO review);

    int getReviewTotalCount(ReviewPagingVO paging);

    List<ReviewVO> selectReview(ReviewPagingVO paging);

}
