package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.*;
import com.sihookang.triple_submission.infra.MileageRepository;
import com.sihookang.triple_submission.infra.PlaceRepository;
import com.sihookang.triple_submission.infra.ReviewRepository;
import com.sihookang.triple_submission.infra.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest
class MileageServiceTest {
    private MileageService mileageService;

    private final MileageRepository mileageRepository = mock(MileageRepository.class);
    private final PlaceRepository placeRepository = mock(PlaceRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final ReviewRepository reviewRepository = mock(ReviewRepository.class);

    private final UUID VALID_ID = UUID.fromString("240a0658-dc5f-4878-9381-ebb7b2667772");
    private final UUID INVALID_ID = UUID.fromString("340a0658-dc5f-4878-9381-ebb7b2667772");
    private final UUID VALID_USER_ID = UUID.fromString("13ac5c4a-de7a-4fa9-aca2-b79fa8b8c46e");
    private final UUID VALID_PLACE_ID = UUID.fromString("347b2197-849c-4c61-8696-9c16b4640e31");
    private final String VALID_CONTENT = "Like!";
    private final UUID VALID_REVIEW_ID = UUID.fromString("8d0f37e1-4e8b-4a19-86fc-99dfc1ba4773");
    private final List<AttachedPhoto> photoList = new ArrayList<>();
    private final List<Review> reviewList = new ArrayList<>();



    @BeforeEach
    void setUp() {
        mileageService = new MileageService(mileageRepository);
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
                .id(VALID_REVIEW_ID)
                .content(VALID_CONTENT)
                .place(place)
                .attachedPhotoList(photoList)
                .user(user)
                .build();

        given(userRepository.findById(VALID_USER_ID)).willReturn(Optional.of(user));
        given(placeRepository.findById(VALID_PLACE_ID)).willReturn(Optional.of(place));
        given(reviewRepository.findById(VALID_REVIEW_ID)).willReturn(Optional.of(review));
        
        given(mileageRepository.save(any(Mileage.class))).will(invocation -> {
            Mileage source = invocation.getArgument(0);
            return Mileage.builder()
                    .id(source.getId())
                    .review(source.getReview())
                    .type("REVIEW")
                    .build();
        });

    }

    @Test
    @DisplayName("올바른 데이터로 마일리지를 생성하는 경우")
    void createWithValidMileageData() {
        User user = new User(VALID_USER_ID, reviewList, 0);
        Place place = new Place(VALID_PLACE_ID, reviewList);
        Review review = new Review(VALID_REVIEW_ID, "LIKE!", user, place, photoList);
        
        Mileage mileage = mileageService.createMileage(user, review, place, photoList);

        assertThat(mileage.getReview().getId()).isEqualTo(VALID_REVIEW_ID);
    }

}
