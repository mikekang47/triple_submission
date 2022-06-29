package com.sihookang.triple_submission.controllers;

import com.sihookang.triple_submission.applications.*;
import com.sihookang.triple_submission.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MileageController.class)
class MileageControllerTest {
    private final UUID VALID_MILEAGE_ID = UUID.fromString("afb0cef2-851d-4a50-bb07-9cc15cbdc332");
    private final UUID VALID_REVIEW_ID = UUID.fromString("240a0658-dc5f-4878-9381-ebb7b2667772");
    private final UUID VALID_USER_ID =  UUID.fromString("3ede0ef2-92b7-4817-a5f3-0c575361f745");
    private final UUID VALID_PLACE_ID = UUID.fromString("2e4baf1c-5acb-4efb-a1af-eddada31b00f");
    private final List<UUID> VALID_PHOTO_ID_LIST = List.of(UUID.fromString("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8"));

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MileageService mileageService;

    @MockBean
    private ReviewService reviewService;

    @MockBean
    private UserService userService;

    @MockBean
    private PlaceService placeService;

    @MockBean
    private AttachedPhotoService attachedPhotoService;

    @BeforeEach
    void setUp() {
        Review review = Review.builder()
                .id(VALID_REVIEW_ID)
                .user(userService.getUser(VALID_USER_ID))
                .content("like!")
                .place(placeService.getPlace(VALID_PLACE_ID))
                .attachedPhotoList(attachedPhotoService.getPhotos(VALID_PHOTO_ID_LIST))
                .build();

        User user = User.builder()
                .id(VALID_USER_ID)
                .reviewList(List.of(review))
                .point(10)
                .build();

        Place place = Place.builder()
                .id(VALID_PLACE_ID)
                .reviewList(List.of(review))
                .build();

        AttachedPhoto photo = AttachedPhoto.builder()
                .id(VALID_PHOTO_ID_LIST.get(0))
                .review(review)
                .build();

        Mileage mileage = Mileage.builder()
                .id(VALID_MILEAGE_ID)
                .review(review)
                .type("REVIEW")
                .point(2)
                .build();
        
        given(mileageService.createMileage(any(User.class), any(Review.class), any(Place.class), anyList(), any(String.class)))
                .willReturn(mileage);

        given(userService.getUser(VALID_USER_ID)).willReturn(user);
        given(placeService.getPlace(VALID_PLACE_ID)).willReturn(place);
        given(reviewService.getReview(VALID_REVIEW_ID)).willReturn(review);
        given(attachedPhotoService.getPhotos(VALID_PHOTO_ID_LIST)).willReturn(List.of(photo));

        given(mileageService.modifyMileage(any(User.class), any(Review.class), any(Place.class), anyList(), any(String.class))).will(invocation -> {
            Review sourceReview = invocation.getArgument(1);
            return Mileage.builder()
                    .id(VALID_MILEAGE_ID)
                    .review(sourceReview)
                    .type("REVIEW")
                    .point(1)
                    .build();
        });

        given(mileageService.getMileage(VALID_MILEAGE_ID)).willReturn(mileage);


    }

    @Test
    void createMileage() throws Exception {
        mvc.perform(post("/events")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"type\": \"REVIEW\"," +
                                "\"action\": \"ADD\", " +
                                "\"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\"," +
                                "\"content\": \"like!\"," +
                                "\"attachedPhotoIds\": [\"e4d1a64e-a531-46de-88d0-ff0ed70c0bb8\"]," +
                                "\"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\"," +
                                "\"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"" +
                                "}")
                )
                .andExpect(status().isCreated());

        verify(mileageService).createMileage(any(User.class), any(Review.class), any(Place.class), anyList(), any(String.class));

    }

    @Test
    @DisplayName("올바르지 않은 타입으로 마일리지를 생성 요청시 타입 에러를 발생시킨다.")
    void createWithInvalidType() throws Exception {
        mvc.perform(post("/events")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"type\": \"EVENT\"," +
                                "\"action\": \"ADD\", " +
                                "\"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\"," +
                                "\"content\": \"like!\"," +
                                "\"attachedPhotoIds\": [\"e4d1a64e-a531-46de-88d0-ff0ed70c0bb8\"]," +
                                "\"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\"," +
                                "\"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"" +
                                "}")
                )
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("존재하는 마일리지를 업데이트 하려는 경우 200을 반환한다.")
    void updateWithValidId() throws Exception {
        mvc.perform(post("/events")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"type\": \"REVIEW\"," +
                        "\"action\": \"MOD\", " +
                        "\"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\"," +
                        "\"content\": \"like!\"," +
                        "\"attachedPhotoIds\": [\"240a0658-dc5f-4878-9381-ebb7b2667772\"]," +
                        "\"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\"," +
                        "\"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"" +
                        "}")
        )
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("존재하는 마일리지의 id로 포인트를 조회하는 경우 포인트를 반환한다.")
    void getPointWithValidId() throws Exception {
        mvc.perform(get("/events/" + VALID_MILEAGE_ID))
                .andExpect(content().string(containsString("2")))
                .andExpect(status().isOk());
    }
}
