package com.isep.acme.domain.service;

import com.isep.acme.domain.model.Vote;
import com.isep.acme.dto.VoteReviewDTO;

public interface VoteService {

    Vote addVoteToReview(Long reviewId, VoteReviewDTO voteReviewDTO);
    
}
