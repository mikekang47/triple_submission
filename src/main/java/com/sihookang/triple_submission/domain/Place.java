package com.sihookang.triple_submission.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PLACE")
public class Place {
    @Id
    @Column(name = "PLACE_ID", nullable = false)
    private UUID id;

    @OneToMany(mappedBy = "place")
    private List<Review> reviewList = new ArrayList<>();
}
