package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.MedicalHealthcareServiceDTO;
import com.viettel.etc.repositories.MedicalHealthcareServiceRepository;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Autogen class Repository Impl:
 *
 * @author ToolGen
 * @date Thu Aug 27 15:08:50 ICT 2020
 */
@Repository
public class MedicalHealthcareServiceRepositoryImpl extends CommonDataBaseRepository implements MedicalHealthcareServiceRepository {

	/**
	 * @param itemParamsEntity: params client truyen len
	 * @return
	 */
	@Override
	public ResultSelectEntity getHealthcareService(MedicalHealthcareServiceDTO itemParamsEntity) {
		StringBuilder sql = new StringBuilder();
		HashMap<String, Object> hmapParams = new HashMap<>();

		sql.append(" SELECT mhs.service_id AS serviceId, ");
		sql.append(" mhs.histories_id AS historiesId, ");
		sql.append(" mhs.patient_id AS patientId, ");
		sql.append(" mhs.service_name AS serviceName, ");
		sql.append(" mhs.decision_date AS decisionDate, ");
		sql.append(" mhs.healthfacilities_code AS healthfacilitiesCode, ");
		sql.append(" cast(mhs.is_sync as int) AS isSync, ");
		sql.append(" cats_healthfacilitie.name AS healthfacilitiesName, ");
		sql.append(" mhs.group_id AS groupId, ");
		sql.append(" mhs.concludes, ");
		sql.append(" mhs.results, mhs.suggestions, ");
		sql.append(" mhs.link_view_dicom AS linkViewDicom, ");
		sql.append(" mhs.his_id AS hisId ");
		sql.append(" FROM medical_healthcare_services mhs ");
		sql.append("    LEFT JOIN cats_healthfacilities AS cats_healthfacilitie ON cats_healthfacilitie.healthfacilities_code = mhs.healthfacilities_code ");
		sql.append(" WHERE 1 = 1 ");

		sql.append(" AND ");
		sql.append("     ( mhs.patient_id = :patientId ");
		if (!StringUtils.isEmpty(itemParamsEntity.getPhoneNumber())) {
			sql.append("  OR mhs.phone_number like concat('%', :phoneNumber, '%') ");
			hmapParams.put("phoneNumber", itemParamsEntity.getPhoneNumber());
		}
		sql.append(" ) ");

		sql.append(" AND mhs.group_id = :groupId ");
		if (Objects.nonNull(itemParamsEntity.getGroupId())) {
			hmapParams.put("groupId", itemParamsEntity.getGroupId());
		} else {
			// thêm điều kiện chuẩn đoán hình ảnh thì với group_id = 2
			hmapParams.put("groupId", Constants.GROUP_ID_Diagnostic_Image);
		}

		hmapParams.put("patientId", itemParamsEntity.getPatientId());

		Integer start = null;
		if (itemParamsEntity != null && itemParamsEntity.getStartrecord() != null) {
			start = itemParamsEntity.getStartrecord();
		}
		Integer pageSize = null;
		if (itemParamsEntity != null && itemParamsEntity.getPagesize() != null) {
			pageSize = itemParamsEntity.getPagesize();
		}
		ResultSelectEntity resultData = getListDataAndCount(sql, hmapParams, start, pageSize, MedicalHealthcareServiceDTO.class);
		return resultData;
	}

