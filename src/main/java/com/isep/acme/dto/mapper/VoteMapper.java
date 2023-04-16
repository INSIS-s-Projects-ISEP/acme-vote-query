package com.isep.acme.dto.mapper;

import java.util.Optional;
import java.util.UUID;

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
        UUID reviewId = voteRequest.getReviewId();
        Optional<Review> optReview = reviewRepository.findById(reviewId);
        
        return new Vote(null,
            optReview.orElse(null),
            voteRequest.getVoteType(),
            voteRequest.getUser()
        );
    }

    public Vote toEntity(VoteMessage voteMessage){
        UUID reviewId = voteMessage.getReviewId();
        Optional<Review> optReview = reviewRepository.findById(reviewId);

        return new Vote(
            voteMessage.getVoteId(),
            optReview.orElseThrow(),
            voteMessage.getVoteType(),
            voteMessage.getUser()
        );
    }

    public VoteMessage toMessage(Vote vote){
        return new VoteMessage(
            vote.getVoteId(),
            vote.getReview().getReviewId(),
            vote.getVoteType(),
            vote.getUser()
        );
    }

}
