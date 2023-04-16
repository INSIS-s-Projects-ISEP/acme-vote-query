package com.isep.acme.domain.service;

import java.util.List;
import java.util.UUID;

import com.isep.acme.domain.model.Review;
import com.isep.acme.domain.model.Vote;
import com.isep.acme.domain.model.enumerate.VoteType;

public interface VoteService {

    Vote save(Vote vote);

    List<Vote> findAll();

    List<Vote> findByReview(Review review);

    Vote updateVoteType(Vote vote, VoteType voteType);
    
    void deleteById(UUID voteId);
}
