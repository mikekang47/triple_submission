package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.AttachedPhoto;
import com.sihookang.triple_submission.errors.PhotoNotFoundException;
import com.sihookang.triple_submission.infra.AttachedPhotoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AttachedPhotoService {
    private final AttachedPhotoRepository attachedPhotoRepository;

    public AttachedPhotoService(AttachedPhotoRepository attachedPhotoRepository) {
        this.attachedPhotoRepository = attachedPhotoRepository;
    }
    
    public AttachedPhoto getPhoto(UUID id) {
        return attachedPhotoRepository.findById(id)
                .orElseThrow(() -> new PhotoNotFoundException(id));
    }
}
