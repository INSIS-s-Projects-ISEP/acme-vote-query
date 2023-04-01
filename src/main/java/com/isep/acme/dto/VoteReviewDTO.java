package com.isep.acme.dto;

import com.isep.acme.domain.model.enumerate.VoteType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VoteReviewDTO {

    private Long userID;
    private VoteType voteType;
    private Long reviewId;
    
}