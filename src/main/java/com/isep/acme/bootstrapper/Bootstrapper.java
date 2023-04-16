package com.isep.acme.bootstrapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.isep.acme.domain.model.Review;
import com.isep.acme.domain.model.Vote;
import com.isep.acme.domain.repository.ReviewRepository;
import com.isep.acme.domain.repository.VoteRepository;
import com.isep.acme.dto.mapper.ReviewMapper;
import com.isep.acme.dto.mapper.VoteMapper;
import com.isep.acme.dto.message.ReviewMessage;
import com.isep.acme.dto.message.VoteMessage;
import com.isep.acme.messaging.RabbitmqService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class Bootstrapper implements CommandLineRunner {

    @Value("#{rpcReviewExchange.name}")
    private final String rpcReviewExchange;

    @Value("#{rpcVoteExchange.name}")
    private final String rpcVoteExchange;

    private final RabbitmqService rabbitmqService;
    
    private final ReviewRepository reviewRepository;
    private final VoteRepository voteRepository;

    private final ReviewMapper reviewMapper;
    private final VoteMapper voteMapper;

    @Override
    public void run(String... args) throws Exception {
        this.rpcReview();
        this.rpcVote();
        log.info("Database ready!");
    }

    private void rpcReview() throws Exception {
        
        log.info("Sending RPC Review Request...");
        String response = (String) rabbitmqService.sendRpc(rpcReviewExchange);
        
        List<ReviewMessage> messages = reviewMapper.toMessageList(response);
        List<Review> reviews = reviewMapper.toEntityList(messages);
        log.info("Received RPC Review Response | Size: " + reviews.size());

        reviewRepository.saveAll(reviews);
    }

    private void rpcVote() throws Exception {
        
        log.info("Sending RPC Vote Request...");
        String response = (String) rabbitmqService.sendRpc(rpcVoteExchange);
        
        List<VoteMessage> messages = voteMapper.toMessageList(response);
        List<Vote> votes = voteMapper.toEntityList(messages);
        log.info("Received RPC Vote Response | Size: " + votes.size());

        voteRepository.saveAll(votes);
    }
    
}
