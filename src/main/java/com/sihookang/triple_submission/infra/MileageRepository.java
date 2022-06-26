package com.sihookang.triple_submission.infra;


import com.sihookang.triple_submission.domain.Mileage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageRepository extends JpaRepository<Mileage, Long> {
}
