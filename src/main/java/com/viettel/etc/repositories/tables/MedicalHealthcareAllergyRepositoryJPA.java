package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.MedicalHealthcareAllergyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Medical_healthcare_allergy
 * 
 * @author ToolGen
 * @date Tue Sep 22 10:56:18 ICT 2020
 */
@Repository
public interface MedicalHealthcareAllergyRepositoryJPA extends JpaRepository<MedicalHealthcareAllergyEntity, Integer> {

    List<MedicalHealthcareAllergyEntity> findByPatientIdOrPhoneNumberAndIsActiveAndIsDelete(Integer patientId, String phoneNumber, Integer isActive, Integer isDelete);

    List<MedicalHealthcareAllergyEntity> findByPatientIdAndRelationshipIdAndIsActiveAndIsDelete(Integer patientId, Integer relationshipId, Integer isActive, Integer isDelete);
}
