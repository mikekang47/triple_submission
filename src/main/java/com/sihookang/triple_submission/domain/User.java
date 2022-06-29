package com.sihookang.triple_submission.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "UUID")
    @Column(name = "USER_ID", nullable = false, columnDefinition = "BINARY(16)")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    @JsonManagedReference
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

    public void setPoint(Integer point) {
        this.point = point;
    }
}
