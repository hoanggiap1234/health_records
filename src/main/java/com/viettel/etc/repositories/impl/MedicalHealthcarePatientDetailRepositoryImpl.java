package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.IndexType;
import com.viettel.etc.dto.MedicalHealthcarePatientDetailDTO;
import com.viettel.etc.repositories.MedicalHealthcarePatientDetailRepository;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

/**
 * Autogen class Repository Impl:
 *
 * @author ToolGen
 * @date Thu Sep 10 16:04:21 ICT 2020
 */
@Repository
public class MedicalHealthcarePatientDetailRepositoryImpl extends CommonDataBaseRepository implements MedicalHealthcarePatientDetailRepository {

	/**
	 * api get medical healthcare patient detail by history
	 *
	 * @param itemParamsEntity: params client truyen len
	 * @return
	 */
	@Override
	public Optional<MedicalHealthcarePatientDetailDTO> getMedicalHealthcarePatientDetailHistory(MedicalHealthcarePatientDetailDTO itemParamsEntity) {
		StringBuilder sql = new StringBuilder();
		HashMap<String, Object> hmapParams = new HashMap<>();
		sql.append(" SELECT  ");
		sql.append(" detail.detail_id AS detailId, ");
		sql.append(" detail.patient_id AS patientId, ");
		sql.append(" detail.pulse AS pulse, ");
		sql.append(" detail.temperature AS temperature, ");
		sql.append(" detail.blood_pressure_min AS bloodPressureMin, ");
		sql.append(" detail.blood_pressure_max AS bloodPressureMax, ");
		sql.append(" detail.breath AS breath, ");
		sql.append(" detail.weight AS weight, ");
		sql.append(" detail.height AS height, ");
		sql.append(" detail.bmi AS bmi, ");
		sql.append(" detail.waist_circumference AS waistCircumference ");
		sql.append(" FROM medical_healthcare_patient_detail AS detail ");
		sql.append("   WHERE detail.is_delete = 0");
		sql.append("     AND detail.is_active = 1");
		sql.append("     AND detail.histories_id = :historiesId");

		hmapParams.put("historiesId", itemParamsEntity.getHistoriesId());

		MedicalHealthcarePatientDetailDTO resultData = (MedicalHealthcarePatientDetailDTO) getFirstData(sql, hmapParams, MedicalHealthcarePatientDetailDTO.class);
		return Optional.ofNullable(resultData);
	}

	@Override
	public ResultSelectEntity getHealthIndexLatest(MedicalHealthcarePatientDetailDTO dataParams) throws TeleCareException {
		StringBuilder sql = new StringBuilder();
		HashMap<String, Object> hmapParams = new HashMap<>();

		sql.append(" SELECT   ");
		sql.append(" detail.detail_id AS detailId,  ");
		sql.append(" detail.patient_id AS patientId,  ");
		sql.append(" detail.blood_sugar AS bloodSugar, ");
		sql.append(" detail.spo2_score AS spo2Score, ");
		sql.append(" detail.blood_pressure_min AS bloodPressureMin, ");
		sql.append(" detail.blood_pressure_max AS bloodPressureMax, ");
		sql.append(" detail.heart_beat AS  heartBeat, ");
		sql.append(" detail.temperature AS temperature, ");
		sql.append(" detail.height AS height, ");
		sql.append(" detail.weight AS weight, ");
		sql.append(" detail.bmi AS bmi, ");
		sql.append(" detail.result_date AS resultDate, ");
		sql.append(" detail.is_sync AS isSync ");
		sql.append(" FROM medical_healthcare_patient_detail AS detail  ");
		sql.append(" WHERE detail.is_delete = 0 ");
		sql.append(" AND detail.is_active = 1 ");

		if (Objects.nonNull(dataParams.getPatientId())) {
			sql.append(" AND detail.patient_id = :patientId ");
			hmapParams.put("patientId", dataParams.getPatientId());
		}

		if (Objects.nonNull(dataParams.getIndexType())) {
			String column = IndexType.valueOfIgnoreCase(dataParams.getIndexType()).getColumn();
			if(!column.isEmpty()){
				if(column.equals(IndexType.BLOOD_SUGAR.getColumn())) column = "blood_sugar";
				if(column.equals(IndexType.SPO2_SCORE.getColumn())) column = "spo2_score";
				if(column.equals(IndexType.HEART_BEAT.getColumn())) column = "heart_beat";
				if(column.equals(IndexType.BLOOD_PRESSURE.getColumn())) column = "blood_pressure_min";
			}
			sql.append(" AND detail." + column + " IS NOT NULL ");
			sql.append(" AND detail." + column + " >0 ");
		}

		sql.append(" ORDER BY detail.result_date DESC ");

		Integer startPage = Objects.nonNull(dataParams.getStartrecord()) ? dataParams.getStartrecord() : Constants.START_RECORD_DEFAULT;

		Integer pageSize = Objects.nonNull(dataParams.getPagesize()) ? dataParams.getPagesize() : Constants.PAGE_SIZE_DEFAULT;

		ResultSelectEntity resultSelectEntity = getListDataAndCount(sql, hmapParams, startPage, pageSize, MedicalHealthcarePatientDetailDTO.class);
		return resultSelectEntity;
	}
}
