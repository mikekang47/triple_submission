package com.sihookang.triple_submission.controllers;

import com.sihookang.triple_submission.applications.PlaceService;
import com.sihookang.triple_submission.domain.Place;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/places")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/{id}")
    public Place detail(@PathVariable("id") UUID id) {
        return placeService.getPlace(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Place create() {
        return placeService.createPlace();
    }

}
