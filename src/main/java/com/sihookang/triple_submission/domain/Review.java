package com.sihookang.triple_submission.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "REVIEW")
public class Review {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "REVIEW_ID", nullable = false, columnDefinition = "BINARY(16)")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "PLACE_ID")
    @JsonBackReference
    private Place place;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "review")
    @JsonManagedReference
    private List<AttachedPhoto> attachedPhotoList = new ArrayList<>();

    @Builder
    public Review(UUID id, String content, User user, Place place, List<AttachedPhoto> attachedPhotoList) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.place = place;
        this.attachedPhotoList = attachedPhotoList;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;

        if(!user.getReviewList().contains(this)) {
            user.getReviewList().add(this);
        }
    }

    public void setPlace(Place place) {
        this.place = place;

        if(!place.getReviewList().contains(this)) {
            place.getReviewList().add(this);
        }
    }

    public void addPhoto(AttachedPhoto photo) {
        this.attachedPhotoList.add(photo);

        if(photo.getReview() != this) {
            photo.setReview(this);
        }
    }
}
