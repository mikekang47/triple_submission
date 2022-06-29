package com.sihookang.triple_submission.errors;

public class InvalidTypeException extends RuntimeException {
    public InvalidTypeException(String type) {
        super("Undefined Type : " + type);
    }
}
