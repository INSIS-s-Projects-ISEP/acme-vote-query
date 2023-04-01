package com.isep.acme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.repositories.AggregatedRatingRepository;

@Service
public class AggregatedRatingServiceImpl implements AggregatedRatingService{

    @Autowired
    AggregatedRatingRepository arRepository;

    @Autowired
    ReviewService rService;

}
