package com.viettel.etc.services;

import com.viettel.etc.dto.MedicalHealthcareHistoriesDTO;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

import java.util.Optional;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Tue Sep 22 16:35:41 ICT 2020
 */
public interface MedicalHealthcareHistoriesService {

    public ResultSelectEntity getHealthcareDrugs(MedicalHealthcareHistoriesDTO itemParamsEntity);

    Optional<MedicalHealthcareHistoriesDTO> getDetailHealthcareDrug(MedicalHealthcareHistoriesDTO itemParamsEntity);

    ResultSelectEntity getReExaminations(MedicalHealthcareHistoriesDTO itemParamsEntity, Authentication authentication) throws TeleCareException;
}