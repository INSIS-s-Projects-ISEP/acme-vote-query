package com.isep.acme.domain.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.isep.acme.domain.model.enumerate.VoteType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @Id
    private UUID voteId;

    @ManyToOne
    @JoinColumn(name = "fk_review", nullable = false)
    private Review review;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    private String user;

}
