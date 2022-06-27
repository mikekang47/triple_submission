package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.*;
import com.sihookang.triple_submission.infra.MileageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MileageService {
    private final MileageRepository mileageRepository;

    public MileageService(MileageRepository mileageRepository) {
        this.mileageRepository = mileageRepository;
    }

    public Mileage createMileage() {
        
        return null;
    }
}
