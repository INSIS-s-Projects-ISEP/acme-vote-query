package com.isep.acme.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.isep.acme.domain.model.AggregatedRating;

public interface AggregatedRatingRepository extends CrudRepository<AggregatedRating, Long> {

}
