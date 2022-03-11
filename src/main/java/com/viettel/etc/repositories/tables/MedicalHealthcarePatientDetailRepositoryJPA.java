package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Medical_healthcare_patient_detail
 *
 * @author ToolGen
 * @date Thu Sep 10 16:04:22 ICT 2020
 */
@Repository
public interface MedicalHealthcarePatientDetailRepositoryJPA extends JpaRepository<MedicalHealthcarePatientDetailEntity, Integer> {
	@Query("select e from MedicalHealthcarePatientDetailEntity e where e.historiesId=?1 and e.isActive=true and e.isDelete=false ")
	MedicalHealthcarePatientDetailEntity findByHistoriesId(int historyId);

	List<MedicalHealthcarePatientDetailEntity> findByPatientIdAndIsActiveAndIsDelete(int patientId, boolean isActive, boolean isDelete);
}
