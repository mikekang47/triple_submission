package com.sihookang.triple_submission.controllers;

import com.sihookang.triple_submission.applications.UserService;
import com.sihookang.triple_submission.domain.User;
import com.sihookang.triple_submission.errors.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    private final UUID VALID_ID = UUID.fromString("3ede0ef2-92b7-4817-a5f3-0c575361f745");
    private final UUID INVALID_ID = UUID.fromString("4ede0ef2-92b7-4817-a5f3-0c575361f745");

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .id(VALID_ID)
                .point(10)
                .reviewList(List.of())
                .build();

        given(userService.getUser(VALID_ID)).willReturn(user);

        given(userService.getUser(INVALID_ID)).willThrow(new UserNotFoundException(INVALID_ID));

        given(userService.createUser()).willReturn(user);
    }

    @Test
    void getUserWithValidId() throws Exception {
        mvc.perform(get("/users/"+VALID_ID)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    void getUserWithInvalidId() throws Exception {
        mvc.perform(get("/users/"+INVALID_ID))
                .andExpect(status().isNotFound());

    }

    @Test
    void createUser() throws Exception {
        mvc.perform(post("/users"))
                .andExpect(status().isCreated());

    }

}
