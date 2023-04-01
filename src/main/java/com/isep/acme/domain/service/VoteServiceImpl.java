package com.isep.acme.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.isep.acme.domain.model.Review;
import com.isep.acme.domain.model.Vote;
import com.isep.acme.domain.repository.ReviewRepository;
import com.isep.acme.domain.repository.VoteRepository;
import com.isep.acme.dto.VoteReviewDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final ReviewRepository reviewRepository;
    private final VoteRepository voteRepository;

    @Override
    public Vote addVoteToReview(Long reviewId, VoteReviewDTO voteReviewDTO) {

        Optional<Review> optReview = reviewRepository.findById(reviewId);
        if(optReview.isEmpty()){
            return null;
        }

        Review review = optReview.get();
        Vote vote = new Vote();

        vote.setReview(review);
        vote.setUserId(voteReviewDTO.getUserID());
        vote.setVoteType(voteReviewDTO.getVoteType());

        return voteRepository.save(vote);
    }
    
}
