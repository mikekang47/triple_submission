package com.sihookang.triple_submission.controllers;

import com.sihookang.triple_submission.applications.AttachedPhotoService;
import com.sihookang.triple_submission.applications.PlaceService;
import com.sihookang.triple_submission.applications.ReviewService;
import com.sihookang.triple_submission.applications.UserService;
import com.sihookang.triple_submission.domain.AttachedPhoto;
import com.sihookang.triple_submission.domain.Place;
import com.sihookang.triple_submission.domain.Review;
import com.sihookang.triple_submission.domain.User;
import com.sihookang.triple_submission.dto.ReviewData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final PlaceService placeService;
    private final AttachedPhotoService attachedPhotoService;
    private final UserService userService;

    public ReviewController(ReviewService reviewService, PlaceService placeService, AttachedPhotoService attachedPhotoService, UserService userService) {
        this.reviewService = reviewService;
        this.placeService = placeService;
        this.attachedPhotoService = attachedPhotoService;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review create(@RequestBody ReviewData reviewData) {
        String content = reviewData.getContent();
        List<AttachedPhoto> attachedPhotoList = attachedPhotoService.getPhotos(reviewData.getAttachedPhotoIds());
        User user = userService.getUser(reviewData.getUserId());
        Place place = placeService.getPlace(reviewData.getPlaceId());
        return reviewService.createReview(content, attachedPhotoList, user, place);
    }

}
