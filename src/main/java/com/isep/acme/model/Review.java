package com.isep.acme.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idReview;

    @Column(nullable = false)
    private String approvalStatus = "pending";

    @ElementCollection
    @Column(nullable = true)
    private List<Vote> upVote = new ArrayList<>();

    @ElementCollection
    @Column(nullable = true)
    private List<Vote> downVote = new ArrayList<>();

    public Boolean setApprovalStatus(String approvalStatus) {

        if( approvalStatus.equalsIgnoreCase("pending") ||
            approvalStatus.equalsIgnoreCase("approved") ||
            approvalStatus.equalsIgnoreCase("rejected")) {
            
            this.approvalStatus = approvalStatus;
            return true;
        }
        return false;
    }

    public boolean addUpVote(Vote upVote) {

        if( !this.approvalStatus.equals("approved"))
            return false;

        if(!this.upVote.contains(upVote)){
            this.upVote.add(upVote);
            return true;
        }
        return false;
    }

    public boolean addDownVote(Vote downVote) {

        if( !this.approvalStatus.equals( "approved") )
            return false;

        if(!this.downVote.contains(downVote)){
            this.downVote.add(downVote);
            return true;
        }
        return false;
    }
}
