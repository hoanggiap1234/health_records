package com.viettel.etc.repositories.impl;

import com.viettel.etc.controllers.MedicalHealthcareAllergyController;
import com.viettel.etc.dto.MedicalHealthcareHistoryAttachmentDTO;
import com.viettel.etc.dto.MedicalHealthcareHistoryDTO;
import com.viettel.etc.repositories.MedicalHealthcareHistoryRepository;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

/**
 * Autogen class Repository Impl:
 *
 * @author ToolGen
 * @date Thu Aug 27 11:39:10 ICT 2020
 */
@Repository
public class MedicalHealthcareHistoryRepositoryImpl extends CommonDataBaseRepository implements MedicalHealthcareHistoryRepository {

	private static final Logger LOGGER = Logger.getLogger(MedicalHealthcareAllergyController.class);

	/**
	 * @param itemParamsEntity: params client truyen len
	 * @return
	 */
	@Override
	public ResultSelectEntity getHealthcareHistory(MedicalHealthcareHistoryDTO itemParamsEntity) {
		StringBuilder sql = new StringBuilder();
		HashMap<String, Object> hmapParams = new HashMap<>();
		sql.append(" SELECT  ");
		sql.append(" mhh.histories_id AS historiesId, booking.booking_id as bookingId, ");
		sql.append(" mhh.his_id AS hisId, ");
		sql.append(" mhh.patient_id AS patientId, ");
		sql.append(" pt.fullname AS patientName, ");
		sql.append(" mhh.phone_number AS phoneNumber, ");
		sql.append(" mhh.examination_date AS examinationDate, ");
		sql.append(" mhh.doctor_name AS doctorName, ");
		sql.append(" mhh.doctor_id AS doctorId, ");
		sql.append(" d.academic_rank_id AS academicRankId, ");
		sql.append(" car.name AS academicRankName, ");
		sql.append(" car.code AS academicRankCode, ");
		sql.append(" d.degree_id AS degreeId, ");
		sql.append(" cd.code AS degreeCode, ");
		sql.append(" cd.name AS degreeName, ");
		sql.append(" mhh.healthfacilities_code AS healthfacilitiesCode, ");
		sql.append(" mhh.healthfacilities_name AS healthfacilitiesName, ");
		sql.append(" mhh.reasons_medicalexamination AS reasonsMedicalexamination, ");
		sql.append(" mhh.symptoms AS symptoms, ");
		sql.append(" mhh.concludes_disease AS concludesDisease, ");
		sql.append(" mhh.treatment_direction AS treatmentDirection, ");
		sql.append(" mhh.re_examination_date AS reExaminationDate, ");
		sql.append(" mhh.type_of_examination AS typeOfExamination, ");
		sql.append(" booking.booking_type AS bookingType, ");
		sql.append(" GROUP_CONCAT(DISTINCT mhhi.diseases_code SEPARATOR ',') AS diseasesCode, ");
		sql.append(" GROUP_CONCAT(DISTINCT mhhi.diseases_name SEPARATOR ',') AS diseasesName ");
		sql.append(" FROM medical_healthcare_histories mhh ");
		sql.append(" LEFT JOIN doctors d ON mhh.doctor_id = d.doctor_id ");
		sql.append("   AND d.is_active = 1  ");
		sql.append("   AND d.is_delete = 0 ");
		sql.append(" LEFT JOIN cats_academic_rank car ON d.academic_rank_id = car.Id ");
		sql.append("   AND car.Is_active = 1  ");
		sql.append("   AND car.is_delete = 0  ");
		sql.append(" LEFT JOIN cats_degree cd ON d.degree_id = cd.degree_id ");
		sql.append("   AND cd.is_active = 1  ");
		sql.append("   AND cd.is_delete = 0  ");
		sql.append(" LEFT JOIN medical_healthcare_histories_icd mhhi ON mhh.histories_id = mhhi.histories_id ");
		sql.append("   AND mhhi.is_active = 1  ");
		sql.append("   AND mhhi.is_delete = 0 ");
		sql.append(" LEFT JOIN (select patient_id, fullname, birthday, gender_id, avatar, phone_number, is_active, is_delete from patients) pt ON pt.patient_id = mhh.patient_id ");
		sql.append("   AND pt.is_active = 1  ");
		sql.append("   AND pt.is_delete = 0 ");
		sql.append("   LEFT JOIN (SELECT booking_id, histories_id, booking_type FROM booking_informations  WHERE is_active = 1 AND is_delete = 0) booking ON booking.histories_id = mhh.histories_id");
		sql.append(" WHERE mhh.is_active = 1  ");
		sql.append("   AND mhh.is_delete = 0  ");
		sql.append("   AND mhh.type_of_examination <> -1  ");

		if (itemParamsEntity != null && itemParamsEntity.getPatientId() != null) {
			sql.append(" AND (mhh.patient_id = :patientId");
			hmapParams.put("patientId", itemParamsEntity.getPatientId());
		}

		if (itemParamsEntity != null && itemParamsEntity.getPhoneNumber() != null) {
			sql.append(" OR mhh.phone_number LIKE CONCAT('%', :phoneNumber, '%')");
			hmapParams.put("phoneNumber", itemParamsEntity.getPhoneNumber());
		}
		sql.append(")");

		if (itemParamsEntity != null && itemParamsEntity.getTypeOfExaminations() != null) {
			int[] type = stringToDistinctIntegerArray(itemParamsEntity.getTypeOfExaminations());
			if (type.length > 0) {
				String arrString = Arrays.toString(type).replace("[", "").replace("]", "");
				sql.append("and mhh.type_of_examination IN(").append(arrString).append(") ");
			}
		}

		sql.append(" GROUP BY mhh.histories_id ");
		sql.append(" ORDER BY mhh.examination_date DESC ");

		Integer start = Constants.START_RECORD_DEFAULT;
		if (itemParamsEntity != null && itemParamsEntity.getStartrecord() != null) {
			start = itemParamsEntity.getStartrecord();
		}
		Integer pageSize = Constants.PAGE_SIZE_DEFAULT;
		if (itemParamsEntity != null && itemParamsEntity.getPagesize() != null) {
			pageSize = itemParamsEntity.getPagesize();
		}
		ResultSelectEntity resultData = getListDataAndCount(sql, hmapParams, start, pageSize, MedicalHealthcareHistoryDTO.class);
		return resultData;
	}

