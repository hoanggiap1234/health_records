package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.PatientsIdentificationDTO;
import com.viettel.etc.repositories.PatientsIdentificationRepository;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Optional;

/**
 * Autogen class Repository Impl:
 *
 * @author ToolGen
 * @date Mon Sep 28 17:02:37 ICT 2020
 */
@Repository
public class PatientsIdentificationRepositoryImpl extends CommonDataBaseRepository implements PatientsIdentificationRepository {

    /**
     * validate health insurance exits
     *
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public Optional<PatientsIdentificationDTO> autofillHealthInsuranceInfo(PatientsIdentificationDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();

        sql.append(" SELECT  ");
        sql.append(" patients_identification.id AS id, ");
        sql.append(" patients_identification.pid AS pid, ");
        sql.append(" patients_identification.fullname AS fullname, ");
        sql.append(" patients_identification.gender_id AS genderId, ");
        sql.append(" patients_identification.birthday AS birthday, ");
        sql.append(" patients_identification.province_code AS provinceCode, ");
        sql.append(" cats_province.name AS provinceName, ");
        sql.append(" patients_identification.district_code AS districtCode, ");
        sql.append(" cats_district.name AS districtName, ");
        sql.append(" patients_identification.ward_code AS wardCode, ");
        sql.append(" cats_ward.name AS wardName, ");
        sql.append(" patients_identification.phone_number AS phoneNumber, ");
        sql.append(" patients_identification.identification AS identification, ");
        sql.append(" patients_identification.healthInsurance_number AS healthInsuranceNumber, ");
        sql.append(" patients_identification.healthfacilities_code AS healthfacilitiesCode, ");
        sql.append(" cats_healthfacilitie.name AS healthfacilitiesName, ");
        sql.append(" patients_identification.from_date AS fromDate, ");
        sql.append(" patients_identification.to_date AS toDate, ");
        sql.append(" patients_identification.area_code AS areaCode, ");
        sql.append(" patients_identification.five_years_date AS  fiveYearsDate, ");
        sql.append(" IF (patients_identification.is_check, 1, 0) AS isCheck ");
        sql.append(" FROM patients_identification AS patients_identification ");
        sql.append(" LEFT JOIN cats_healthfacilities AS cats_healthfacilitie ON cats_healthfacilitie.healthfacilities_code = patients_identification.healthfacilities_code ");
        sql.append(" AND cats_healthfacilitie.is_delete = 0 ");
        sql.append(" AND cats_healthfacilitie.is_active = 1 ");
        sql.append(" LEFT JOIN cats_provinces AS cats_province ON cats_province.province_code = patients_identification.province_code ");
        sql.append(" AND cats_province.is_delete = 0 ");
        sql.append(" AND cats_province.is_active = 1 ");
        sql.append(" LEFT JOIN cats_districts AS cats_district ON cats_district.district_code = patients_identification.district_code ");
        sql.append(" AND cats_district.is_delete = 0 ");
        sql.append(" AND cats_district.is_active = 1 ");
        sql.append(" LEFT JOIN cats_wards AS cats_ward ON cats_ward.ward_code = patients_identification.ward_code ");
        sql.append(" AND cats_ward.is_delete = 0 ");
        sql.append(" AND cats_ward.is_active = 1 ");
        sql.append(" WHERE patients_identification.is_delete = 0 ");
        sql.append(" AND patients_identification.is_active = 1 ");

        if (!StringUtils.isEmpty(itemParamsEntity.getHealthinsuranceNumber())) {
            sql.append(" AND patients_identification.healthInsurance_number = :healthInsuranceNumber ");
            hmapParams.put("healthInsuranceNumber", itemParamsEntity.getHealthinsuranceNumber());
        }
        sql.append(" GROUP BY patients_identification.id ");

        PatientsIdentificationDTO resultData = (PatientsIdentificationDTO) getFirstData(sql, hmapParams, PatientsIdentificationDTO.class);
        return Optional.ofNullable(resultData);
    }
}
