package com.viettel.etc.repositories;

import com.viettel.etc.dto.MedicalHealthcareHistoryAttachmentDTO;
import com.viettel.etc.dto.MedicalHealthcareHistoryDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

import java.util.Optional;

/**
 * Autogen class Repository Interface:
 *
 * @author toolGen
 * @date Thu Aug 27 11:39:10 ICT 2020
 */
public interface MedicalHealthcareHistoryRepository {

    ResultSelectEntity getHealthcareHistory(MedicalHealthcareHistoryDTO itemParamsEntity);

    Optional<MedicalHealthcareHistoryDTO> getDetailHealthcareHistory(MedicalHealthcareHistoryDTO dataParams);

    ResultSelectEntity getMedicalHealthcareHistoryAttachments(MedicalHealthcareHistoryAttachmentDTO dataParams);
}
