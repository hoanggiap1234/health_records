package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.VaccinationsDTO;
import com.viettel.etc.repositories.VaccinationsRepository;
import com.viettel.etc.utils.Constants;
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
 * @date Thu Aug 27 09:47:44 ICT 2020
 */
@Repository
public class VaccinationsRepositoryImpl extends CommonDataBaseRepository implements VaccinationsRepository{

    /**
     * api get vaccinations history
     *
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity getVaccinationsHistory(VaccinationsDTO itemParamsEntity){
         StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();

        sql.append("SELECT v.vaccination_id as vaccinationId, v.vaccine_name as vaccineName,v.batch_number AS batchNumber, v.healthfacilities_code as healthfacilitiesCode, h.name AS healthfacilitiesName, v.antigen, v.injection_date as injectionDate, v.injections_number as injectionsNumber, v.vaccination_facility as vaccinationFacility, v.reaction_level as reactionLevel, v.reaction_time as reactionTime, v.result ");
        sql.append("from (select * from medical_healthcare_vaccinations WHERE is_delete = 0 AND is_active =1 AND (patient_id = :patient_id ");
        if (itemParamsEntity != null && itemParamsEntity.getPhoneNumber() != null && itemParamsEntity.getFullname() != null) {
            sql.append(" OR phone_number =:phoneNumber and fullname =:fullname) ");
            hmapParams.put("phoneNumber", itemParamsEntity.getPhoneNumber());
            hmapParams.put("fullname", itemParamsEntity.getFullname());
        }
        sql.append(") v LEFT JOIN (select healthfacilities_code, name from cats_healthfacilities) h ON h.healthfacilities_code = v.healthfacilities_code ");
        sql.append("order by v.injection_date");

        hmapParams.put("patient_id", itemParamsEntity.getPatientId());
        Integer start = Objects.nonNull(itemParamsEntity.getStartrecord())?itemParamsEntity.getStartrecord(): Constants.START_RECORD_DEFAULT;
        if(itemParamsEntity!=null && itemParamsEntity.getStartrecord()!=null){
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = Objects.nonNull(itemParamsEntity.getStartrecord())?itemParamsEntity.getStartrecord():Constants.PAGE_SIZE_DEFAULT;
        if(itemParamsEntity!=null && itemParamsEntity.getPagesize()!=null){
            pageSize = itemParamsEntity.getPagesize();
        }
        ResultSelectEntity listData = getListDataAndCount(sql, hmapParams, start, pageSize,VaccinationsDTO.class);

         return listData;
    }
    

    /**
     * api get detail vaccinations history
     * 
     * @param itemParamsEntity : params client truyen len
     * @return
     */
    @Override
    public Optional<VaccinationsDTO> getDetailVaccinationsHistory(VaccinationsDTO itemParamsEntity) {
        HashMap<String, Object> hmapParams = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT  ");
        sql.append(" medical_healthcare_vaccination.vaccination_id AS vaccinationId,");
        sql.append(" medical_healthcare_vaccination.vaccine_name AS vaccineName,");
        sql.append(" medical_healthcare_vaccination.healthfacilities_code AS healthfacilitiesCode,");
        sql.append(" cats_healthfacilitie.name AS healthfacilitiesName,");
        sql.append(" medical_healthcare_vaccination.antigen,");
        sql.append(" medical_healthcare_vaccination.batch_number AS batchNumber,");
        sql.append(" medical_healthcare_vaccination.injection_date AS injectionDate,");
        sql.append(" medical_healthcare_vaccination.injections_number AS injectionsNumber,");
        sql.append(" medical_healthcare_vaccination.vaccination_facility AS vaccinationFacility,");
        sql.append(" medical_healthcare_vaccination.reaction_level AS reactionLevel,");
        sql.append(" medical_healthcare_vaccination.reaction_time AS reactionTime,");
        sql.append(" medical_healthcare_vaccination.result");
        sql.append(" FROM medical_healthcare_vaccinations AS medical_healthcare_vaccination");
        sql.append("     LEFT JOIN cats_healthfacilities AS cats_healthfacilitie ON cats_healthfacilitie.healthfacilities_code = medical_healthcare_vaccination.healthfacilities_code");
        sql.append(" WHERE medical_healthcare_vaccination.is_delete = 0 AND medical_healthcare_vaccination.is_active =1");
        sql.append("     AND medical_healthcare_vaccination.vaccination_id = :vaccinationId ");

        hmapParams.put("vaccinationId", itemParamsEntity.getVaccinationId());
        if (Objects.nonNull(itemParamsEntity.getPatientId())) {
            sql.append("     AND (medical_healthcare_vaccination.patient_id = :patientId ");
            hmapParams.put("patientId", itemParamsEntity.getPatientId());
        }
        if (itemParamsEntity.getPhoneNumber() != null) {
            sql.append(" OR medical_healthcare_vaccination.phone_number LIKE CONCAT('%', :phoneNumber, '%')");
            hmapParams.put("phoneNumber", itemParamsEntity.getPhoneNumber());
        }
        sql.append(")");
        VaccinationsDTO resultData = (VaccinationsDTO) getFirstData(sql, hmapParams, VaccinationsDTO.class);
        return Optional.ofNullable(resultData);
    }
}
