package com.isep.acme.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.isep.acme.domain.model.Vote;
import com.isep.acme.domain.service.VoteService;
import com.isep.acme.dto.mapper.VoteMapper;
import com.isep.acme.dto.message.VoteMessage;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class VoteConsumer {

    private final VoteService voteService;
    private final VoteMapper voteMapper;
    
    @RabbitListener(queues = "#{voteCreatedQueue.name}")
    public void voteCreated(VoteMessage voteMessage){

        log.info("Vote received: " + voteMessage);
        Vote vote = voteMapper.toEntity(voteMessage);
        voteService.create(vote);
        log.info("Vote created: " + vote);

    }
}
