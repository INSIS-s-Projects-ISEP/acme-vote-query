package com.isep.acme.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.isep.acme.domain.model.Review;
import com.isep.acme.domain.model.Vote;
import com.isep.acme.domain.service.VoteService;
import com.isep.acme.dto.mapper.VoteMapper;
import com.isep.acme.dto.response.VoteResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;
    private final VoteMapper voteMapper;
    
    @GetMapping("votes")
    public ResponseEntity<List<VoteResponse>> findAll(){
        
        List<Vote> votes = voteService.findAll();
        List<VoteResponse> responses = voteMapper.toResponseList(votes);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("reviews/{reviewId}/votes")
    public ResponseEntity<List<VoteResponse>> findByReview(@PathVariable("reviewId") Review review){

        List<Vote> votes = voteService.findByReview(review);
        List<VoteResponse> responses = voteMapper.toResponseList(votes);

        return ResponseEntity.ok(responses);
    }

}
