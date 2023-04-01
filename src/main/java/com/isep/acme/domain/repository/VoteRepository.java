package com.isep.acme.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isep.acme.domain.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    
}
