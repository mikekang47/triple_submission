package com.sihookang.triple_submission.dto;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class ReviewData {
    private String content;

    private List<UUID> attachedPhotoIds;

    private UUID userId;

    private UUID placeId;
}
