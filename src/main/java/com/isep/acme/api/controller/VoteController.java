package com.isep.acme.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isep.acme.domain.model.Vote;
import com.isep.acme.domain.service.ReviewService;
import com.isep.acme.dto.mapper.VoteMapper;
import com.isep.acme.dto.request.VoteRequest;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("votes")
public class VoteController {

    private final ReviewService reviewService;
    private final VoteMapper voteMapper;
    
}
