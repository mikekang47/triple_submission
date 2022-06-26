package com.sihookang.triple_submission.controllers;


import com.sihookang.triple_submission.applications.MileageService;
import com.sihookang.triple_submission.domain.Mileage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class MileageController {
    private final MileageService mileageService;

    public MileageController(MileageService mileageService) {
        this.mileageService = mileageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mileage create() {
        return null;
    }
}
