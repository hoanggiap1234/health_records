package com.viettel.etc.services.impl;

import com.viettel.etc.dto.*;
import com.viettel.etc.repositories.DrugsRepository;
import com.viettel.etc.repositories.MedicalHealthcareHistoryRepository;
import com.viettel.etc.repositories.MedicalHealthcarePatientDetailRepository;
import com.viettel.etc.repositories.MedicalHealthcareServiceRepository;
import com.viettel.etc.services.MedicalHealthcareHistoryService;
import com.viettel.etc.services.tables.PatientsServiceJPA;
import com.viettel.etc.utils.Constants;
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
 * @date Thu Aug 27 11:39:10 ICT 2020
 */
@Service
public class MedicalHealthcareHistoryServiceImpl implements MedicalHealthcareHistoryService{

    @Autowired
    private MedicalHealthcareHistoryRepository medicalHealthcareHistoryRepository;

    @Autowired
    private PatientsServiceJPA patientsServiceJPA;

    @Autowired
    private MedicalHealthcarePatientDetailRepository medicalHealthcarePatientDetailRepository;

    @Autowired
    private MedicalHealthcareServiceRepository medicalHealthcareServiceRepository;

    @Autowired
    private DrugsRepository drugsRepository;

    /**
     *
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getHealthcareHistory(MedicalHealthcareHistoryDTO itemParamsEntity, Authentication authentication) throws TeleCareException {
        // bo check theo luong demo
//        Integer currentPatientId = patientsServiceJPA.getUserIdFromToken(authentication);
//        Boolean hasRelationship = patientsServiceJPA.checkRelationship(itemParamsEntity.getPatientId(), currentPatientId);
//        if (!hasRelationship) {
//            FnCommon.throwsErrorApp(ErrorApp.ERR_PATIENT_RELATIONSHIP_NOT_EXIST);
//        }
        ResultSelectEntity dataResult = medicalHealthcareHistoryRepository.getHealthcareHistory(itemParamsEntity);
        return dataResult;
    }

    @Override
    public Optional<MedicalHealthcareHistoryDTO> getDetailHealthcareHistory(MedicalHealthcareHistoryDTO dataParams) {
        Optional<MedicalHealthcareHistoryDTO> resultDataOpt = medicalHealthcareHistoryRepository.getDetailHealthcareHistory(dataParams);

        if (resultDataOpt.isPresent()) {
            // MedicalHealthcareHistoryAttachment
            MedicalHealthcareHistoryAttachmentDTO medicalHealthcareHistoryAttachmentDTO = new MedicalHealthcareHistoryAttachmentDTO();
            medicalHealthcareHistoryAttachmentDTO.setHistoriesId(dataParams.getHistoriesId());
            ResultSelectEntity resultSelectAttachment = medicalHealthcareHistoryRepository.getMedicalHealthcareHistoryAttachments(medicalHealthcareHistoryAttachmentDTO);
            resultDataOpt.get().setAttachments((List<MedicalHealthcareHistoryAttachmentDTO>) resultSelectAttachment.getListData());

            // livingFunction
            MedicalHealthcarePatientDetailDTO medicalHealthcarePatientDetailDTO = new MedicalHealthcarePatientDetailDTO();
            medicalHealthcarePatientDetailDTO.setHistoriesId(dataParams.getHistoriesId());
            Optional<MedicalHealthcarePatientDetailDTO> medicalHealthcarePatientDetailDTOOpt = medicalHealthcarePatientDetailRepository.getMedicalHealthcarePatientDetailHistory(medicalHealthcarePatientDetailDTO);
            if (medicalHealthcarePatientDetailDTOOpt.isPresent()) {
                resultDataOpt.get().setLivingFunction(medicalHealthcarePatientDetailDTOOpt.get());
            }

            // subclinical
            MedicalHealthcareServiceDTO medicalHealthcareServiceDTO = new MedicalHealthcareServiceDTO();
            medicalHealthcareServiceDTO.setHistoriesId(dataParams.getHistoriesId());
            List<MedicalHealthcareServiceDTO> medicalHealthcareServices = medicalHealthcareServiceRepository.getListDetailHealthcareServiceByHistory(medicalHealthcareServiceDTO);
            resultDataOpt.get().setMedicalServices(medicalHealthcareServices);

            // drugs
            DrugsDTO drugsDTO = new DrugsDTO();
            drugsDTO.setHistoriesId(dataParams.getHistoriesId());
            drugsDTO.setStartrecord(Constants.START_RECORD_DEFAULT);
            drugsDTO.setPagesize(Integer.MAX_VALUE);
            ResultSelectEntity resultSelectEntity = (ResultSelectEntity) drugsRepository.getDrugs(drugsDTO);
            if(resultSelectEntity!=null) {
                resultDataOpt.get().setDrugs((List<DrugsDTO>) resultSelectEntity.getListData());
            }
        }
        return resultDataOpt;
    }
}
