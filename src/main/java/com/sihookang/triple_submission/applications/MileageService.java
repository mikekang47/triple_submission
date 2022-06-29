package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.*;
import com.sihookang.triple_submission.errors.IdNotMatchException;
import com.sihookang.triple_submission.errors.MileageAlreadyExistsException;
import com.sihookang.triple_submission.errors.MileageNotFoundException;
import com.sihookang.triple_submission.infra.MileageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Mileage에 관련한 내부 처리를 담당하고 있는 클래스 입니다.
 */

@Slf4j
@Service
@Transactional
public class MileageService {
    private final MileageRepository mileageRepository;

    public MileageService(MileageRepository mileageRepository) {
        this.mileageRepository = mileageRepository;
    }

    public Mileage getMileage(UUID id) {
        return mileageRepository.findById(id)
                .orElseThrow(MileageNotFoundException::new);
    }


    public Mileage createMileage(User user, Review review, Place place, List<AttachedPhoto> photoList) {
        if(user.getId() != review.getUser().getId() || place.getId() != review.getPlace().getId()) {
            throw new IdNotMatchException();
        }

        if(checkMileage(review.getId())) {
            throw new MileageAlreadyExistsException();
        }
        int point = 0;
        Mileage mileage = new Mileage();

        if(review.getContent().length() >= 1) {
            log.info("사용자 {}의 포인트가 컨텐츠 길이로 인해 1 증가 했습니다.", user.getId());
            point += 1;
        }
        if(photoList.size() >= 1) {
            log.info("사용자 {}의 포인트가 첨부 사진 개수로 인해 1 증가 했습니다.", user.getId());
            point += 1;
        }

        if(place.getReviewList().size() == 1) {
            point += 1;
        }

        user.setPoint(point);
        mileage.setPoint(point);
        mileage.setReview(review);
        mileage.setType("REVIEW");
        return mileageRepository.save(mileage);
    }


    public Mileage modifyMileage(User user, Review review, Place place, List<AttachedPhoto> photoList) {
        Mileage mileage = findMileage(review.getId());
        Integer point = user.getPoint();
        Integer mileagePoint = mileage.getPoint();

        if(photoList.size() <= 0) {
           point -= 1;
           mileagePoint -= 1;
            log.info("사용자 {}의 포인트가 첨부 사진 개수 변경으로 인해 1 감소 했습니다.", user.getId());
        }

        if(review.getContent().length() <= 1) {
            point -= 1;
            log.info("사용자 {}의 포인트가 리뷰 내용 길이 변경으로 인해 1 감소 했습니다.", user.getId());
            mileagePoint -= 1;
        }

        user.setPoint(point);
        review.setUser(user);
        review.setAttachedPhotoList(photoList);
        review.setPlace(place);
        mileage.setReview(review);
        mileage.setPoint(mileagePoint);
        
        return mileage;
    }

    public void deleteMileage(User user, Review review, Place place) {
        Integer point = user.getPoint();
        if(review.getAttachedPhotoList().size() >= 1) {
            log.info("사용자 {}의 포인트가 사진 삭제로 인해 1 감소 했습니다.", user.getId());
            point -= 1;
        }

        if(place.getReviewList().size() <= 1) {
            log.info("사용자 {}의 포인트가 첫 장소 리뷰 삭제로 인해 1 감소 했습니다.", user.getId());
            point -= 1;
        }


        if(review.getContent().length() >= 1) {
            log.info("사용자 {}의 포인트가 리뷰 내용 삭제로 인해 1 감소 했습니다.", user.getId());
            point -= 1;
        }
        user.setPoint(point);
        mileageRepository.deleteByReviewId(review.getId());
    }

    private Mileage findMileage(UUID reviewId) {
        return mileageRepository.findByReviewId(reviewId)
                .orElseThrow(MileageNotFoundException::new);
    }

    private boolean checkMileage(UUID reviewId) {
        return mileageRepository.existsByReviewId(reviewId);

    }



}
