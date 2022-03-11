package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.MedicalHealthcareHistoriesDTO;
import com.viettel.etc.dto.MedicalHealthcareHistoryAttachmentDTO;
import com.viettel.etc.dto.ReExaminationDTO;
import com.viettel.etc.repositories.MedicalHealthcareHistoriesRepository;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Autogen class Repository Impl:
 *
 * @author ToolGen
 * @date Tue Sep 22 16:35:41 ICT 2020
 */
@Repository
public class MedicalHealthcareHistoriesRepositoryImpl extends CommonDataBaseRepository implements MedicalHealthcareHistoriesRepository {

    /**
     * Danh sach cac don thuoc (Ho so suc khoe)
     *
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity getHealthcareDrugs(MedicalHealthcareHistoriesDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT mhh.histories_id AS historiesId, mhh.healthfacilities_code AS healthfacilitiesCode, mhh.healthfacilities_name AS healthfacilitiesName, mhh.doctor_id AS doctorId, mhh.doctor_name AS doctorName, d.academic_rank_id AS academicRankId, car.code AS academicRankCode, car.name AS academicRankName, d.degree_id AS degreeId, cd.code AS degreeCode, cd.name AS degreeName, mhh.concludes_disease AS concludesDisease, mhd.decision_date AS decisionDate, mhd.is_sync AS isSync ");
        sql.append(" FROM medical_healthcare_histories mhh");
        sql.append(" LEFT JOIN doctors d");
        sql.append(" ON mhh.doctor_id = d.doctor_id");
        sql.append("    AND d.is_active = 1 AND d.is_delete = 0  ");
        sql.append(" LEFT JOIN cats_academic_rank car");
        sql.append(" ON d.academic_rank_id = car.Id");
        sql.append("   AND car.Is_active = 1 AND car.is_delete = 0  ");
        sql.append(" LEFT JOIN cats_degree cd");
        sql.append(" ON d.degree_id = cd.degree_id");
        sql.append("   AND cd.is_active = 1 AND cd.is_delete = 0  ");
        sql.append(" JOIN medical_healthcare_drugs mhd");
        sql.append(" ON mhh.histories_id = mhd.histories_id");
        sql.append("   AND mhd.is_active = 1 AND mhd.is_delete = 0  ");
        sql.append(" WHERE mhh.is_active = 1 AND mhh.is_delete = 0  ");

        if (itemParamsEntity != null && itemParamsEntity.getPatientId() != null) {
            sql.append(" AND (mhh.patient_id = :patientId");
            hmapParams.put("patientId", itemParamsEntity.getPatientId());
        }

        if (itemParamsEntity != null && itemParamsEntity.getPhoneNumber() != null) {
            sql.append(" OR mhh.phone_number LIKE CONCAT('%', :phoneNumber, '%')");
            hmapParams.put("phoneNumber", itemParamsEntity.getPhoneNumber());
        }
        sql.append(")");
        sql.append(" GROUP BY mhh.histories_id");
        sql.append(" ORDER BY mhd.decision_date DESC");

        Integer start = Constants.START_RECORD_DEFAULT;
        if (itemParamsEntity != null && itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = Constants.PAGE_SIZE_DEFAULT;
        if (itemParamsEntity != null && itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        ResultSelectEntity listData = getListDataAndCount(sql, hmapParams, start, pageSize, MedicalHealthcareHistoriesDTO.class);
        return listData;
    }

    @Override
    public Optional<MedicalHealthcareHistoriesDTO> getDetailHealthcareDrug(MedicalHealthcareHistoriesDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT mhh.histories_id AS historiesId, mhh.healthfacilities_code AS healthfacilitiesCode, mhh.healthfacilities_name AS healthfacilitiesName, mhh.doctor_id AS doctorId, mhh.doctor_name AS doctorName, d.academic_rank_id AS academicRankId, car.code AS academicRankCode, d.degree_id AS degreeId, cd.code AS degreeCode, mhh.concludes_disease AS concludesDisease, mhd.decision_date AS decisionDate, mhd.is_sync AS isSync ");
        sql.append(" FROM medical_healthcare_histories mhh");
        sql.append(" LEFT JOIN doctors d");
        sql.append(" ON mhh.doctor_id = d.doctor_id");
        sql.append(" AND d.is_active = 1 AND d.is_delete = 0 ");
        sql.append(" LEFT JOIN cats_academic_rank car");
        sql.append(" ON d.academic_rank_id = car.Id");
        sql.append("  AND car.Is_active = 1 AND car.is_delete = 0 ");
        sql.append(" LEFT JOIN cats_degree cd");
        sql.append(" ON d.degree_id = cd.degree_id");
        sql.append("  AND cd.is_active = 1 AND cd.is_delete = 0  ");
        sql.append(" LEFT JOIN medical_healthcare_drugs mhd");
        sql.append(" ON mhh.histories_id = mhd.histories_id");
        sql.append("  AND mhd.is_active = 1 AND mhd.is_delete = 0 ");
        sql.append(" WHERE mhh.is_active = 1 AND mhh.is_delete = 0 ");

        if (itemParamsEntity != null && itemParamsEntity.getHistoriesId() != null) {
            sql.append(" AND mhh.histories_id = :historiesId");
            hmapParams.put("historiesId", itemParamsEntity.getHistoriesId());
        }

        if (itemParamsEntity != null && itemParamsEntity.getPatientId() != null) {
            sql.append(" AND (mhh.patient_id = :patientId");
            hmapParams.put("patientId", itemParamsEntity.getPatientId());
        }

        if (itemParamsEntity != null && itemParamsEntity.getPhoneNumber() != null) {
            sql.append(" OR mhh.phone_number LIKE CONCAT('%', :phoneNumber, '%')");
            hmapParams.put("phoneNumber", itemParamsEntity.getPhoneNumber());
        }
        sql.append(")");
        sql.append(" GROUP BY mhh.histories_id");
        sql.append(" ORDER BY mhd.decision_date DESC");

        MedicalHealthcareHistoriesDTO resultData = (MedicalHealthcareHistoriesDTO) getFirstData(sql, hmapParams, MedicalHealthcareHistoriesDTO.class);
        if (resultData != null) {
            StringBuilder sqlFile = new StringBuilder();
            sqlFile.append(" SELECT ");
            sqlFile.append(" hat.attachment_id AS attachmentId, ");
            sqlFile.append(" hat.group_type AS groupType, ");
            sqlFile.append(" hat.file_name AS fileName, ");
            sqlFile.append(" hat.url ");
            sqlFile.append(" FROM medical_healthcare_histories_attachments hat ");
            sqlFile.append(" WHERE hat.histories_id=:historiesId AND hat.is_delete= 0 AND hat.is_active= 1 ");

            HashMap<String, Object> hmapParamsAttachment = new HashMap<>();
            hmapParamsAttachment.put("historiesId", Objects.requireNonNull(itemParamsEntity).getHistoriesId());

            List<MedicalHealthcareHistoryAttachmentDTO> attachment = (List<MedicalHealthcareHistoryAttachmentDTO>) getListData(sqlFile, hmapParamsAttachment, null, null, MedicalHealthcareHistoryAttachmentDTO.class);
            resultData.setAttachments(attachment);
        }
        return Optional.ofNullable(resultData);
    }

    /**
     * Danh sach lich tai kham
     *
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity getReExaminations(MedicalHealthcareHistoriesDTO itemParamsEntity, Long patientBookingId) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT mhh.histories_id AS historiesId," +
                "       bi.booking_id AS bookingId," +
                "       bi.booking_type AS bookingType," +
                "       bi.booking_group AS bookingGroup," +
                "       bi.doctor_id as doctorId," +
                "       bi.service_id AS serviceId," +
                "       p.fullname," +
                "       mhh.re_examination_date AS approveDate," +
                "       ch.healthfacilities_code AS healthfacilitiesCode," +
                "       ch.name as healthfacilitiesName," +
                "       ch.address as healthfacilitiesAddress," +
                "       mhh.doctor_name AS doctorName," +
                "       car.code AS academicRankCode," +
                "       cd.code AS degreeCode," +
                "       mhh.patient_id as patientId," +
                "       cp.name AS healthfacilitiesProvince," +
                "       cdi.name AS healthfacilitiesDistrict," +
                "       cw.name AS healthfacilitiesWard" +
                " FROM(" +
                " SELECT histories_id," +
                "       patient_id," +
                "       doctor_id," +
                "       doctor_name," +
                "       re_examination_date" +
                " FROM medical_healthcare_histories" +
                " WHERE is_active=1 AND is_delete=0 AND re_examination_date IS NOT NULL AND patient_id=:patientId" +
                " ORDER BY re_examination_date DESC) AS mhh" +
                " INNER JOIN(" +
                " SELECT booking_id, booking_group, booking_type, doctor_id," +
                "       histories_id," +
                "       healthfacilities_code," +
                "       service_id" +
                "       FROM booking_informations" +
                " WHERE is_active=1 AND is_delete=0 AND patient_booking_id =:patientBookingId ) AS bi" +
                " ON mhh.histories_id = bi.histories_id" +
                " LEFT JOIN (" +
                " SELECT patient_id," +
                "       fullname" +
                " FROM patients" +
                " WHERE is_active=1  AND is_delete=0) AS p" +
                " ON mhh.patient_id = p.patient_id" +
                " LEFT JOIN (" +
                " SELECT doctor_id," +
                "        academic_rank_id," +
                "        degree_id" +
                " FROM doctors" +
                " WHERE is_active=1 AND is_delete=0) AS d" +
                " ON mhh.doctor_id = d.doctor_id" +
                " LEFT JOIN (" +
                " SELECT Id," +
                "       code" +
                " FROM cats_academic_rank" +
                " WHERE is_active=1 AND is_delete=0) AS car" +
                " ON car.id = d.academic_rank_id" +
                " LEFT JOIN (" +
                " SELECT degree_id, code" +
                " FROM cats_degree" +
                " WHERE is_active=1 AND is_delete=0) AS cd" +
                " ON cd.degree_id = d.degree_id" +
                " LEFT JOIN(" +
                " SELECT healthfacilities_code," +
                "       name," +
                "       address," +
                "       province_code," +
                "       district_code," +
                "       ward_code" +
                " FROM cats_healthfacilities" +
                " WHERE is_active=1 AND is_delete=0) ch" +
                " ON ch.healthfacilities_code = bi.healthfacilities_code" +
                " LEFT JOIN (" +
                " SELECT province_code," +
                "        name" +
                " FROM cats_provinces" +
                " WHERE is_delete=0 AND is_active=1) cp" +
                " ON cp.province_code=ch.province_code" +
                " LEFT JOIN (" +
                " SELECT district_code," +
                "        name" +
                " FROM cats_districts" +
                " WHERE is_active=1 AND is_delete=0) cdi" +
                " ON ch.district_code=cdi.district_code " +
                " LEFT JOIN (" +
                " SELECT ward_code," +
                "        name" +
                " FROM cats_wards" +
                " WHERE is_active=1 AND is_delete=0) cw" +
                " ON ch.ward_code=cw.ward_code");

        hmapParams.put("patientId", itemParamsEntity.getPatientId());
        hmapParams.put("patientBookingId", patientBookingId);

        Integer start = Constants.START_RECORD_DEFAULT;
        if (itemParamsEntity != null && itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = Constants.PAGE_SIZE_DEFAULT;
        if (itemParamsEntity != null && itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        ResultSelectEntity listData = getListDataAndCount(sql, hmapParams, start, pageSize, ReExaminationDTO.class);
        return listData;
    }
}