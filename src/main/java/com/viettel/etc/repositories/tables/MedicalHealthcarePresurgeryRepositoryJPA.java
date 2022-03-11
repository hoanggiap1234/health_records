package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePresurgeryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Medical_healthcare_presurgery
 *
 * @author ToolGen
 * @date Tue Sep 22 11:31:19 ICT 2020
 */
@Repository
public interface MedicalHealthcarePresurgeryRepositoryJPA extends JpaRepository<MedicalHealthcarePresurgeryEntity, Integer> {

    List<MedicalHealthcarePresurgeryEntity> findByPatientIdAndIsActiveAndIsDelete(Integer patientId, Integer isActive, Integer isDelete);

    List<MedicalHealthcarePresurgeryEntity> findByPatientIdAndRelationshipIdAndIsActiveAndIsDelete(Integer patientId, Integer relationshipId, Integer isActive, Integer isDelete);
}
