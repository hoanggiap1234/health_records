package com.viettel.etc.services.tables;

import com.viettel.etc.dto.MedicalHealthcarePresurgeryDTO;
import com.viettel.etc.repositories.tables.MedicalHealthcarePresurgeryRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePresurgeryEntity;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Medical_healthcare_presurgery
 *
 * @author ToolGen
 * @date Tue Sep 22 11:31:19 ICT 2020
 */
@Service
public class MedicalHealthcarePresurgeryServiceJPA {

    @Autowired
    MedicalHealthcarePresurgeryRepositoryJPA medical_healthcare_presurgery;

    public Object saveMedicalHealthcarePresurgery(MedicalHealthcarePresurgeryDTO medicalHealthcarePresurgeryDTO, Authentication authentication) throws TeleCareException {
        if (medicalHealthcarePresurgeryDTO == null || medicalHealthcarePresurgeryDTO.getSurgicalSurgeryName() == null || medicalHealthcarePresurgeryDTO.getHealthfacilitiesCode() == null || medicalHealthcarePresurgeryDTO.getYearOfSurgery() == null || medicalHealthcarePresurgeryDTO.getReasonOfSurgery() == null) {
            throw new TeleCareException(ErrorApp.ERR_DATA_MEDICAL_HEALTHCARE_PRESURGERY_INFO);
        }
        medicalHealthcarePresurgeryDTO.setIsSync(Constants.IS_NOT_SYNC);
        medicalHealthcarePresurgeryDTO.setIsActive(Constants.IS_ACTIVE);
        medicalHealthcarePresurgeryDTO.setIsDelete(Constants.IS_NOT_DELETE);
        medicalHealthcarePresurgeryDTO.setCreateDate(Date.valueOf(LocalDate.now()));

        MedicalHealthcarePresurgeryEntity medicalHealthcarePresurgeryEntity = (MedicalHealthcarePresurgeryEntity) FnCommon.convertObjectToObject(medicalHealthcarePresurgeryDTO, MedicalHealthcarePresurgeryEntity.class);
        MedicalHealthcarePresurgeryEntity result = this.medical_healthcare_presurgery.save(medicalHealthcarePresurgeryEntity);

        return FnCommon.convertObjectToObject(result, MedicalHealthcarePresurgeryDTO.class);
    }

    public Object updatePatientPresurgery(MedicalHealthcarePresurgeryDTO dataParams, Authentication authentication) throws TeleCareException {
        Optional<MedicalHealthcarePresurgeryEntity> entityOpt = medical_healthcare_presurgery.findById(dataParams.getPresurgeryId());
        if (!entityOpt.isPresent()) {
            FnCommon.throwsErrorApp(ErrorApp.ERR_DATA_MEDICAL_HEALTHCARE_PRESURGERY_INFO);
        }

        FnCommon.copyProperties(dataParams, entityOpt.get());
        String userId = FnCommon.getUserIdLogin(authentication);
        if (Objects.nonNull(userId) && userId.matches("[0-9]+")) {
            entityOpt.get().setUpdateUserId(Integer.valueOf(userId));
        }
        medical_healthcare_presurgery.save(entityOpt.get());
        return entityOpt.get();
    }

    public void deletePatientPresurgery(MedicalHealthcarePresurgeryDTO dataParams, Authentication authentication) throws TeleCareException {
        Optional<MedicalHealthcarePresurgeryEntity> entityOpt = medical_healthcare_presurgery.findById(dataParams.getPresurgeryId());
        if (!entityOpt.isPresent()) {
            FnCommon.throwsErrorApp(ErrorApp.ERR_DATA_MEDICAL_HEALTHCARE_PRESURGERY_INFO);
        }
        String userId = FnCommon.getUserIdLogin(authentication);
        if (Objects.nonNull(userId) && userId.matches("[0-9]+")) {
            entityOpt.get().setUpdateUserId(Integer.valueOf(userId));
        }
        entityOpt.get().setIsDelete(Constants.IS_DELETE);
        entityOpt.get().setIsActive(Constants.IS_NOT_ACTIVE);
        medical_healthcare_presurgery.save(entityOpt.get());
    }
}