	private int[] stringToDistinctIntegerArray(String str) {
		String[] strArr = str.split(",");
		int size = strArr.length;
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			try {
				arr[i] = Integer.parseInt(strArr[i]);
			} catch (Exception e) {
				LOGGER.info(e);
			}
		}

		return Arrays.stream(arr).distinct().toArray();
	}

	/**
	 * get Detail Healthcare History
	 *
	 * @param dataParams: params client truyen len
	 * @return Optional MedicalHealthcareHistoryDTO
	 */
	@Override
	public Optional<MedicalHealthcareHistoryDTO> getDetailHealthcareHistory(MedicalHealthcareHistoryDTO dataParams) {
		StringBuilder sql = new StringBuilder();
		HashMap<String, Object> hmapParams = new HashMap<>();

		sql.append(" SELECT ");
		sql.append(" medical_healthcare_historie.histories_id AS historiesId, ");
		sql.append(" medical_healthcare_historie.phone_number AS phoneNumber, ");
		sql.append(" medical_healthcare_historie.his_id AS hisId, ");
		sql.append(" medical_healthcare_historie.patient_id AS patientId, ");
		sql.append(" medical_healthcare_historie.doctor_name AS doctorName, ");
		sql.append(" medical_healthcare_historie.doctor_id AS doctorId, car.code AS academicRankCode, cd.code AS degreeCode, ");
		sql.append(" medical_healthcare_historie.examination_date AS examinationDate, ");
		sql.append(" medical_healthcare_historie.healthfacilities_code AS healthfacilitiesCode, ");
		sql.append(" medical_healthcare_historie.healthfacilities_name AS healthfacilitiesName, ");
		sql.append(" medical_healthcare_historie.reasons_medicalexamination AS reasonsMedicalexamination, ");
		sql.append(" medical_healthcare_historie.symptoms AS symptoms, ");
		sql.append(" medical_healthcare_historie.concludes_disease as concludesDisease, ");
		sql.append(" medical_healthcare_historie.treatment_direction as treatmentDirection,");
		sql.append(" medical_healthcare_historie.re_examination_date as reExaminationDate,");
		sql.append(" GROUP_CONCAT(DISTINCT medical_healthcare_histories_icd.diseases_code SEPARATOR ',') AS diseasesCode, ");
		sql.append(" GROUP_CONCAT(DISTINCT medical_healthcare_histories_icd.diseases_name SEPARATOR ',') AS diseasesName, ");

		sql.append(" medical_healthcare_historie.right_eye_without_glasses AS rightEyeWithoutGlasses, ");
		sql.append(" medical_healthcare_historie.left_eye_without_glasses AS leftEyeWithoutGlasses, ");
		sql.append(" medical_healthcare_historie.right_eye_with_glasses AS rightEyeWithGlasses, ");
		sql.append(" medical_healthcare_historie.left_eye_with_glasses AS leftEyeWithGlasses, ");
		sql.append(" medical_healthcare_historie.body_skin AS bodySkin, ");
		sql.append(" medical_healthcare_historie.body_skin_other AS bodySkinOther, ");
		sql.append(" medical_healthcare_historie.body_part_heart AS bodyPartHeart, ");
		sql.append(" medical_healthcare_historie.body_part_respiratory AS bodyPartRespiratory, ");
		sql.append(" medical_healthcare_historie.body_part_digest AS bodyPartDigest, ");
		sql.append(" medical_healthcare_historie.body_part_urinary AS bodyPartUrinary, ");
		sql.append(" medical_healthcare_historie.body_part_osteoarthritis AS bodyPartOsteoarthritis, ");
		sql.append(" medical_healthcare_historie.body_part_endocrine AS bodyPartEndocrine, ");
		sql.append(" medical_healthcare_historie.body_part_mental AS bodyPartMental, ");
		sql.append(" medical_healthcare_historie.body_part_surgical AS bodyPartSurgical, ");
		sql.append(" medical_healthcare_historie.body_part_gynecology AS bodyPartGynecology, ");
		sql.append(" medical_healthcare_historie.body_part_ear_nose_throat AS bodyPartEarNoseThroat, ");
		sql.append(" medical_healthcare_historie.body_part_dentomaxillofacial AS bodyPartDentomaxillofacial, ");
		sql.append(" medical_healthcare_historie.body_part_eye AS bodyPartEye, ");
		sql.append(" medical_healthcare_historie.body_part_skin AS bodyPartSkin, ");
		sql.append(" medical_healthcare_historie.body_part_nutrition AS bodyPartNutrition, ");
		sql.append(" medical_healthcare_historie.body_part_motive AS bodyPartMotive, ");
		sql.append(" medical_healthcare_historie.body_part_evaluation AS bodyPartEvaluation, ");
		sql.append(" medical_healthcare_historie.body_part_other AS bodyPartOther, ");
		sql.append(" medical_healthcare_historie.body_part_nerve AS bodyPartNerve, ");
		sql.append(" medical_healthcare_historie.is_sync AS isSync, ");
		sql.append(" booking.booking_type AS bookingType, ");
		sql.append(" booking.booking_id AS bookingId, ");
		sql.append(" pt.fullname as patientName, pt.birthday as patientBirthday, pt.gender_id as patientGenderId, pt.avatar as patientAvatar, pt.phone_number as patientPhoneNumber ");

		sql.append(" FROM medical_healthcare_histories AS medical_healthcare_historie ");
		sql.append("   LEFT JOIN medical_healthcare_histories_icd AS medical_healthcare_histories_icd ON medical_healthcare_histories_icd.histories_id = medical_healthcare_historie.histories_id");
		sql.append("      AND medical_healthcare_histories_icd.is_delete = 0 ");
		sql.append("      AND medical_healthcare_histories_icd.is_active = 1");

		sql.append("   LEFT JOIN (SELECT booking_id, histories_id, booking_type FROM booking_informations  WHERE is_active = 1 AND is_delete = 0) booking ON booking.histories_id = medical_healthcare_historie.histories_id ");
		sql.append(" LEFT JOIN (select patient_id, fullname, birthday, gender_id, avatar, phone_number, is_active, is_delete from patients) pt ON pt.patient_id = medical_healthcare_historie.patient_id ");
		sql.append(" LEFT JOIN (SELECT doctor_id, academic_rank_id, degree_id FROM doctors WHERE is_active=1 AND is_delete=0) d ON d.doctor_id = medical_healthcare_historie.doctor_id ");
		sql.append(" LEFT JOIN (SELECT Id, code FROM cats_academic_rank WHERE is_active=1 AND is_delete=0) car ON car.Id = d.academic_rank_id ");
		sql.append(" LEFT JOIN (SELECT degree_id, code FROM cats_degree WHERE is_active=1 AND is_delete=0) cd ON cd.degree_id = d.degree_id ");

		sql.append(" WHERE medical_healthcare_historie.is_active = 1 ");
		sql.append("   AND medical_healthcare_historie.is_delete = 0 ");
		if (Objects.nonNull(dataParams.getPatientId())) {
			sql.append("   AND ( medical_healthcare_historie.patient_id = :patientId ");
			hmapParams.put("patientId", dataParams.getPatientId());

			if (!StringUtils.isEmpty(dataParams.getPhoneNumber())) {
				sql.append("   OR medical_healthcare_historie.phone_number like concat('%', :phoneNumber, '%') ");
				hmapParams.put("phoneNumber", dataParams.getPhoneNumber());
			}
			sql.append("       ) ");
		}
		sql.append("   AND medical_healthcare_historie.histories_id = :historiesId");

		sql.append("   GROUP BY medical_healthcare_historie.histories_id ");
		sql.append("   ORDER BY medical_healthcare_historie.examination_date ");

		hmapParams.put("historiesId", dataParams.getHistoriesId());

		MedicalHealthcareHistoryDTO resultData = (MedicalHealthcareHistoryDTO) getFirstData(sql, hmapParams, MedicalHealthcareHistoryDTO.class);
		if (resultData == null) {
			return Optional.ofNullable(null);
		}
		return Optional.ofNullable(resultData);
	}

	@Override
	public ResultSelectEntity getMedicalHealthcareHistoryAttachments(MedicalHealthcareHistoryAttachmentDTO dataParams) {
		StringBuilder sql = new StringBuilder();
		HashMap<String, Object> hmapParams = new HashMap<>();

		sql.append(" SELECT ");
		sql.append(" hat.attachment_id AS attachmentId, ");
		sql.append(" hat.group_type AS groupType, ");
		sql.append(" concat(hat.file_name, '.', SUBSTRING_INDEX(hat.url, '.', -1)) as name, ");
		sql.append(" hat.url,");
		sql.append(" service_id AS serviceId ");
		sql.append(" FROM medical_healthcare_histories_attachments hat ");
		sql.append(" WHERE hat.is_delete= 0 AND hat.is_active= 1 ");

		if (Objects.nonNull(dataParams.getHistoriesId())) {
			sql.append("   AND hat.histories_id = :historiesId ");
			hmapParams.put("historiesId", dataParams.getHistoriesId());
		}

		return getListDataAndCount(sql, hmapParams, null, null, MedicalHealthcareHistoryAttachmentDTO.class);
	}

}
