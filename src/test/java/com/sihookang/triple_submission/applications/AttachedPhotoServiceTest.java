package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.AttachedPhoto;
import com.sihookang.triple_submission.domain.Review;
import com.sihookang.triple_submission.errors.PhotoNotFoundException;
import com.sihookang.triple_submission.infra.AttachedPhotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AttachedPhotoServiceTest {
    private AttachedPhotoService attachedPhotoService;
    private AttachedPhotoRepository attachedPhotoRepository = mock(AttachedPhotoRepository.class);
    private final UUID VALID_ID = UUID.fromString("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8");
    private final UUID INVALID_ID = UUID.fromString("e4d1a64e-a531-46de-88d0-ff0ed70c0bb9");

    @BeforeEach
    void setUp() {
        attachedPhotoService = new AttachedPhotoService(attachedPhotoRepository);
        AttachedPhoto photo = AttachedPhoto.builder()
                    .id(VALID_ID)
                    .review(new Review())
                    .build();
        
        given(attachedPhotoRepository.findById(VALID_ID)).willReturn(Optional.of(photo));
    }

    @Test
    @DisplayName("올바른 id로 사진을 조회하면 일치하는 사진을 반환한다.")
    void getPhotoWithValidId() {
        AttachedPhoto photo = attachedPhotoService.getPhoto(VALID_ID);

        assertThat(photo.getId()).isEqualTo(VALID_ID);
    }

    @Test
    @DisplayName("올바르지 않은 id로 사진을 조회하면 에러를 발생시킨다.")
    void getPhotoWithInvalidId() {
        assertThatThrownBy(() -> attachedPhotoService.getPhoto(INVALID_ID))
                .isInstanceOf(PhotoNotFoundException.class);
    }
}
