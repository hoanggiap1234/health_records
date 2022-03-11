package com.viettel.etc.services;

import com.viettel.etc.dto.MedicalHealthcarePrehistoricDTO;
import com.viettel.etc.utils.TeleCareException;
import org.springframework.security.core.Authentication;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Thu Sep 17 17:27:43 ICT 2020
 */
public interface MedicalHealthcarePrehistoricService {

    Object getPatientPrehistoric(MedicalHealthcarePrehistoricDTO itemParamsEntity, Authentication authentication, Integer patientId) throws TeleCareException;
}
