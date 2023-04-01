package com.isep.acme.domain.service;

import com.isep.acme.domain.model.Review;
import com.isep.acme.domain.model.Vote;

public interface ReviewService {

    Review create(Review review);
    
    Review moderateReview(Long reviewId, String approved);
    
    void addVoteToReview(Long reviewId, Vote vote);

    void deleteReview(Long reviewId);

}
