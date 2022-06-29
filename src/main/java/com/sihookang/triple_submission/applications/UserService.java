package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.User;
import com.sihookang.triple_submission.errors.UserNotFoundException;
import com.sihookang.triple_submission.infra.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User와 관련된 내부 처리를 담당하는 클래스입니다.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * parameter로 입력된 찾으려고 하는 객체의 id와 같은 id를 가진 객체를 반환합니다.
     * @param valid_id  찾으려고 하는 객체의 id
     * @return  사용자 객체
     */
    public User getUser(UUID valid_id) {
        return userRepository.findById(valid_id)
                .orElseThrow(() -> new UserNotFoundException(valid_id));
    }

    public User createUser() {
        return userRepository.save(new User());
    }
    
}
