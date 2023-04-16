package com.isep.acme.dto.response;

import java.util.UUID;

import com.isep.acme.domain.model.enumerate.VoteType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponse {
    
    private UUID voteId;
    private UUID reviewId;
    private VoteType voteType;
    private String user;

}
