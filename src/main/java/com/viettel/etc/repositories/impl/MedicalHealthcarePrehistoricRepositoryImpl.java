package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.MedicalHealthcarePrehistoricDTO;
import com.viettel.etc.repositories.MedicalHealthcarePrehistoricRepository;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Autogen class Repository Impl:
 *
 * @author ToolGen
 * @date Thu Sep 17 17:27:43 ICT 2020
 */
@Repository
public class MedicalHealthcarePrehistoricRepositoryImpl extends CommonDataBaseRepository implements MedicalHealthcarePrehistoricRepository {

    @Override
    public List<MedicalHealthcarePrehistoricDTO> getMedicalHealthcarePrehistoric(MedicalHealthcarePrehistoricDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT mhp.prehistoric_id AS prehistoricId, mhp.relationship_id AS relationshipId, cr.name AS relationshipName, mhp.health_history AS healthHistory, CAST(mhp.is_sync AS int) AS isSync");
        sql.append(" FROM medical_healthcare_prehistoric mhp");
        sql.append(" LEFT JOIN cats_relationships cr");
        sql.append(" ON mhp.relationship_id = cr.relationship_Id");
        sql.append(" WHERE mhp.is_active = 1 AND mhp.is_delete = 0 AND cr.is_active = 1 AND cr.is_delete = 0");

        if (itemParamsEntity != null && itemParamsEntity.getPatientId() != null) {
            sql.append(" AND (mhp.patient_id = :patientId");
            hmapParams.put("patientId", itemParamsEntity.getPatientId());
        }

        if (itemParamsEntity != null && itemParamsEntity.getPhoneNumber() != null) {
            sql.append(" OR mhp.phone_number LIKE CONCAT('%', :phoneNumber, '%')");
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
        List<MedicalHealthcarePrehistoricDTO> listData = (List<MedicalHealthcarePrehistoricDTO>) getListData(sql, hmapParams, start, pageSize, MedicalHealthcarePrehistoricDTO.class);
        return listData;
    }
}
