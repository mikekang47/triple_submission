package com.sihookang.triple_submission.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Entity
@Setter
@Builder
@Table(name = "MILEAGE", indexes = @Index(name = "idx_mileage", columnList = "id, review_id"))
public class Mileage extends BaseTimeEntity{
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ID", nullable = false, columnDefinition = "BINARY(16)")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotNull
    @Column(name = "type")
    private String type;

    @OneToOne
    @JoinColumn(name = "REVIEW_ID")
    @JsonBackReference
    @NotNull
    private Review review;

    @Column(name = "point")
    @Builder.Default
    @NotNull
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
