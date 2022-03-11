package com.viettel.etc.repositories;

import com.viettel.etc.dto.MedicalHealthcareServiceDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class Repository Interface:
 *
 * @author toolGen
 * @date Thu Aug 27 15:08:50 ICT 2020
 */
public interface MedicalHealthcareServiceRepository {

    public ResultSelectEntity getHealthcareService(MedicalHealthcareServiceDTO itemParamsEntity);

    Optional<MedicalHealthcareServiceDTO> getDetailHealthcareService(MedicalHealthcareServiceDTO dataParams);

    Optional<MedicalHealthcareServiceDTO> getDetailHealthcareServiceHistory(MedicalHealthcareServiceDTO dataParams);

    List<MedicalHealthcareServiceDTO> getMedicalHealthcareServicesResults(MedicalHealthcareServiceDTO dataParams);

    List<MedicalHealthcareServiceDTO> getMedicalHealthcareHistoriesAttachments(MedicalHealthcareServiceDTO dataParams);

    List<MedicalHealthcareServiceDTO> getListDetailHealthcareServiceByHistory(MedicalHealthcareServiceDTO dataParams);
}