	@Override
	public Optional<MedicalHealthcareServiceDTO> getDetailHealthcareService(MedicalHealthcareServiceDTO dataParams) {
		StringBuilder sql = new StringBuilder();
		HashMap<String, Object> hmapParams = new HashMap<>();

		sql.append("SELECT ");
		sql.append(" medical_healthcare_service.decision_date AS decisionDate, ");
		sql.append(" cats_healthfacilitie.healthfacilities_code AS healthfacilitiesCode, ");
		sql.append(" cats_healthfacilitie.name AS healthfacilitiesName, ");
		sql.append(" medical_healthcare_service.service_id AS serviceId, ");
		sql.append(" medical_healthcare_service.histories_id AS historiesId, ");
		sql.append(" medical_healthcare_service.patient_id AS patientId, ");
		sql.append(" patient.fullname AS patientName, ");
		sql.append(" medical_healthcare_service.service_name AS serviceName, ");
		sql.append(" cast(medical_healthcare_service.is_sync as int) AS isSync, ");
		sql.append(" medical_healthcare_service.group_id AS groupId, ");
		sql.append(" medical_healthcare_service.concludes, ");
		sql.append(" medical_healthcare_service.results, ");
		sql.append(" medical_healthcare_service.suggestions, ");
		sql.append(" medical_healthcare_service.link_view_dicom AS linkViewDicom, ");
		sql.append(" medical_healthcare_service.his_id AS hisId, ");
		sql.append(" medical_healthcare_service.unit AS unit, ");
		sql.append(" medical_healthcare_service.quantity AS quantity, ");
		sql.append(" GROUP_CONCAT(DISTINCT medical_healthcare_histories_icd.diseases_code SEPARATOR ', ') AS diseasesCode, ");
		sql.append(" GROUP_CONCAT(DISTINCT medical_healthcare_histories_icd.diseases_name SEPARATOR ', ') AS diseasesName ");

		sql.append("  FROM medical_healthcare_services AS medical_healthcare_service ");
		sql.append("    LEFT JOIN cats_healthfacilities AS cats_healthfacilitie ON cats_healthfacilitie.healthfacilities_code = medical_healthcare_service.healthfacilities_code ");
		sql.append("       AND cats_healthfacilitie.is_delete = 0 ");
		sql.append("       AND cats_healthfacilitie.is_active = 1 ");
		sql.append("    LEFT JOIN patients AS patient ON patient.patient_id = medical_healthcare_service.patient_id ");
		sql.append("       AND patient.is_delete = 0 ");
		sql.append("       AND patient.is_active = 1 ");
		sql.append("   LEFT JOIN medical_healthcare_histories_icd AS medical_healthcare_histories_icd ON medical_healthcare_histories_icd.histories_id = medical_healthcare_service.histories_id ");
		sql.append("      AND medical_healthcare_histories_icd.is_delete = 0 ");
		sql.append("      AND medical_healthcare_histories_icd.is_active = 1");
		sql.append("     WHERE medical_healthcare_service.is_delete = 0 ");
		sql.append("       AND medical_healthcare_service.is_active = 1 ");
		sql.append("       AND medical_healthcare_service.service_id = :serviceId ");

		if (Objects.nonNull(dataParams.getPatientId())) {
			sql.append(" AND  ( medical_healthcare_service.patient_id = :patientId ");
			hmapParams.put("patientId", dataParams.getPatientId());
			if (!StringUtils.isEmpty(dataParams.getPhoneNumber())) {
				sql.append("  OR medical_healthcare_service.phone_number like concat('%', :phoneNumber, '%') ");
				hmapParams.put("phoneNumber", dataParams.getPhoneNumber());
			}
			sql.append(" ) ");
		}

		sql.append("   GROUP BY medical_healthcare_service.service_id ");
		hmapParams.put("serviceId", dataParams.getServiceId());

		MedicalHealthcareServiceDTO resultData = (MedicalHealthcareServiceDTO) getFirstData(sql, hmapParams, MedicalHealthcareServiceDTO.class);

		return Optional.ofNullable(resultData);
	}

	@Override
	public Optional<MedicalHealthcareServiceDTO> getDetailHealthcareServiceHistory(MedicalHealthcareServiceDTO dataParams) {
		StringBuilder sql = new StringBuilder();
		HashMap<String, Object> hmapParams = new HashMap<>();

		sql.append(" SELECT  ");
		sql.append(" medical_healthcare_service.service_name AS serviceName, ");
		sql.append(" medical_healthcare_service.concludes AS concludes, ");
		sql.append(" medical_healthcare_histories_attachment.url AS url ");

		sql.append(" FROM medical_healthcare_services AS medical_healthcare_service ");
		sql.append(" LEFT JOIN medical_healthcare_histories_attachments AS medical_healthcare_histories_attachment  ");
		sql.append("   ON  medical_healthcare_histories_attachment.service_id = medical_healthcare_service.service_id ");
		sql.append("   AND medical_healthcare_histories_attachment.histories_id = medical_healthcare_service.histories_id ");
		sql.append("   AND medical_healthcare_histories_attachment.is_delete = 0 ");
		sql.append("   AND medical_healthcare_histories_attachment.is_active = 1 ");
		sql.append(" WHERE medical_healthcare_service.is_delete = 0 ");
		sql.append("   AND medical_healthcare_service.is_active = 1 ");

		if (Objects.nonNull(dataParams.getServiceId())) {
			sql.append("   AND medical_healthcare_service.service_id = :serviceId ");
			hmapParams.put("serviceId", dataParams.getServiceId());
		}

		if (Objects.nonNull(dataParams.getHistoriesId())) {
			sql.append("   AND medical_healthcare_service.histories_id = :historiesId ");
			hmapParams.put("historiesId", dataParams.getHistoriesId());
		}

		MedicalHealthcareServiceDTO resultData = (MedicalHealthcareServiceDTO) getFirstData(sql, hmapParams, MedicalHealthcareServiceDTO.class);
		return Optional.ofNullable(resultData);
	}

