package com.sihookang.triple_submission.infra;

import com.sihookang.triple_submission.domain.AttachedPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttachedPhotoRepository extends JpaRepository<AttachedPhoto, UUID> {
}
