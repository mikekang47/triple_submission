package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.AttachedPhoto;
import com.sihookang.triple_submission.domain.Place;
import com.sihookang.triple_submission.domain.Review;
import com.sihookang.triple_submission.domain.User;
import com.sihookang.triple_submission.errors.ReviewNotFoundException;
import com.sihookang.triple_submission.infra.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ReviewServiceTest {
    private ReviewService reviewService;
    private ReviewRepository reviewRepository = mock(ReviewRepository.class);
    private final UUID VALID_ID = UUID.fromString("240a0658-dc5f-4878-9381-ebb7b2667772");
    private final UUID INVALID_ID = UUID.fromString("340a0658-dc5f-4878-9381-ebb7b2667772");
    


    @BeforeEach
    void setUp() {
        reviewService = new ReviewService(reviewRepository);

        Review review = Review.builder()
                .id(VALID_ID)
                .content("좋아요")
                .place(new Place())
                .attachedPhotoList(new ArrayList<AttachedPhoto>())
                .user(new User())
                .build();
        
        given(reviewRepository.findById(VALID_ID)).willReturn(Optional.of(review));


    }

    @Test
    void getReviewWithValidId() {
        Review review = reviewService.getReview(VALID_ID);

        assertThat(review.getId()).isEqualTo(VALID_ID);
        assertThat(review.getContent()).isEqualTo("좋아요");
    }

    @Test
    void getReviewWithInvalidId() {
        assertThatThrownBy(() -> reviewService.getReview(INVALID_ID))
                .isInstanceOf(ReviewNotFoundException.class);
    }

}
