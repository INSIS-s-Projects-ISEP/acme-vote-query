package com.isep.acme.domain.service;

import com.isep.acme.domain.model.Review;

public interface ReviewService {

    Review create(Review review);

    Review moderateReview(Long reviewId, String approved);

    void deleteReview(Long reviewId);

}
