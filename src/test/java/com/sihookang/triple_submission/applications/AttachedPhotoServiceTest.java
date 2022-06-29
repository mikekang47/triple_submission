package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.AttachedPhoto;
import com.sihookang.triple_submission.domain.Review;
import com.sihookang.triple_submission.errors.PhotoNotFoundException;
import com.sihookang.triple_submission.infra.AttachedPhotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AttachedPhotoServiceTest {
    private AttachedPhotoService attachedPhotoService;
    private AttachedPhotoRepository attachedPhotoRepository = mock(AttachedPhotoRepository.class);
    private final List<UUID> VALID_IDS = List.of(UUID.fromString("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8"));
    private final List<UUID> INVALID_IDS = List.of(UUID.fromString("e4d1a64e-a531-46de-88d0-ff0ed70c0bb9"));

    @BeforeEach
    void setUp() {
        attachedPhotoService = new AttachedPhotoService(attachedPhotoRepository);
        AttachedPhoto photo = AttachedPhoto.builder()
                    .id(VALID_IDS.get(0))
                    .review(new Review())
                    .build();
        
        given(attachedPhotoRepository.findById(VALID_IDS.get(0))).willReturn(Optional.of(photo));
    }

    @Test
    @DisplayName("올바른 id list로 사진을 조회하면 일치하는 사진 list를 반환한다.")
    void getPhotosWithValidId() {
        List<AttachedPhoto> photos = attachedPhotoService.getPhotos(VALID_IDS);

        assertThat(photos.get(0).getId()).isEqualTo(VALID_IDS.get(0));
    }

    @Test
    @DisplayName("올바르지 않은 id로 사진을 조회하면 에러를 발생시킨다.")
    void getPhotosWithInvalidId() {
        assertThatThrownBy(() -> attachedPhotoService.getPhotos(INVALID_IDS))
                .isInstanceOf(PhotoNotFoundException.class);
    }
}
