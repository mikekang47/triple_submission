package com.sihookang.triple_submission.errors;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID valid_id) {
        super("User Not Found :" + valid_id.toString());
    }
}
