package com.isep.acme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isep.acme.services.AggregatedRatingService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "AggregatedRating", description = "Endpoints for managing aggregated Rating")
@RestController
@RequestMapping("/aggregatedrating")
public class AggregatedRatingController {

    @Autowired
    AggregatedRatingService aService;

}
