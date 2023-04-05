package com.isep.acme.dto.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.isep.acme.domain.model.Review;
import com.isep.acme.domain.model.Vote;
import com.isep.acme.domain.repository.ReviewRepository;
import com.isep.acme.dto.message.VoteMessage;
import com.isep.acme.dto.request.VoteRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class VoteMapper {

    private final ReviewRepository reviewRepository;

    public Vote toEntity(VoteRequest voteRequest){

        Long reviewId = voteRequest.getReviewId();
        Optional<Review> optReview = reviewRepository.findById(reviewId);
        
        return new Vote(null,
            optReview.orElse(null),
            voteRequest.getVoteType(),
            voteRequest.getUserId()
        );
    }

    public Vote toEntity(VoteMessage voteMessage){

        Long reviewId = voteMessage.getReviewId();
        Optional<Review> optReview = reviewRepository.findById(reviewId);

        return new Vote(null,
            optReview.orElse(null),
            voteMessage.getVoteType(),
            voteMessage.getVoteId()
        );
    }

}
