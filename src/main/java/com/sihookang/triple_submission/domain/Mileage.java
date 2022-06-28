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
@Table(name = "MILEAGE")
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

    @Builder
    public Mileage(UUID id, String type, Review review) {
        this.id = id;
        this.type = type;
        this.review = review;
    }
}
