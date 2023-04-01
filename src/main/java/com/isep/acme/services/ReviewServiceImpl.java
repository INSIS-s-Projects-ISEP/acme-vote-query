package com.isep.acme.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;
import com.isep.acme.model.VoteReviewDTO;
import com.isep.acme.repositories.ReviewRepository;

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
    public boolean addVoteToReview(Long reviewID, VoteReviewDTO voteReviewDTO) {

        Optional<Review> review = reviewRepository.findById(reviewID);
        if(review.isEmpty()){
            return false;
        }

        Vote vote = new Vote(voteReviewDTO.getVote(), voteReviewDTO.getUserID());
        if(voteReviewDTO.getVote().equalsIgnoreCase("upVote")){
            boolean added = review.get().addUpVote(vote);
            if(added){
                Review reviewUpdated = this.reviewRepository.save(review.get());
                return reviewUpdated != null;
            }
        } else if (voteReviewDTO.getVote().equalsIgnoreCase("downVote")) {
            boolean added = review.get().addDownVote(vote);
            if (added) {
                Review reviewUpdated = this.reviewRepository.save(review.get());
                return reviewUpdated != null;
            }
        }
        return false;
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
    public Boolean deleteReview(Long reviewId)  {

        Optional<Review> rev = reviewRepository.findById(reviewId);
        if (rev.isEmpty()){
            return null;
        }

        Review review = rev.get();
        if (review.getUpVote().isEmpty() && review.getDownVote().isEmpty()) {
            reviewRepository.delete(review);
            return true;
        }

        return false;
    }

}