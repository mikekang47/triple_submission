package com.sihookang.triple_submission.controllers;


import com.sihookang.triple_submission.applications.*;
import com.sihookang.triple_submission.domain.*;
import com.sihookang.triple_submission.dto.MileageData;
import com.sihookang.triple_submission.errors.ActionNotFoundException;
import com.sihookang.triple_submission.errors.ContentNotMatchException;
import com.sihookang.triple_submission.errors.InvalidTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

/**
 * Client의 요청을 처리하는 클래스 입니다.
 */
@RestController
@RequestMapping("/events")
public class MileageController {
    private final MileageService mileageService;
    private final UserService userService;
    private final ReviewService reviewService;
    private final PlaceService placeService;
    private final AttachedPhotoService attachedPhotoService;

    public MileageController(MileageService mileageService, UserService userService, ReviewService reviewService, PlaceService placeService, AttachedPhotoService attachedPhotoService) {
        this.mileageService = mileageService;
        this.userService = userService;
        this.reviewService = reviewService;
        this.placeService = placeService;
        this.attachedPhotoService = attachedPhotoService;
    }

    /**
     * 마일리지 적립에 관련된 데이터를 받아와 마일리지를 적립합니다.
     * @param mileageData 마일리지 데이터
     * @return 적립된 객체
     */
    @PostMapping
    public ResponseEntity<Mileage> create(@RequestBody @Valid MileageData mileageData) {
        String type = mileageData.getType();
        if(!type.equals("REVIEW")) {
            throw new InvalidTypeException(type);
        }
        User user = userService.getUser(mileageData.getUserId());
        Review review = reviewService.getReview(mileageData.getReviewId());
        Place place = placeService.getPlace(mileageData.getPlaceId());
        List<AttachedPhoto> photoList = attachedPhotoService.getPhotos(mileageData.getAttachedPhotoIds());
        if(!review.getContent().equals(mileageData.getContent())) {
            throw new ContentNotMatchException();
        }
        String action = mileageData.getAction();

        switch (action) {
            case "ADD":
                mileageService.createMileage(user, review, place, photoList);
                return new ResponseEntity<>(HttpStatus.CREATED);
            case "MOD":
                mileageService.modifyMileage(user, review, place, photoList);
                return new ResponseEntity<>(HttpStatus.OK);
            case "DELETE":
                mileageService.deleteMileage(user, review, place);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            default:
                throw new ActionNotFoundException(action);
        }
    }
}
