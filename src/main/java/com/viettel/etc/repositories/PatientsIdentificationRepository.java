package com.viettel.etc.repositories;

import com.viettel.etc.dto.PatientsIdentificationDTO;

import java.util.Optional;

/**
 * Autogen class Repository Interface:
 *
 * @author toolGen
 * @date Mon Sep 28 17:02:37 ICT 2020
 */
public interface PatientsIdentificationRepository {

    Optional<PatientsIdentificationDTO> autofillHealthInsuranceInfo(PatientsIdentificationDTO itemParamsEntity);
}