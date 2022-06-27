package com.sihookang.triple_submission.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "USER")
public class User {
    @Id
    @Column(name = "USER_ID", nullable = false)
    private UUID id;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();

    @Column(name = "POINT", nullable = false)
    @Builder.Default
    private Integer point = 0;

    public void addReview(Review review) {
        this.reviewList.add(review);

        if(review.getUser() != this) {
            review.setUser(this);
        }
    }
}
