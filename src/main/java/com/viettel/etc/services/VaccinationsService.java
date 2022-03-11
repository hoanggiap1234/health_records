package com.viettel.etc.services;

import com.viettel.etc.dto.VaccinationsDTO;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.Optional;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Thu Aug 27 09:47:44 ICT 2020
 */
public interface VaccinationsService {
    

    public ResultSelectEntity getVaccinationsHistory(VaccinationsDTO itemParamsEntity, Integer patientId, Authentication authentication) throws TeleCareException;

    Optional<VaccinationsDTO> getDetailVaccinationsHistory(VaccinationsDTO itemParamsEntity);

    Object getVaccinationsCovidHistory(VaccinationsDTO dataParams, Integer patientId, Authentication authentication) throws TeleCareException, IOException;
}