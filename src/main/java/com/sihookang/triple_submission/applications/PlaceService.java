package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.Place;
import com.sihookang.triple_submission.errors.PlaceNotFoundException;
import com.sihookang.triple_submission.infra.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 장소와 관련된 내부 처리를 담당하는 클래스입니다.
 */
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    /**
     * parameter로 입력된 찾으려고 하는 객체의 id와 같은 id를 가진 객체를 반환합니다.
     * @param id  찾으려고 하는 객체의 id
     * @return 장소 객체
     */
    public Place getPlace(UUID id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(id));
    }

    public Place createPlace() {
        return placeRepository.save(new Place());
    }
}
