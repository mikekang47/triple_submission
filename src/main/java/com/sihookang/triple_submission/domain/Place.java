package com.sihookang.triple_submission.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Entity
@Table(name = "PLACE", indexes = {
        @Index(name = "idx__place", columnList = "place_id")
})
public class Place extends BaseTimeEntity{
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "PLACE_ID", nullable = false, columnDefinition = "BINARY(16)")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotNull
    @OneToMany(mappedBy = "place",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    @JsonManagedReference
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
