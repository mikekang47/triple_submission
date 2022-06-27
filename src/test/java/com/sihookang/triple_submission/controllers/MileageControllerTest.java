package com.sihookang.triple_submission.controllers;

import com.sihookang.triple_submission.applications.MileageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MileageController.class)
class MileageControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MileageService mileageService;

    @BeforeEach
    void setUp() {

        given(mileageService.createMileage()).will(invocation -> {

            return null;
        });
    }

    @Test
    void createMileage() throws Exception {
        mvc.perform(post("/events")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"type\": \"REVIEW\",\n" +
                                "\"action\": \"ADD\", \n" +
                                "\"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\",\n" +
                                "\"content\": \"좋아요!\",\n" +
                                "\"attachedPhotoIds\": [\"e4d1a64e-a531-46de-88d0-ff0ed70c0bb8\", \"afb0cef2-\n" +
                                "851d-4a50-bb07-9cc15cbdc332\"],\n" +
                                " \"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\",\n" +
                                " \"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"\n" +
                                "}")
                )
                .andExpect(content().string(containsString("REVIEW")))
                .andExpect(status().isCreated());


    }

    @Test
    @DisplayName("올바르지 않은 타입으로 마일리지를 생성 요청시 타입 에러를 발생시킨다.")
    void createWithInvalidType() throws Exception {
        mvc.perform(post("/events")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"type\": \"BUY\",\n" +
                                "\"action\": \"ADD\", \n" +
                                "\"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\",\n" +
                                "\"content\": \"좋아요!\",\n" +
                                "\"attachedPhotoIds\": [\"e4d1a64e-a531-46de-88d0-ff0ed70c0bb8\", \"afb0cef2-\n" +
                                "851d-4a50-bb07-9cc15cbdc332\"],\n" +
                                " \"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\",\n" +
                                " \"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"\n" +
                                "}")
                )
                .andExpect(status().isNotFound());

    }

}
