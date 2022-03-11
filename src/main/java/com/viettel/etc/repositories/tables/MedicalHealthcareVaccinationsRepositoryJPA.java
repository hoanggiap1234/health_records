package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePresurgeryEntity;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcareVaccinationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Medical_healthcare_vaccinations
 * 
 * @author ToolGen
 * @date Thu Feb 04 09:45:44 ICT 2021
 */
@Repository
public interface MedicalHealthcareVaccinationsRepositoryJPA extends JpaRepository<MedicalHealthcareVaccinationsEntity, Long> {
    List<MedicalHealthcareVaccinationsEntity> findByPatientIdAndIsActiveAndIsDelete(Integer patientId, Integer isActive, Integer isDelete);

    MedicalHealthcareVaccinationsEntity findFirstByVaccineNameAndPhoneNumberAndInjectionDate(String vaccineName, String phoneNumber, Date injectionDate);
}