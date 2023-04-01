package com.isep.acme.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.isep.acme.domain.model.Review;
import com.isep.acme.domain.repository.ReviewRepository;
import com.isep.acme.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review moderateReview(Long reviewID, String approved){

        Optional<Review> r = reviewRepository.findById(reviewID);
        if(r.isEmpty()){
            throw new ResourceNotFoundException("Review not found");
        }

        Boolean ap = r.get().setApprovalStatus(approved);
        if(!ap) {
            throw new IllegalArgumentException("Invalid status value");
        }

        return reviewRepository.save(r.get());
    }

    @Override
    public void deleteReview(Long reviewId)  {
        reviewRepository.deleteById(reviewId);
    }

}