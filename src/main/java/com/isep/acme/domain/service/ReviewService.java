package com.isep.acme.domain.service;

import com.isep.acme.domain.model.Review;
import com.isep.acme.dto.VoteReviewDTO;

public interface ReviewService {

    Review create(Review review);

    boolean addVoteToReview(Long reviewId, VoteReviewDTO voteReviewDTO);

    Review moderateReview(Long reviewId, String approved);

    Boolean deleteReview(Long reviewId);

}
