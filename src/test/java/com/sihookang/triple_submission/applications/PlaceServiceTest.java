package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.Place;
import com.sihookang.triple_submission.domain.Review;
import com.sihookang.triple_submission.infra.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class PlaceServiceTest {
    private PlaceService placeService;
    private PlaceRepository placeRepository = mock(PlaceRepository.class);

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


    }

    @Test
    void getPlaceWithValidId() {
        Place place = placeService.getPlace(VALID_ID);

        assertThat(place.getId()).isEqualTo(VALID_ID);
    }

}
