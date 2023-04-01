package com.isep.acme.services;

import java.util.List;

import com.isep.acme.model.CreateReviewDTO;
import com.isep.acme.model.Review;
import com.isep.acme.model.ReviewDTO;
import com.isep.acme.model.VoteReviewDTO;

public interface ReviewService {

    Iterable<Review> getAll();

    ReviewDTO create(CreateReviewDTO createReviewDTO);

    boolean addVoteToReview(Long reviewID, VoteReviewDTO voteReviewDTO);

    Boolean DeleteReview(Long reviewId);

    List<ReviewDTO> findPendingReview();

    ReviewDTO moderateReview(Long reviewID, String approved);

    List<ReviewDTO> findReviewsByUser(Long userID);
}
