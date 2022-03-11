package com.viettel.etc.repositories;

import com.viettel.etc.dto.MedicalHealthcareHistoriesDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

import java.util.Optional;

/**
 * Autogen class Repository Interface:
 *
 * @author toolGen
 * @date Tue Sep 22 16:35:41 ICT 2020
 */
public interface MedicalHealthcareHistoriesRepository {


    ResultSelectEntity getHealthcareDrugs(MedicalHealthcareHistoriesDTO itemParamsEntity);

    Optional<MedicalHealthcareHistoriesDTO> getDetailHealthcareDrug(MedicalHealthcareHistoriesDTO itemParamsEntity);

    ResultSelectEntity getReExaminations(MedicalHealthcareHistoriesDTO itemParamsEntity, Long patientBookingId);
}