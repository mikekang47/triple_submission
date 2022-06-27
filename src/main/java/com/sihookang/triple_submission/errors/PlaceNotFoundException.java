package com.sihookang.triple_submission.errors;

import java.util.UUID;

public class PlaceNotFoundException extends RuntimeException{
    public PlaceNotFoundException(UUID id) {
        super("Place Not found" + id.toString());
    }
}
