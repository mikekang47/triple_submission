package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.AttachedPhoto;
import com.sihookang.triple_submission.domain.Place;
import com.sihookang.triple_submission.domain.Review;
import com.sihookang.triple_submission.domain.User;
import com.sihookang.triple_submission.errors.ReviewNotFoundException;
import com.sihookang.triple_submission.infra.ReviewRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review getReview(UUID id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    public Review createReview(String content, List<AttachedPhoto> attachedPhotoList, User user, Place place) {
        Review review = new Review();
        
        review.setPlace(place);
        review.setUser(user);
        attachedPhotoList.forEach(review::addPhoto);
        review.setContent(content);
        user.addReview(review);

        return reviewRepository.save(review);
    }
}
