package com.sihookang.triple_submission.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    @Id
    @Column(name = "USER_ID", nullable = false)
    private UUID id;

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();
}
