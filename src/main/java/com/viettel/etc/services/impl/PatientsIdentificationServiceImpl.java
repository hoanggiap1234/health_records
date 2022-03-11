package com.viettel.etc.services.impl;

import com.viettel.etc.dto.PatientsIdentificationDTO;
import com.viettel.etc.repositories.PatientsIdentificationRepository;
import com.viettel.etc.services.PatientsIdentificationService;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Mon Sep 28 17:02:37 ICT 2020
 */
@Service
public class PatientsIdentificationServiceImpl implements PatientsIdentificationService {

    @Autowired
    private PatientsIdentificationRepository patientsIdentificationRepository;


    /**
     * validate health insurance exits
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Optional<PatientsIdentificationDTO> autofillHealthInsuranceInfo(PatientsIdentificationDTO itemParamsEntity) throws TeleCareException {
        /*
        ==========================================================
        itemParamsEntity: params nguoi dung truyen len
        ==========================================================
        */
        if (StringUtils.isEmpty(itemParamsEntity.getHealthinsuranceNumber()) || !itemParamsEntity.getHealthinsuranceNumber().matches("^[a-zA-Z0-9]{15}$")) {
            FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUT_PARAMS_HEALTH_INSURANCE_NUMBER);
        }
        Optional<PatientsIdentificationDTO> dataResult = patientsIdentificationRepository.autofillHealthInsuranceInfo(itemParamsEntity);
        return dataResult;
    }
}
