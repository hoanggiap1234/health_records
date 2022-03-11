package com.viettel.etc.services;

import com.viettel.etc.dto.MedicalHealthcareServiceDTO;
import com.viettel.etc.utils.TeleCareException;
import org.springframework.security.core.Authentication;

import java.util.Optional;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Thu Aug 27 15:08:50 ICT 2020
 */
public interface MedicalHealthcareServiceService {


    public Object getHealthcareService(MedicalHealthcareServiceDTO itemParamsEntity, Authentication authentication) throws TeleCareException;

    Optional<MedicalHealthcareServiceDTO> getDetailHealthcareService(MedicalHealthcareServiceDTO dataParams);

    Optional<MedicalHealthcareServiceDTO> getDetailHealthcareServiceHistory(MedicalHealthcareServiceDTO dataParams);
}
