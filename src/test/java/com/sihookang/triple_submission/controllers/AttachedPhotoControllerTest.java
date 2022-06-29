package com.sihookang.triple_submission.controllers;

import com.sihookang.triple_submission.applications.AttachedPhotoService;
import com.sihookang.triple_submission.domain.AttachedPhoto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AttachedPhotoController.class)
class AttachedPhotoControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AttachedPhotoService attachedPhotoService;
    private final UUID VALID_ID = UUID.fromString("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8");

    @BeforeEach
    void setUp() {
        AttachedPhoto photo = AttachedPhoto.builder()
                .id(VALID_ID)
                .build();

        given(attachedPhotoService.getPhoto(VALID_ID)).willReturn(photo);
    }

    @Test
    void getPhotoWithValidId() throws Exception {
        mvc.perform(get("/photos/"+VALID_ID))
                .andExpect(status().isOk());
    }
}
