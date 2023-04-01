package com.isep.acme.repositories;

import org.springframework.data.repository.CrudRepository;

import com.isep.acme.model.AggregatedRating;

public interface AggregatedRatingRepository extends CrudRepository<AggregatedRating, Long> {

}
