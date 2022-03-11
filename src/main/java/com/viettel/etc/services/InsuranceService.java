package com.viettel.etc.services;

import com.viettel.etc.dto.InsuranceDTO;
import com.viettel.etc.utils.TeleCareException;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Tue Aug 18 10:12:25 ICT 2020
 */
public interface InsuranceService {
    

    public Object getInsuranceInfo(Integer patientId, InsuranceDTO itemParamsEntity, Authentication authentication) throws TeleCareException;
}