package com.viettel.etc.services.tables;

import com.viettel.etc.dto.MedicalHealthcareAllergyDTO;
import com.viettel.etc.repositories.tables.MedicalHealthcareAllergyRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcareAllergyEntity;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ValueRange;
import java.util.Objects;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Medical_healthcare_allergy
 *
 * @author ToolGen
 * @date Tue Sep 22 10:56:18 ICT 2020
 */
@Service
public class MedicalHealthcareAllergyServiceJPA {

    @Autowired
    MedicalHealthcareAllergyRepositoryJPA medical_healthcare_allergy;

//    public List<MedicalHealthcareAllergyEntity> findAll() {
//        return this.medical_healthcare_allergy.findAll();
//    }
//
//    public MedicalHealthcareAllergyEntity save(MedicalHealthcareAllergyEntity Medical_healthcare_allergy) {
//        return this.medical_healthcare_allergy.save(Medical_healthcare_allergy);
//    }
//
//    public Optional<MedicalHealthcareAllergyEntity> findById(Long id) {
//        return this.medical_healthcare_allergy.findById(id);
//    }
//
//    public void deleteById(Long id) {
//        this.medical_healthcare_allergy.deleteById(id);
//    }
//
//    public MedicalHealthcareAllergyEntity getOne(Long id) {
//        return this.medical_healthcare_allergy.getOne(id);
//    }
//
//    public Boolean existsById(Long id) {
//        return this.medical_healthcare_allergy.existsById(id);
//    }

    public Object createPatientAllergy(MedicalHealthcareAllergyDTO medicalHealthcareAllergyDTO) throws TeleCareException {
        if (medicalHealthcareAllergyDTO == null || medicalHealthcareAllergyDTO.getAllergyGroupId() == null || medicalHealthcareAllergyDTO.getRelationshipId() == null || medicalHealthcareAllergyDTO.getAllergyName() == null) {
            throw new TeleCareException(ErrorApp.ERR_DATA_MEDICAL_HEALTHCARE_ALLERGY_INFO);
        }
        medicalHealthcareAllergyDTO.setIsSync(Constants.IS_NOT_SYNC);
        medicalHealthcareAllergyDTO.setIsActive(Constants.IS_ACTIVE);
        medicalHealthcareAllergyDTO.setIsDelete(Constants.IS_NOT_DELETE);
        medicalHealthcareAllergyDTO.setCreateDate(Date.valueOf(LocalDate.now()));

        MedicalHealthcareAllergyEntity medicalHealthcareAllergyEntity = (MedicalHealthcareAllergyEntity) FnCommon.convertObjectToObject(medicalHealthcareAllergyDTO, MedicalHealthcareAllergyEntity.class);
        MedicalHealthcareAllergyEntity result = this.medical_healthcare_allergy.save(medicalHealthcareAllergyEntity);

        return FnCommon.convertObjectToObject(result, MedicalHealthcareAllergyDTO.class);
    }

    public Object updatePatientAllergy(MedicalHealthcareAllergyDTO dataParams, Authentication authentication) throws TeleCareException {
        if (Objects.nonNull(dataParams.getAllergyGroupId()) && !ValueRange.of(1, 5).isValidValue(dataParams.getAllergyGroupId())) {
            FnCommon.throwsErrorApp(ErrorApp.ERR_DATA_MEDICAL_HEALTHCARE_ALLERGY_INFO);
        }

        Optional<MedicalHealthcareAllergyEntity> entityOpt = medical_healthcare_allergy.findById(dataParams.getAllergyId());
        if (!entityOpt.isPresent()) {
            FnCommon.throwsErrorApp(ErrorApp.ERR_DATA_MEDICAL_HEALTHCARE_ALLERGY_INFO);
        }

        FnCommon.copyProperties(dataParams, entityOpt.get());
        String userId = FnCommon.getUserIdLogin(authentication);
        if (Objects.nonNull(userId) && userId.matches("[0-9]+")) {
            entityOpt.get().setUpdateUserId(Integer.valueOf(userId));
        }
        medical_healthcare_allergy.save(entityOpt.get());
        return entityOpt.get();
    }

    public void deletePatientAllergy(MedicalHealthcareAllergyDTO dataParams, Authentication authentication) throws TeleCareException {
        Optional<MedicalHealthcareAllergyEntity> entityOpt = medical_healthcare_allergy.findById(dataParams.getAllergyId());
        if (!entityOpt.isPresent()) {
            FnCommon.throwsErrorApp(ErrorApp.ERR_DATA_MEDICAL_HEALTHCARE_ALLERGY_INFO);
        }
        String userId = FnCommon.getUserIdLogin(authentication);
        if (Objects.nonNull(userId) && userId.matches("[0-9]+")) {
            entityOpt.get().setUpdateUserId(Integer.valueOf(userId));
        }
        entityOpt.get().setIsDelete(Constants.IS_DELETE);
        entityOpt.get().setIsActive(Constants.IS_NOT_ACTIVE);
        medical_healthcare_allergy.save(entityOpt.get());
    }

}
