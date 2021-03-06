package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.Place;
import com.sihookang.triple_submission.domain.Review;
import com.sihookang.triple_submission.errors.PlaceNotFoundException;
import com.sihookang.triple_submission.infra.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class PlaceServiceTest {
    private PlaceService placeService;

    private final PlaceRepository placeRepository = mock(PlaceRepository.class);

    private final UUID VALID_ID = UUID.fromString("2e4baf1c-5acb-4efb-a1af-eddada31b00f");
    private final UUID INVALID_ID = UUID.fromString("3e4baf1c-5acb-4efb-a1af-eddada31b00f");

    @BeforeEach
    void setUp() {
        placeService = new PlaceService(placeRepository);
        List<Review> reviewList = new ArrayList<>();

        Place place = Place.builder()
                .id(VALID_ID)
                .reviewList(reviewList)
                .build();
        
        given(placeRepository.findById(VALID_ID)).willReturn(Optional.of(place));

        given(placeRepository.save(any(Place.class))).willReturn(place);


    }

    @Test
    @DisplayName("올바른 ID로 장소를 조회하면 장소 객체를 반환한다.")
    void getPlaceWithValidId() {
        Place place = placeService.getPlace(VALID_ID);

        assertThat(place.getId()).isEqualTo(VALID_ID);
    }

    @Test
    @DisplayName("올바르지 않은 ID로 장소를 조회하면 에러를 날린다.")
    void getPlaceWithInvalidId() {
        assertThatThrownBy(() -> placeService.getPlace(INVALID_ID))
                .isInstanceOf(PlaceNotFoundException.class);
    }

    @Test
    @DisplayName("장소를 생성한다.")
    void createPlace() {
        Place place = placeService.createPlace();

        assertThat(place.getReviewList()).isEmpty();
    }

}
