package com.viettel.etc.services.impl;

import com.viettel.etc.dto.MedicalHealthcareServiceDTO;
import com.viettel.etc.repositories.MedicalHealthcareServiceRepository;
import com.viettel.etc.services.MedicalHealthcareServiceService;
import com.viettel.etc.services.tables.PatientsServiceJPA;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Thu Aug 27 15:08:50 ICT 2020
 */
@Service
public class MedicalHealthcareServiceServiceImpl implements MedicalHealthcareServiceService{

    @Autowired
    private MedicalHealthcareServiceRepository medicalHealthcareServiceRepository;

    @Autowired
    private PatientsServiceJPA patientsServiceJPA;


    /**
     *
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getHealthcareService(MedicalHealthcareServiceDTO itemParamsEntity, Authentication authentication) throws TeleCareException {
        // serve demo
//        Integer currentPatientId = patientsServiceJPA.getUserIdFromToken(authentication);
//        Boolean hasRelationship = patientsServiceJPA.checkRelationship(itemParamsEntity.getPatientId(), currentPatientId);
//        if (!hasRelationship) {
//            FnCommon.throwsErrorApp(ErrorApp.ERR_PATIENT_RELATIONSHIP_NOT_EXIST);
//        }
        ResultSelectEntity dataResult = medicalHealthcareServiceRepository.getHealthcareService(itemParamsEntity);
        return dataResult;
    }

    @Override
    public Optional<MedicalHealthcareServiceDTO> getDetailHealthcareService(MedicalHealthcareServiceDTO dataParams) {
        Optional<MedicalHealthcareServiceDTO> resultData = medicalHealthcareServiceRepository.getDetailHealthcareService(dataParams);
        if (resultData.isPresent()){
            // indexs
            List<MedicalHealthcareServiceDTO> indexs = medicalHealthcareServiceRepository.getMedicalHealthcareServicesResults(dataParams);
            resultData.get().setIndexs(indexs);

            // attachments
            List<MedicalHealthcareServiceDTO> attachments = medicalHealthcareServiceRepository.getMedicalHealthcareHistoriesAttachments(dataParams);
            resultData.get().setAttachments(attachments);
        }
        return resultData;
    }

    @Override
    public Optional<MedicalHealthcareServiceDTO> getDetailHealthcareServiceHistory(MedicalHealthcareServiceDTO dataParams) {
        return medicalHealthcareServiceRepository.getDetailHealthcareServiceHistory(dataParams);
    }
}
