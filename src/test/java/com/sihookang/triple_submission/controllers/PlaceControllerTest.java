package com.sihookang.triple_submission.controllers;

import com.sihookang.triple_submission.applications.PlaceService;
import com.sihookang.triple_submission.domain.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlaceController.class)
class PlaceControllerTest {
    private final UUID VALID_ID = UUID.fromString("2e4baf1c-5acb-4efb-a1af-eddada31b00f");

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlaceService placeService;

    @BeforeEach
    void setUp() {
        Place place = Place.builder()
                .id(VALID_ID)
                .build();

        given(placeService.getPlace(VALID_ID)).willReturn(place);

        given(placeService.createPlace()).willReturn(place);
    }

    @Test
    void getPlaceWithValidId() throws Exception {
        mvc.perform(get("/places/"+VALID_ID))
                .andExpect(content().string(containsString("2e4baf1c-5acb-4efb-a1af-eddada31b00f")))
                .andExpect(status().isOk());
    }

    @Test
    void createPlace() throws Exception{
        mvc.perform(post("/places"))
                .andExpect(status().isCreated());
    }
}
