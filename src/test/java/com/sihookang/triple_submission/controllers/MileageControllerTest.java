package com.sihookang.triple_submission.controllers;

import com.sihookang.triple_submission.applications.MileageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(MileageController.class)
class MileageControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MileageService mileageService;

}
