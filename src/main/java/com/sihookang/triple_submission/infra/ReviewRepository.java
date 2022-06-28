package com.sihookang.triple_submission.infra;

import com.sihookang.triple_submission.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

}
