package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.domain.*;
import com.sihookang.triple_submission.infra.MileageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Mileage에 관련한 내부 처리를 담당하고 있는 클래스 입니다.
 */
@Service
public class MileageService {
    private final MileageRepository mileageRepository;

    public MileageService(MileageRepository mileageRepository) {
        this.mileageRepository = mileageRepository;
    }

    /**
     * 마일리지를 생성합니다.
     * @return
     */
    public Mileage createMileage() {
        
        return null;
    }
}
