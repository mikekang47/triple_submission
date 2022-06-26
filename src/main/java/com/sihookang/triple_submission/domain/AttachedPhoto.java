package com.sihookang.triple_submission.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ATTACHED_PHOTO")
public class AttachedPhoto {
    @Id
    @Column(name = "ATTACHED_PHOTO_ID", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "REVIEW_ID")
    private Review review;

}
