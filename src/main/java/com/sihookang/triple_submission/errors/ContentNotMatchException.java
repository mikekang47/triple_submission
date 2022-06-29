package com.sihookang.triple_submission.errors;

public class ContentNotMatchException extends RuntimeException {
    public ContentNotMatchException() {
        super("Content type Not Match!");
    }
}
