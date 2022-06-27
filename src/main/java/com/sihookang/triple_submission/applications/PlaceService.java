package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.Place;
import com.sihookang.triple_submission.errors.PlaceNotFoundException;
import com.sihookang.triple_submission.infra.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;


    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Place getPlace(UUID id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(id));
    }
}
