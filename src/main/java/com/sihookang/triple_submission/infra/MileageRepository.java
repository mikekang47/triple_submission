package com.sihookang.triple_submission.infra;


import com.sihookang.triple_submission.domain.Mileage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface MileageRepository extends JpaRepository<Mileage, UUID> {
    @Query("select m from Mileage m where m.review.id = ?1")
    Optional<Mileage> findByReviewId(UUID id);

    void deleteByReviewId(UUID uuid);

    @Query("select (count(m) > 0) from Mileage m where m.review.id = ?1")
    boolean existsByReviewId(UUID id);
}
