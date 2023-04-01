package com.isep.acme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AggregatedRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aggregatedId;

    @Column()
    private double average;

    protected AggregatedRating() {}

    public AggregatedRating(double averaget) {
        this.average = averaget;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Long getAggregatedId() {
        return aggregatedId;
    }
}
