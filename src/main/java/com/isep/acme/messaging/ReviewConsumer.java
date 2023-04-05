package com.isep.acme.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.isep.acme.domain.model.Review;
import com.isep.acme.domain.service.ReviewService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ReviewConsumer {

    private final ReviewService reviewService;
 
    @RabbitListener(queues = "#{reviewCreatedQueue.name}")
    public void reviewCreated(Review review){
        log.info("Review received: " + review);
        reviewService.create(review);
        log.info("Review created: " + review);
    }
}
