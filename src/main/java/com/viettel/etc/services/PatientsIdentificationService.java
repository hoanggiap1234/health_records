package com.viettel.etc.services;

import com.viettel.etc.dto.PatientsIdentificationDTO;
import com.viettel.etc.utils.TeleCareException;

import java.util.Optional;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Mon Sep 28 17:02:37 ICT 2020
 */
public interface PatientsIdentificationService {

    Optional<PatientsIdentificationDTO> autofillHealthInsuranceInfo(PatientsIdentificationDTO itemParamsEntity) throws TeleCareException;
}