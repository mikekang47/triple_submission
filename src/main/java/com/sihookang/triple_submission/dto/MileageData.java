package com.sihookang.triple_submission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MileageData {
    private String type;

    private String action;

    private UUID reviewId;

    private String content;

    private List<UUID> attachedPhotoIds;

    private UUID userId;

    private UUID placeId;
}
