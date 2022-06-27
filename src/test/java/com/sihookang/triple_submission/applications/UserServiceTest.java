package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.Review;
import com.sihookang.triple_submission.domain.User;
import com.sihookang.triple_submission.errors.UserNotFoundException;
import com.sihookang.triple_submission.infra.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserServiceTest {
    private UserService userService;

    private final UserRepository userRepository = mock(UserRepository.class);

    private final UUID VALID_ID = UUID.fromString("3ede0ef2-92b7-4817-a5f3-0c575361f745");
    private final UUID INVALID_ID = UUID.fromString("4ede0ef2-92b7-4817-a5f3-0c575361f745");
    private final UUID DELETED_ID = UUID.fromString("5ede0ef2-92b7-4817-a5f3-0c575361f745");

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
        
        User user = User.builder()
                .id(VALID_ID)
                .build();

        given(userRepository.findById(VALID_ID)).willReturn(Optional.of(user));

        given(userRepository.findById(DELETED_ID)).willThrow(UserNotFoundException.class);

    }

    @Test
    @DisplayName("올바른 id로 사용자를 조회하면 id와 일치하는 사용자를 반환한다.")
    void getUserWithValidId() {
        User user = userService.getUser(VALID_ID);

        assertThat(user.getId()).isEqualTo(VALID_ID);

    }

    @Test
    @DisplayName("올바르지 않은 id로 사용자를 조회하면 에러를 발생시킨다.")
    void getUserWithInvalidId() {
        assertThatThrownBy(() -> userService.getUser(INVALID_ID))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    @DisplayName("삭제된 사용자의 id로 사용자를 조회하면 에러를 발생시킨다.")
    void getUserWithDeletedId() {
        assertThatThrownBy(() -> userService.getUser(DELETED_ID))
                .isInstanceOf(UserNotFoundException.class);
    }
}
