package com.viettel.etc.services;

import com.viettel.etc.dto.MedicalHealthcareHistoryDTO;
import com.viettel.etc.utils.TeleCareException;
import org.springframework.security.core.Authentication;

import java.util.Optional;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Thu Aug 27 11:39:10 ICT 2020
 */
public interface MedicalHealthcareHistoryService {


    Object getHealthcareHistory(MedicalHealthcareHistoryDTO itemParamsEntity, Authentication authentication) throws TeleCareException;

    Optional<MedicalHealthcareHistoryDTO> getDetailHealthcareHistory(MedicalHealthcareHistoryDTO dataParams);
}
