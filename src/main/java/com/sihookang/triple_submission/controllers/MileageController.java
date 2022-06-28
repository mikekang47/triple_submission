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

/**
 * Client의 요청을 처리하는 클래스 입니다.
 */
@RestController
@RequestMapping("/events")
public class MileageController {
    private final MileageService mileageService;
    private UserService userService;

    public MileageController(MileageService mileageService) {
        this.mileageService = mileageService;
    }

    /**
     * 마일리지 적립에 관련된 데이터를 받아와 마일리지를 적립합니다.
     * @param mileageData 마일리지 데이터
     * @return 적립된 객체
     */
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
