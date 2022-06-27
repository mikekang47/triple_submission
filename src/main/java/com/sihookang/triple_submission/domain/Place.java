package com.sihookang.triple_submission.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "PLACE")
public class Place {
    @Id
    @Column(name = "PLACE_ID", nullable = false)
    private UUID id;

    @OneToMany(mappedBy = "place",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    @Builder
    public Place(UUID id, List<Review> reviewList) {
        this.id = id;
        this.reviewList = reviewList;
    }

    public void addReview(Review review) {
        this.reviewList.add(review);

        if(review.getPlace() != this) {
            review.setPlace(this);
        }
    }
}
