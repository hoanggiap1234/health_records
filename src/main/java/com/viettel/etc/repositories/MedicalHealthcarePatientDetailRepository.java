package com.viettel.etc.repositories;

import com.viettel.etc.dto.MedicalHealthcarePatientDetailDTO;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

import java.util.Optional;

/**
 * Autogen class Repository Interface:
 *
 * @author toolGen
 * @date Thu Sep 10 16:04:21 ICT 2020
 */
public interface MedicalHealthcarePatientDetailRepository {

    Optional<MedicalHealthcarePatientDetailDTO> getMedicalHealthcarePatientDetailHistory(MedicalHealthcarePatientDetailDTO itemParamsEntity);

    ResultSelectEntity getHealthIndexLatest(MedicalHealthcarePatientDetailDTO dataParams) throws TeleCareException;
}
