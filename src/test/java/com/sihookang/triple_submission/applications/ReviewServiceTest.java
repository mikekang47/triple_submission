package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.AttachedPhoto;
import com.sihookang.triple_submission.domain.Place;
import com.sihookang.triple_submission.domain.Review;
import com.sihookang.triple_submission.domain.User;
import com.sihookang.triple_submission.errors.ReviewNotFoundException;
import com.sihookang.triple_submission.infra.PlaceRepository;
import com.sihookang.triple_submission.infra.ReviewRepository;
import com.sihookang.triple_submission.infra.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ReviewServiceTest {
    private ReviewService reviewService;
    private final ReviewRepository reviewRepository = mock(ReviewRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final PlaceRepository placeRepository = mock(PlaceRepository.class);

    private final UUID VALID_ID = UUID.fromString("240a0658-dc5f-4878-9381-ebb7b2667772");
    private final UUID INVALID_ID = UUID.fromString("340a0658-dc5f-4878-9381-ebb7b2667772");
    private final UUID VALID_USER_ID = UUID.fromString("13ac5c4a-de7a-4fa9-aca2-b79fa8b8c46e");
    private final UUID VALID_PLACE_ID = UUID.fromString("347b2197-849c-4c61-8696-9c16b4640e31");
    private final String VALID_CONTENT = "Like!";
    private final List<AttachedPhoto> photoList = new ArrayList<>();
    private final List<Review> reviewList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        reviewService = new ReviewService(reviewRepository);

        User user = User.builder()
                .id(VALID_USER_ID)
                .reviewList(Collections.emptyList())
                .point(0)
                .build();

        Place place = Place.builder()
                .id(VALID_PLACE_ID)
                .reviewList(Collections.emptyList())
                .build();

        Review review = Review.builder()
                .id(VALID_ID)
                .content(VALID_CONTENT)
                .place(place)
                .attachedPhotoList(photoList)
                .user(user)
                .build();


        given(userRepository.save(any(User.class))).willReturn(user);

        given(placeRepository.save(any(Place.class))).willReturn(place);

        given(reviewRepository.findById(VALID_ID)).willReturn(Optional.of(review));

        given(reviewRepository.save(any(Review.class))).willReturn(review);



    }

    @Test
    @DisplayName("올바른 ID로 리뷰를 조회하면 리뷰 객체를 반환한다.")
    void getReviewWithValidId() {
        Review review = reviewService.getReview(VALID_ID);

        assertThat(review.getId()).isEqualTo(VALID_ID);
        assertThat(review.getContent()).isEqualTo("Like!");
    }

    @Test
    @DisplayName("올바르지 않은 ID로 리뷰를 조회하면 에러를 발생시킨다..")
    void getReviewWithInvalidId() {
        assertThatThrownBy(() -> reviewService.getReview(INVALID_ID))
                .isInstanceOf(ReviewNotFoundException.class);
    }


    @Test
    @DisplayName("존재하는 유저와 존재하는 장소로 리뷰를 생성하면 리뷰 객체를 생성하고 반환한다.")
    void createReviewWithExistsUserAndExistsPlace() {
        User user = User.builder()
                .id(VALID_USER_ID)
                .reviewList(reviewList)
                .point(0)
                .build();

        Place place = Place.builder()
                .id(VALID_PLACE_ID)
                .reviewList(reviewList)
                .build();


        Review review = reviewService.createReview(VALID_CONTENT, photoList, user, place);

        assertThat(review.getUser().getId()).isEqualTo(VALID_USER_ID);
        assertThat(review.getPlace().getId()).isEqualTo(VALID_PLACE_ID);
    }

}
