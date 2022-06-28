package com.sihookang.triple_submission.errors;

import java.util.UUID;

public class PhotoNotFoundException extends RuntimeException{
    public PhotoNotFoundException(UUID id) {
        super("Photo Not Found :" + id);
    }
}
