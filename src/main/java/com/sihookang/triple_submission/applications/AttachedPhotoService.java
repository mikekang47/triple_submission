package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.AttachedPhoto;
import com.sihookang.triple_submission.errors.PhotoNotFoundException;
import com.sihookang.triple_submission.infra.AttachedPhotoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 리뷰에 첨부된 사진에 관련된 실제 처리를 담당하는 클래스입니다.
 */
@Service
public class AttachedPhotoService {
    private final AttachedPhotoRepository attachedPhotoRepository;

    public AttachedPhotoService(AttachedPhotoRepository attachedPhotoRepository) {
        this.attachedPhotoRepository = attachedPhotoRepository;
    }

    /**
     * parameter id와 일치하는 id를 가진 사진 객체를 찾아서 반환합니다.
     * @param id 찾고자 하는 id
     * @return parameter id와 일치하는 id를 가진 사진 객체
     */
    public AttachedPhoto getPhoto(UUID id) {
        return attachedPhotoRepository.findById(id)
                .orElseThrow(() -> new PhotoNotFoundException(id));
    }
}
