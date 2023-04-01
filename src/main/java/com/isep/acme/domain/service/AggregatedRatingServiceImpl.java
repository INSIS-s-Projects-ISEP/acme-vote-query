package com.isep.acme.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.domain.repository.AggregatedRatingRepository;

@Service
public class AggregatedRatingServiceImpl implements AggregatedRatingService{

    @Autowired
    AggregatedRatingRepository arRepository;

    @Autowired
    ReviewService rService;

}
