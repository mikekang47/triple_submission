package com.sihookang.triple_submission.domain;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "MILEAGE")
public class Mileage extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "action")
    private String action;

    @OneToOne
    @JoinColumn(name = "REVIEW_ID")
    private Review review;

    @Builder
    public Mileage(Long id, String action, Review review) {
        this.id = id;
        this.action = action;
        this.review = review;
    }
}
