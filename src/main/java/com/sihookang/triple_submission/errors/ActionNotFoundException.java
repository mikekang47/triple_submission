package com.sihookang.triple_submission.errors;

public class ActionNotFoundException extends RuntimeException {
    public ActionNotFoundException(String action) {
        super("Action not found : " + action);
    }
}
