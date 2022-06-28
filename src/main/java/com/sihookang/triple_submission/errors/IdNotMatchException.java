package com.sihookang.triple_submission.errors;

public class IdNotMatchException extends RuntimeException {
    public IdNotMatchException() {
        super("Id not Match each other");
    }
}
