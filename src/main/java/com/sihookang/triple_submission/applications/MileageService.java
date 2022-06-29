package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.*;
import com.sihookang.triple_submission.errors.IdNotMatchException;
import com.sihookang.triple_submission.errors.MileageAlreadyExistsException;
import com.sihookang.triple_submission.errors.MileageNotFoundException;
import com.sihookang.triple_submission.infra.MileageRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Mileage에 관련한 내부 처리를 담당하고 있는 클래스 입니다.
 */
@Service
@Transactional
public class MileageService {
    private final MileageRepository mileageRepository;

    public MileageService(MileageRepository mileageRepository) {
        this.mileageRepository = mileageRepository;
    }

    /**
     * 마일리지를 생성합니다.
     * @return
     * @param user
     * @param review
     * @param place
     * @param photoList
     */
    public Mileage createMileage(User user, Review review, Place place, List<AttachedPhoto> photoList) {
        if(user.getId() != review.getUser().getId() || place.getId() != review.getPlace().getId()) {
            throw new IdNotMatchException();
        }

        if(checkMileage(review.getId())) {
            throw new MileageAlreadyExistsException();
        }

        if(review.getContent().length() >= 1) {
            user.setPoint(user.getPoint() + 1);
        }
        if(photoList.size() >= 1) {
            user.setPoint(user.getPoint() + 1);
        }
        if(place.getReviewList().size() == 1) {
            user.setPoint(user.getPoint() + 1);
        }

        Mileage mileage = new Mileage();
        mileage.setReview(review);
        mileage.setType("REVIEW");
        return mileageRepository.save(mileage);
    }


    public Mileage modifyMileage(User user, Review review, Place place, List<AttachedPhoto> photoList) {
        Mileage mileage = findMileage(review.getId());
        if(photoList.size() <= 0) {
            user.setPoint(user.getPoint() - 1);
        }
        if(place.getReviewList().size() == 1) {
            user.setPoint(user.getPoint() + 1);
        }
        review.setUser(user);
        review.setAttachedPhotoList(photoList);
        review.setPlace(place);
        mileage.setReview(review);
        return mileage;
    }

    public void deleteMileage(User user, Review review, Place place) {
        if(review.getAttachedPhotoList().size() >= 1) {
            user.setPoint(user.getPoint() - 1);
        }

        if(place.getReviewList().size() <= 1) {
            user.setPoint(user.getPoint()-1);
        }

        mileageRepository.deleteByReviewId(review.getId());
    }

    private Mileage findMileage(UUID reviewId) {
        return mileageRepository.findByReviewId(reviewId)
                .orElseThrow(() -> new MileageNotFoundException());
    }

    private boolean checkMileage(UUID reviewId) {
        return mileageRepository.existsByReviewId(reviewId);

    }


}
