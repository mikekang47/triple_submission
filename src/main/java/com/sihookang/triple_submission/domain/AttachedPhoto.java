package com.sihookang.triple_submission.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "ATTACHED_PHOTO")
public class AttachedPhoto extends BaseTimeEntity{
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ATTACHED_ID", nullable = false, columnDefinition = "BINARY(16)")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "REVIEW_ID")
    @JsonBackReference
    private Review review;

    @Builder
    public AttachedPhoto(UUID id, Review review) {
        this.id = id;
        this.review = review;
    }

    public void setReview(Review review) {
        this.review = review;

        if(!review.getAttachedPhotoList().contains(this)) {
            review.getAttachedPhotoList().add(this);
        }
    }
}
