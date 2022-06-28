package com.sihookang.triple_submission.errors;

import java.util.UUID;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(UUID id) {
        super("Review Not Found :"+ id);
    }
}
