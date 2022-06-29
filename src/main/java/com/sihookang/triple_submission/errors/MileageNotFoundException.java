package com.sihookang.triple_submission.errors;

public class MileageNotFoundException extends RuntimeException {
    public MileageNotFoundException() {
        super("Mileage Not Found");
    }
}
