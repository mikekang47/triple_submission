package com.sihookang.triple_submission.controllers;


import com.sihookang.triple_submission.applications.MileageService;
import com.sihookang.triple_submission.applications.UserService;
import com.sihookang.triple_submission.domain.Mileage;
import com.sihookang.triple_submission.domain.User;
import com.sihookang.triple_submission.dto.MileageData;
import com.sihookang.triple_submission.errors.InvalidTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/events")
public class MileageController {
    private final MileageService mileageService;
    private UserService userService;

    public MileageController(MileageService mileageService) {
        this.mileageService = mileageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mileage create(@RequestBody @Valid MileageData mileageData) {
        String type = mileageData.getType();
        if(!type.equals("REVIEW")) {
            throw new InvalidTypeException(type);
        }
        User user = userService.getUser(mileageData.getUserId());

        return new Mileage();
    }
}
