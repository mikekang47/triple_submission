package com.sihookang.triple_submission.errors;


public class MileageAlreadyExistsException extends RuntimeException {
    public MileageAlreadyExistsException() {
        super("Mileage Already Exists");
    }
}
