package com.isep.acme.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;

    @Column(nullable = false)
    private String approvalStatus = "pending";

    @OneToMany(mappedBy = "review")
    private Set<Vote> votes = new HashSet<>();

    public Boolean setApprovalStatus(String approvalStatus) {

        if( approvalStatus.equalsIgnoreCase("pending") ||
            approvalStatus.equalsIgnoreCase("approved") ||
            approvalStatus.equalsIgnoreCase("rejected")) {
            
            this.approvalStatus = approvalStatus;
            return true;
        }
        return false;
    }

    public void addVote(Vote upVote) {

        if(!approvalStatus.equals("approved")){
            throw new RuntimeException("Review is not approved");
        }

        votes.add(upVote);
    }
}
