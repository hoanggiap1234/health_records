package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.DrugsDTO;
import com.viettel.etc.repositories.DrugsRepository;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Repository
public class DrugsRepositoryImpl extends CommonDataBaseRepository implements DrugsRepository {

	/**
	 * lay danh sach don thuoc
	 *
	 * @param itemParamsEntity: params client truyen len
	 * @return
	 */
	@Override
	public Object getDrugs(DrugsDTO itemParamsEntity) {
		StringBuilder sql = new StringBuilder();
		HashMap<String, Object> hmapParams = new HashMap<>();

		sql.append(" SELECT result.*, ch.name AS healthfacilitiesName ");
		sql.append(" FROM ( ");
		sql.append(" SELECT d.patient_id AS patientId, drug_id as drugId, ");
		sql.append(" d.histories_id AS historiesId,  ");
		sql.append(" d.healthfacilities_code AS healthfacilitiesCode, ");
		sql.append(" d.drug_name AS drugName, ");
		sql.append(" d.unit,  ");
		sql.append(" d.method_code AS methodCode, cm.name AS methodName,");
		sql.append(" d.quantity,  ");
		sql.append(" d.morning_amount AS morningAmount, ");
		sql.append(" d.noon_amount AS noonAmount,  ");
		sql.append(" d.afternoon_amount AS afternoonAmount,  ");
		sql.append(" d.evening_amount AS eveningAmount, ");
		sql.append(" d.dosage_description AS dosageDescription, ");
		sql.append(" d.notes ");
		sql.append(" FROM medical_healthcare_drugs d ");
		sql.append("left join (select method_code, name from cats_methods where is_active = 1 and is_delete = 0) cm on cm.method_code = d.method_code");
		sql.append(" WHERE d.is_delete = 0  ");
		sql.append(" AND is_active = 1 ");
		if (Objects.nonNull(itemParamsEntity.getPatientId())) {
			sql.append(" AND (d.patient_id =:patientId ");
			hmapParams.put("patientId", itemParamsEntity.getPatientId());
		}
		if (!StringUtils.isEmpty(itemParamsEntity.getPhoneNumber())) {
			if (Objects.nonNull(itemParamsEntity.getPatientId())) {
				sql.append(" OR d.phone_number LIKE CONCAT('%', :phoneNumber, '%') ");
			} else {
				sql.append(" AND ( d.phone_number LIKE CONCAT('%', :phoneNumber, '%') ");
			}
			hmapParams.put("phoneNumber", itemParamsEntity.getPhoneNumber());
		}
		if (Objects.nonNull(itemParamsEntity.getPatientId()) || Objects.nonNull(itemParamsEntity.getPhoneNumber())) {
			sql.append(" ) ");
		}

		if (itemParamsEntity != null && itemParamsEntity.getHistoriesId() != null) {
			sql.append(" AND d.histories_id =:historiesId ");
			hmapParams.put("historiesId", itemParamsEntity.getHistoriesId());
		}
		sql.append(" ) AS result ");
		sql.append(" LEFT JOIN ( ");
		sql.append(" SELECT healthfacilities_code, name ");
		sql.append(" FROM cats_healthfacilities) ch ON ch.healthfacilities_code = result.healthfacilitiesCode ");

		Integer start = Constants.START_RECORD_DEFAULT;
		if (itemParamsEntity != null && itemParamsEntity.getStartrecord() != null) {
			start = itemParamsEntity.getStartrecord();
		}
		Integer pageSize = Constants.PAGE_SIZE_DEFAULT;
		if (itemParamsEntity != null && itemParamsEntity.getPagesize() != null) {
			pageSize = itemParamsEntity.getPagesize();
		}
		Object listData = getListDataAndCount(sql, hmapParams, start, pageSize, DrugsDTO.class);

		return listData;
	}

	@Override
	public List<DrugsDTO> getListDrug(DrugsDTO itemParamsEntity) {
		StringBuilder sql = new StringBuilder();
		HashMap<String, Object> hmapParams = new HashMap<>();
		sql.append("SELECT " +
				"mhd.drug_id AS drugId, " +
				"mhd.drug_name AS drugName, " +
				"mhd.unit AS unit, " +
				"mhd.method_code AS methodCode, " +
				"mt.name AS methodName, " +
				"mhd.morning_amount AS morningAmount, " +
				"mhd.noon_amount AS noonAmount, " +
				"mhd.afternoon_amount AS afternoonAmount, " +
				"mhd.evening_amount AS eveningAmount, " +
				"mhd.quantity AS quantity, " +
				"mhd.dosage_description AS dosageDescription, " +
				"mhd.notes AS notes");
		sql.append(" FROM medical_healthcare_drugs mhd " +
				"left join (select method_code, name from cats_methods) mt on mt.method_code=mhd.method_code ");
		sql.append(" WHERE mhd.is_active = 1 AND mhd.is_delete = 0");

		if (itemParamsEntity != null && itemParamsEntity.getHistoriesId() != null) {
			sql.append(" AND (mhd.histories_id = :historiesId");
			hmapParams.put("historiesId", itemParamsEntity.getHistoriesId());
		}

		if (itemParamsEntity != null && itemParamsEntity.getPhoneNumber() != null) {
			sql.append(" OR mhd.phone_number LIKE CONCAT('%', :phoneNumber, '%')");
			hmapParams.put("phoneNumber", itemParamsEntity.getPhoneNumber());
		}

		sql.append(")");

		Integer start = Constants.START_RECORD_DEFAULT;
		if (itemParamsEntity != null && itemParamsEntity.getStartrecord() != null) {
			start = itemParamsEntity.getStartrecord();
		}
		Integer pageSize = Constants.PAGE_SIZE_DEFAULT;
		if (itemParamsEntity != null && itemParamsEntity.getPagesize() != null) {
			pageSize = itemParamsEntity.getPagesize();
		}
		List<DrugsDTO> listData = (List<DrugsDTO>) getListData(sql, hmapParams, start, pageSize, DrugsDTO.class);
		return listData;
	}
}
