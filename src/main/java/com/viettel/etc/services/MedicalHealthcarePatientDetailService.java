package com.viettel.etc.services;

import com.viettel.etc.dto.MedicalHealthcarePatientDetailDTO;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientDetailEntity;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

import java.util.Optional;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Thu Sep 10 16:04:21 ICT 2020
 */
public interface MedicalHealthcarePatientDetailService {

	Optional<MedicalHealthcarePatientDetailDTO> getMedicalHealthcarePatientDetailHistory(MedicalHealthcarePatientDetailDTO itemParamsEntity);

	ResultSelectEntity getHealthIndexLatest(MedicalHealthcarePatientDetailDTO dataParams) throws TeleCareException;

	MedicalHealthcarePatientDetailEntity delete(MedicalHealthcarePatientDetailDTO dataParams, Authentication authentication) throws TeleCareException;

	MedicalHealthcarePatientDetailEntity createHealthIndex(MedicalHealthcarePatientDetailDTO dataParams, Authentication authentication) throws Exception;

	MedicalHealthcarePatientDetailEntity updateHealthIndex(MedicalHealthcarePatientDetailDTO dataParams, Authentication authentication) throws Exception;
}