	@Override
	public List<MedicalHealthcareServiceDTO> getMedicalHealthcareServicesResults(MedicalHealthcareServiceDTO dataParams) {
		StringBuilder sql = new StringBuilder();
		HashMap<String, Object> hmapParams = new HashMap<>();

		sql.append("SELECT ");
		sql.append(" medical_healthcare_services_result.index_code AS indexCode, ");
		sql.append(" medical_healthcare_services_result.index_name AS indexName, ");
		sql.append(" medical_healthcare_services_result.value AS value ");

		sql.append("    FROM medical_healthcare_services_results AS medical_healthcare_services_result ");
		sql.append("     WHERE medical_healthcare_services_result.is_delete = 0 ");
		sql.append("       AND medical_healthcare_services_result.is_active = 1 ");

		if (Objects.nonNull(dataParams.getServiceId())) {
			sql.append(" AND medical_healthcare_services_result.service_id = :serviceId ");
			hmapParams.put("serviceId", dataParams.getServiceId());
		}

		List<MedicalHealthcareServiceDTO> resultData = (List<MedicalHealthcareServiceDTO>) getListData(sql, hmapParams, null, null, MedicalHealthcareServiceDTO.class);
		return resultData;
	}

	@Override
	public List<MedicalHealthcareServiceDTO> getMedicalHealthcareHistoriesAttachments(MedicalHealthcareServiceDTO dataParams) {
		StringBuilder sql = new StringBuilder();
		HashMap<String, Object> hmapParams = new HashMap<>();

		sql.append(" SELECT ");
		sql.append(" medical_healthcare_histories_attachment.url AS url, ");
		sql.append(" medical_healthcare_histories_attachment.file_name AS fileName ");

		sql.append("    FROM medical_healthcare_histories_attachments AS medical_healthcare_histories_attachment ");
		sql.append("     WHERE medical_healthcare_histories_attachment.is_delete = 0 ");
		sql.append("           AND medical_healthcare_histories_attachment.is_active = 1 ");

		if (Objects.nonNull(dataParams.getServiceId())) {
			sql.append(" AND medical_healthcare_histories_attachment.service_id = :serviceId ");
			hmapParams.put("serviceId", dataParams.getServiceId());
		}

		List<MedicalHealthcareServiceDTO> resultData = (List<MedicalHealthcareServiceDTO>) getListData(sql, hmapParams, null, null, MedicalHealthcareServiceDTO.class);
		return resultData;
	}

	@Override
	public List<MedicalHealthcareServiceDTO> getListDetailHealthcareServiceByHistory(MedicalHealthcareServiceDTO dataParams) {
		StringBuilder sql = new StringBuilder();
		HashMap<String, Object> hmapParams = new HashMap<>();

		sql.append("SELECT ");
		sql.append(" cats_healthfacilitie.healthfacilities_code AS healthfacilitiesCode, ");
		sql.append(" cats_healthfacilitie.name AS healthfacilitiesName, ");
		sql.append(" medical_healthcare_service.service_id AS serviceId, ");
		sql.append(" medical_healthcare_service.histories_id AS historiesId, ");
		sql.append(" medical_healthcare_service.patient_id AS patientId, ");
		sql.append(" patient.fullname AS patientName, ");
		sql.append(" medical_healthcare_service.service_name AS serviceName, ");
		sql.append(" medical_healthcare_service.group_id AS groupId, ");
		sql.append(" medical_healthcare_service.concludes, ");
		sql.append(" medical_healthcare_service.results, ");
		sql.append(" medical_healthcare_service.suggestions, ");
		sql.append(" medical_healthcare_service.link_view_dicom AS linkViewDicom, ");
		sql.append(" medical_healthcare_service.his_id AS hisId, ");
		sql.append(" medical_healthcare_service.unit AS unit, ");
		sql.append(" medical_healthcare_service.quantity AS quantity, ");
		sql.append(" medical_healthcare_service.decision_date AS decisionDate ");

		sql.append("  FROM medical_healthcare_services AS medical_healthcare_service ");
		sql.append("    LEFT JOIN cats_healthfacilities AS cats_healthfacilitie ON cats_healthfacilitie.healthfacilities_code = medical_healthcare_service.healthfacilities_code ");
		sql.append("       AND cats_healthfacilitie.is_delete = 0 ");
		sql.append("       AND cats_healthfacilitie.is_active = 1 ");
		sql.append("    LEFT JOIN patients AS patient ON patient.patient_id = medical_healthcare_service.patient_id ");
		sql.append("       AND patient.is_delete = 0 ");
		sql.append("       AND patient.is_active = 1 ");
		sql.append("     WHERE medical_healthcare_service.is_delete = 0 ");
		sql.append("       AND medical_healthcare_service.is_active = 1 ");

		if (Objects.nonNull(dataParams.getServiceId())) {
			hmapParams.put("serviceId", dataParams.getServiceId());
			sql.append("       AND medical_healthcare_service.service_id = :serviceId ");
		}

		if (Objects.nonNull(dataParams.getHistoriesId())) {
			hmapParams.put("historiesId", dataParams.getHistoriesId());
			sql.append("       AND medical_healthcare_service.histories_id = :historiesId ");
		}

//		sql.append("  GROUP BY medical_healthcare_service.histories_id ");
		sql.append("  ORDER BY medical_healthcare_service.histories_id ");
		List<MedicalHealthcareServiceDTO> resultData = (List<MedicalHealthcareServiceDTO>) getListData(sql, hmapParams, null, null, MedicalHealthcareServiceDTO.class);
		return resultData;
	}
}
