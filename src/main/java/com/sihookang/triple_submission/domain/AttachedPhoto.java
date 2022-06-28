package com.sihookang.triple_submission.domain;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "ATTACHED_PHOTO")
public class AttachedPhoto extends BaseTimeEntity{
    @Id
    @Column(name = "ATTACHED_PHOTO_ID", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "REVIEW_ID")
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
