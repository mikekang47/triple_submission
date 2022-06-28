package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.Review;
import com.sihookang.triple_submission.errors.ReviewNotFoundException;
import com.sihookang.triple_submission.infra.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review getReview(UUID id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }
}
