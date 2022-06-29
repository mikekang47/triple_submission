package com.sihookang.triple_submission.controllers;

import com.sihookang.triple_submission.applications.AttachedPhotoService;
import com.sihookang.triple_submission.applications.PlaceService;
import com.sihookang.triple_submission.applications.ReviewService;
import com.sihookang.triple_submission.applications.UserService;
import com.sihookang.triple_submission.domain.AttachedPhoto;
import com.sihookang.triple_submission.domain.Place;
import com.sihookang.triple_submission.domain.Review;
import com.sihookang.triple_submission.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {
    private final UUID VALID_ID = UUID.fromString("240a0658-dc5f-4878-9381-ebb7b2667772");
    private final UUID VALID_USER_ID = UUID.fromString("13ac5c4a-de7a-4fa9-aca2-b79fa8b8c46e");
    private final UUID VALID_PLACE_ID = UUID.fromString("347b2197-849c-4c61-8696-9c16b4640e31");
    private final String VALID_CONTENT = "Like!";
    private final List<AttachedPhoto> photoList = new ArrayList<>();
    private final List<Review> reviewList = new ArrayList<>();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @MockBean
    private PlaceService placeService;

    @MockBean
    private UserService userService;

    @MockBean
    private AttachedPhotoService attachedPhotoService;

    @BeforeEach
    void setUp() {
        User user = new User(VALID_USER_ID,reviewList,0);
        Place place = new Place(VALID_PLACE_ID,reviewList);
        given(reviewService.createReview(VALID_CONTENT,photoList, user, place)).will(invocation -> Review.builder()
                .id(VALID_ID)
                .content(invocation.getArgument(0))
                .attachedPhotoList(invocation.getArgument(1))
                .user(invocation.getArgument(2))
                .place(invocation.getArgument(3))
                .build());
    }

    @Test
    @DisplayName("올바른 데이터로 리뷰를 생성할 경우")
    void createWithValidData() throws Exception {
        mvc.perform(post("/reviews")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"content\": \"Like!\"," +
                                "\"attachedPhotoIds\": []," +
                                "\"userId\": \"13ac5c4a-de7a-4fa9-aca2-b79fa8b8c46e\"," +
                                "\"placeId\": \"347b2197-849c-4c61-8696-9c16b4640e31\"" +
                                "}")
                )
                .andExpect(status().isCreated());
    }
}
