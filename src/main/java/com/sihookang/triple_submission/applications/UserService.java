package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.User;
import com.sihookang.triple_submission.errors.UserNotFoundException;
import com.sihookang.triple_submission.infra.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(UUID valid_id) {
        return userRepository.findById(valid_id)
                .orElseThrow(() -> new UserNotFoundException(valid_id));
    }
}
