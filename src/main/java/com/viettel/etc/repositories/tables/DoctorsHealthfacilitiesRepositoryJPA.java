package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.DoctorsHealthfacilitiesEntity;
import com.viettel.etc.utils.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorsHealthfacilitiesRepositoryJPA extends JpaRepository<DoctorsHealthfacilitiesEntity, Integer> {


	@Query("select d.doctorId from DoctorsHealthfacilitiesEntity d where d.healthfacilitiesCode in ?1")
	List<Integer> getDoctorIdsByHealthficility(List<String> codes);

	Boolean existsByDoctorIdAndHealthfacilitiesCodeAndIsActiveAndIsDelete(Integer doctorId, String healthfacilitiesCode, Boolean isActive, Boolean isDelete);
}
