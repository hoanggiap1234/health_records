package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.InsuranceDTO;
import com.viettel.etc.repositories.InsuranceRepository;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * Autogen class Repository Impl:
 *
 * @author ToolGen
 * @date Tue Aug 18 10:12:25 ICT 2020
 */
@Repository
public class InsuranceRepositoryImpl extends CommonDataBaseRepository implements InsuranceRepository {

    /**
     * lay danh sach nguoi than
     *
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public Object getInsuranceInfo(InsuranceDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        sql.append("select patient_id as patientId, healthInsurance_id as healthInsuranceId, healthInsurance_number as healthinsuranceNumber, from_date as fromDate, to_date as toDate, five_years_date as fiveYearsDate, area_code as areaCode, ch.healthfacilities_code as healthfacilitiesCode, ch.name as healthfacilytiesName, ch.province_code as provinceCode, ch.name as provinceName, cd.district_code as districtCode, cd.name as districtName, cw.ward_code as wardCode, cw.name as wardName from patients_healthinsurances ph left join (select healthfacilities_code, name, province_code, district_code, ward_code from cats_healthfacilities where is_active = 1 and is_delete = 0) ch on ch.healthfacilities_code = ph.healthfacilities_code left join (select province_code, name from cats_provinces where is_active = 1 and is_delete = 0) cp on cp.province_code = ch.province_code left join (select district_code, name from cats_districts where is_active = 1 and is_delete = 0) cd on cd.district_code = ch.district_code left join (select ward_code, name from cats_wards where is_active = 1 and is_delete = 0) cw on cw.ward_code = ch.ward_code where is_active = 1 and is_delete = 0 ");
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("and ph.patient_id = :patient_id ");
        hmapParams.put("patient_id", itemParamsEntity.getPatientId());
        if (itemParamsEntity != null && itemParamsEntity.getPhoneNumber() != null) {
            sql.append("or ph.phone_number = :phoneNumber");
            hmapParams.put("phoneNumber", itemParamsEntity.getPhoneNumber());
        }
        return getFirstData(sql, hmapParams, InsuranceDTO.class);
    }
}