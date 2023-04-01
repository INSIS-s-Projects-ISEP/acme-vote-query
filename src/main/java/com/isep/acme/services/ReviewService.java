package com.isep.acme.services;

import com.isep.acme.model.Review;
import com.isep.acme.model.VoteReviewDTO;

public interface ReviewService {

    Review create(Review review);

    boolean addVoteToReview(Long reviewId, VoteReviewDTO voteReviewDTO);

    Review moderateReview(Long reviewId, String approved);

    Boolean deleteReview(Long reviewId);

}
