package com.sihookang.triple_submission.applications;

import com.sihookang.triple_submission.infra.MileageRepository;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;

@SpringBootTest
class MileageServiceTest {
    private MileageService mileageService;

    private final MileageRepository mileageRepository = mock(MileageRepository.class);

}
