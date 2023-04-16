package com.isep.acme.messaging;

import java.io.IOException;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.isep.acme.domain.model.Vote;
import com.isep.acme.domain.service.VoteService;
import com.isep.acme.dto.mapper.VoteMapper;
import com.isep.acme.dto.message.VoteMessage;
import com.rabbitmq.client.Channel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class VoteConsumer {

    private final VoteService voteService;
    private final VoteMapper voteMapper;

    @RabbitListener(queues = "#{voteCreatedQueue.name}", ackMode = "MANUAL")
    public void voteCreated(VoteMessage voteMessage, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException{

        Vote vote = voteMapper.toEntity(voteMessage);
        log.info("Vote received: " + vote.getVoteId());
        voteService.save(vote);
        channel.basicAck(tag, false);
        log.info("Vote created: " + vote.getVoteId());
    }

    @RabbitListener(queues = "#{voteUpdatedQueue.name}", ackMode = "MANUAL")
    public void voteUpdated(VoteMessage voteMessage, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException{

        Vote vote = voteMapper.toEntity(voteMessage);
        log.info("Vote received: " + vote.getVoteId());
        voteService.updateVoteType(vote, vote.getVoteType());
        channel.basicAck(tag, false);
        log.info("Vote updated: " + vote.getVoteId());
    }

    @RabbitListener(queues = "#{voteDeletedQueue.name}", ackMode = "MANUAL")
    public void voteDeleted(UUID voteId, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException{

        log.info("Vote received: " + voteId);
        voteService.deleteById(voteId);
        log.info("Vote deleted: " + voteId);
        channel.basicAck(tag, false);
    }
}
