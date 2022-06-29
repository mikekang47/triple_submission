package com.sihookang.triple_submission.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@NoArgsConstructor
@Getter
@Entity
@Setter
@Builder
@Table(name = "MILEAGE", indexes = @Index(name = "idx_mileage", columnList = "id, review.id"))
public class Mileage extends BaseTimeEntity{
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ID", nullable = false, columnDefinition = "BINARY(16)")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "type")
    private String type;

    @OneToOne
    @JoinColumn(name = "REVIEW_ID")
    @JsonBackReference
    private Review review;

    @Column(name = "point")
    @Builder.Default
    private Integer point = 0;


    public Mileage(UUID id, String type, Review review) {
        this.id = id;
        this.type = type;
        this.review = review;
        this.point = 0;
    }

    public Mileage(UUID id, String type, Review review, Integer point) {
        this.id = id;
        this.type = type;
        this.review = review;
        this.point = point;
    }
}
