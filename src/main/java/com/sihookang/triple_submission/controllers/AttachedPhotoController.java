package com.sihookang.triple_submission.controllers;

import com.sihookang.triple_submission.applications.AttachedPhotoService;
import com.sihookang.triple_submission.domain.AttachedPhoto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/photos")
public class AttachedPhotoController {
    private AttachedPhotoService photoService;

    public AttachedPhotoController(AttachedPhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/{id}")
    public AttachedPhoto detail(@PathVariable("id") UUID id) {
        return photoService.getPhoto(id);
    }

    

}
