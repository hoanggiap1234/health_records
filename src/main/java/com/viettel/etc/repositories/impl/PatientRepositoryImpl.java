package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.CovidPatientResultDTO;
import com.viettel.etc.dto.PatientDTO;
import com.viettel.etc.repositories.PatientRepository;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Autogen class Repository Impl:
 *
 * @author ToolGen
 * @date Wed Aug 12 17:57:15 ICT 2020
 */
@Repository
public class PatientRepositoryImpl extends CommonDataBaseRepository implements PatientRepository {

    /**
     * Lay thong tin benh nhan theo id
     *
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public Object getPatient(PatientDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.patient_id as patientId, p.address, p.keycloak_user_id as keycloakUserId, p.fullname as fullName, p.gender_id as genderId, p.avatar, p.pid, p.birthday, p.phone_number as phoneNumber, p.email as email, p.identification AS identification, \n" +
                "max(mhps.weight) AS weight, max(mhps.height) AS height, max(mhps.blood_abo) AS bloodABO, max(mhps.blood_rh) as bloodRh, max(mhps.bmi) as bmi, mhp.health_history as healthHistory,\n" +
                "cp.name as provinceName, cp.province_code as provinceCode, cd.name as districtName, cd.district_code as districtCode, " +
                " cw.name as wardName, p.ward_code as wardCode, ph.healthInsurance_id as healthInsuranceId, ph.healthInsurance_number as healthInsuranceNumber,\n" +
                "cn.nation_name AS nationName, p.nation_code as nationCode, ce.name AS ethnicityName, ce.ethnicity_code as ethnicityCode," +
                " cr.name AS religionName, cr.religion_code as religionCode, cj.name AS jobName, cj.job_id as jobId,\n" +
                "ph.from_date AS insurenceFromDate, ph.to_date AS insurenceToDate, ph.five_years_date AS fiveYearsDate,\n" +
                "ph.area_code AS areaCode, ch.healthfacilities_code as healthfacilitiesCode, ch.name as healthfacilitiesName,\n" +
                "bi.totalExams, \n" +
                "bi.totalAdvices, mhps.summary_id as summaryId, mhp.prehistoric_id as prehistoricId " +
                "FROM patients p \n" +
                "LEFT JOIN (SELECT name, province_code FROM cats_provinces) cp ON p.province_code = cp.province_code \n" +
                "LEFT JOIN (SELECT name, district_code FROM cats_districts) cd ON p.district_code = cd.district_code \n" +
                "LEFT JOIN (SELECT healthInsurance_id, healthInsurance_number, patient_id, healthfacilities_code, from_date, \n" +
                "  to_date, area_code, five_years_date FROM patients_healthinsurances) ph on p.patient_id = ph.patient_id \n" +
                "LEFT JOIN (SELECT name, ward_code FROM cats_wards) cw ON p.ward_code = cw.ward_code \n" +
                "LEFT JOIN (SELECT booking_group, patient_id, COUNT(CASE WHEN booking_group = 1 and patient_id = :patient_id  then 0 end) as totalExams, " +
                "COUNT(CASE WHEN booking_group = 2 and patient_id = :patient_id then 0 end) as totalAdvices " +
                "FROM booking_informations) bi ON p.patient_id = bi.patient_id\n" +
                "LEFT JOIN (SELECT summary_id, patient_id, weight, height, blood_abo, blood_rh, bmi FROM medical_healthcare_patient_summary) mhps ON mhps.patient_id = p.patient_id\n" +
                "LEFT JOIN (SELECT prehistoric_id, patient_id, health_history FROM medical_healthcare_prehistoric) mhp ON mhp.patient_id = p.patient_id\n" +
                "LEFT JOIN (SELECT nation_name, nation_code FROM cats_nations) cn ON cn.nation_code = p.nation_code\n" +
                "LEFT JOIN (SELECT ethnicity_code, NAME FROM cats_ethnicities) ce ON ce.ethnicity_code = p.ethnicity_code\n" +
                "LEFT JOIN (SELECT name, religion_code FROM cats_religions) cr ON cr.religion_code = p.religion_code\n" +
                "LEFT JOIN (SELECT job_id, name FROM cats_jobs) cj ON cj.job_id = p.job_id\n" +
                "LEFT JOIN (SELECT name, healthfacilities_code FROM cats_healthfacilities) ch ON ch.healthfacilities_code = ph.healthfacilities_code\n" +
                "WHERE p.patient_id = :patient_id ");
        HashMap<String, Object> hmapParams = new HashMap<>();
        hmapParams.put("patient_id", itemParamsEntity.getPatientId());
        sql.append("group by patientId");
        return getFirstData(sql, hmapParams, PatientDTO.class);
    }


    /**
     * lay danh sach nguoi than
     *
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity getPatientRelatives(PatientDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        sql.append("select p.patient_id as patientId, p.fullname as fullname, p.avatar as avatar, p.phone_number as phoneNumber, p.gender_id as genderId, p.birthday, crn.name as relationshipName from patients p join (select cr.name as name, pr.patient_id as patient_id from patients_relationships pr join cats_relationships cr on pr.relationship_id = cr.relationship_Id where pr.patient_parent_id = :patient_id) crn on crn.patient_id = p.patient_id");
        HashMap<String, Object> hmapParams = new HashMap<>();
        hmapParams.put("patient_id", itemParamsEntity.getPatientId());
        Integer start = Constants.START_RECORD_DEFAULT;
        if (itemParamsEntity != null && itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = Constants.PAGE_SIZE_DEFAULT;
        if (itemParamsEntity != null && itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        ResultSelectEntity listData = getListDataAndCount(sql, hmapParams, start, pageSize, PatientDTO.class);
        return listData;
    }


    /**
     * api get patients
     *
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity getPatients(PatientDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        sql.append("select result.patientId, result.fullname, result.birthday, result.avatar, result.genderId, result.phoneNumber, result.address, result.provinceName, result.districtName, result.wardName, result.healthInsuranceNumber, bi.totalExams, bi.totalAdvices from (\n" +
                "select p.patient_id as patientId, p.fullname as fullname, p.birthday, p.avatar, p.gender_id as genderId, p.phone_number as phoneNumber, p.address, cp.name as provinceName, cd.name as districtName, cw.name as wardName, ph.healthInsurance_number as healthInsuranceNumber from patients p\n" +
                "left join (select name, province_code from cats_provinces where is_active = 1 and is_delete = 0) cp on p.province_code = cp.province_code\n" +
                "left join (select name, district_code from cats_districts where is_active = 1 and is_delete = 0) cd on p.district_code = cd.district_code\n" +
                "left join (select healthInsurance_number, patient_id, healthfacilities_code from patients_healthinsurances where is_active = 1 and is_delete = 0) ph on p.patient_id = ph.patient_id\n" +
                "left join (select name, ward_code from cats_wards where is_active = 1 and is_delete = 0) cw on p.ward_code = cw.ward_code ");
        HashMap<String, Object> hmapParams = new HashMap<>();
        if (itemParamsEntity != null && itemParamsEntity.getDoctorId() != null) {
            sql.append("inner join (select appointment_doctor_id, patient_id from booking_informations where appointment_doctor_id = :appointmentDoctorId) bi on p.patient_id = bi.patient_id ");
            hmapParams.put("appointmentDoctorId", itemParamsEntity.getDoctorId());
        }
        sql.append("where 1 = 1 and is_delete = 0 ");
        if (itemParamsEntity != null && itemParamsEntity.getFullname() != null) {
            sql.append("and p.fullname like concat('%', :fullname, '%') ");
            hmapParams.put("fullname", itemParamsEntity.getFullname());
        }
        if (itemParamsEntity != null && itemParamsEntity.getEmail() != null) {
            sql.append("and p.email = :email ");
            hmapParams.put("email", itemParamsEntity.getEmail());
        }
        if (itemParamsEntity != null && itemParamsEntity.getFromAge() != null) {
            sql.append("and TIMESTAMPDIFF(year, p.birthday, CURDATE()) >= :fromAge ");
            hmapParams.put("fromAge", itemParamsEntity.getFromAge());
        }
        if (itemParamsEntity != null && itemParamsEntity.getToAge() != null) {
            sql.append("and TIMESTAMPDIFF(year, p.birthday, CURDATE()) <= :toAge ");
            hmapParams.put("toAge", itemParamsEntity.getToAge());
        }
        if (itemParamsEntity != null && itemParamsEntity.getQueryString() != null) {
            sql.append("and (p.fullname like concat('%', :queryString, '%') or p.phone_number like concat('%', :queryString, '%')) ");
            hmapParams.put("queryString", itemParamsEntity.getQueryString());
        }
        if (itemParamsEntity != null && itemParamsEntity.getBirthYear() != null) {
            sql.append("and year(p.birthday) = :birthYear ");
            hmapParams.put("birthYear", itemParamsEntity.getBirthYear());
        }
        if (itemParamsEntity != null && itemParamsEntity.getGenderId() != null) {
            sql.append("and p.gender_id = :genderId ");
            hmapParams.put("genderId", itemParamsEntity.getGenderId());
        }
        if (itemParamsEntity != null && itemParamsEntity.getPhoneNumber() != null) {
            sql.append("and p.phone_number like concat('%', :phoneNumber, '%') ");
            hmapParams.put("phoneNumber", itemParamsEntity.getPhoneNumber());
        }
        if (itemParamsEntity != null && itemParamsEntity.getProvinceCode() != null) {
            sql.append("and p.province_code = :provinceCode ");
            hmapParams.put("provinceCode", itemParamsEntity.getProvinceCode());
        }
        if (itemParamsEntity != null && itemParamsEntity.getWardCode() != null) {
            sql.append("and p.ward_code = :wardCode ");
            hmapParams.put("wardCode", itemParamsEntity.getWardCode());
        }
        if (itemParamsEntity != null && itemParamsEntity.getDistrictCode() != null) {
            sql.append("and p.district_code = :districtCode ");
            hmapParams.put("districtCode", itemParamsEntity.getDistrictCode());
        }
        if (itemParamsEntity != null && itemParamsEntity.getHealthInsuranceNumber() != null) {
            sql.append("and ph.healthInsurance_number = :healthInsuranceNumber ");
            hmapParams.put("healthInsuranceNumber", itemParamsEntity.getHealthInsuranceNumber());
        }
        if (itemParamsEntity != null && itemParamsEntity.getDoctorId() != null) {
            sql.append("group by p.patient_id");
        }
        sql.append(") result left join (select patient_id, SUM(case when booking_group = 1 then 1 else 0 end) as totalExams, SUM(case when booking_group = 2 then 1 else 0 end) as totalAdvices from booking_informations where is_active = 1 and is_delete = 0 group by patient_id) bi on bi.patient_id = result.patientId order by fullname");
        Integer start = Constants.START_RECORD_DEFAULT;
        if (itemParamsEntity != null && itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = Constants.PAGE_SIZE_DEFAULT;
        if (itemParamsEntity != null && itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        ResultSelectEntity listData = getListDataAndCount(sql, hmapParams, start, pageSize, PatientDTO.class);
        return listData;
    }

    @Override
    public List<PatientDTO> exportPatientData(PatientDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        sql.append("select result.fullname, result.birthday, result.genderId, result.phoneNumber, IF(CONCAT_WS(', ', result.address, result.districtName, result.districtName, result.provinceName) > \"\", CONCAT_WS(', ', result.address, result.districtName, result.districtName, result.provinceName), null) as address, result.healthInsuranceNumber, bi.totalExams, bi.totalAdvices from (\n" +
                "select p.patient_id as patientId, p.fullname as fullname, p.birthday, p.gender_id as genderId, p.phone_number as phoneNumber, p.address, cp.name as provinceName, cd.name as districtName, cw.name as wardName, ph.healthInsurance_number as healthInsuranceNumber from patients p\n" +
                "left join (select name, province_code from cats_provinces where is_active = 1 and is_delete = 0) cp on p.province_code = cp.province_code\n" +
                "left join (select name, district_code from cats_districts where is_active = 1 and is_delete = 0) cd on p.district_code = cd.district_code\n" +
                "left join (select healthInsurance_number, patient_id, healthfacilities_code from patients_healthinsurances where is_active = 1 and is_delete = 0) ph on p.patient_id = ph.patient_id\n" +
                "left join (select name, ward_code from cats_wards where is_active = 1 and is_delete = 0) cw on p.ward_code = cw.ward_code ");
        HashMap<String, Object> hmapParams = new HashMap<>();
        if (itemParamsEntity != null && itemParamsEntity.getDoctorId() != null) {
            sql.append("inner join (select appointment_doctor_id, patient_id from booking_informations where appointment_doctor_id = :appointmentDoctorId) bi on p.patient_id = bi.patient_id ");
            hmapParams.put("appointmentDoctorId", itemParamsEntity.getDoctorId());
        }
        sql.append("where 1 = 1 and is_active = 1 and is_delete = 0 ");
        if (itemParamsEntity != null && itemParamsEntity.getFullname() != null) {
            sql.append("and p.fullname like concat('%', :fullname, '%') ");
            hmapParams.put("fullname", itemParamsEntity.getFullname());
        }
        if (itemParamsEntity != null && itemParamsEntity.getEmail() != null) {
            sql.append("and p.email = :email ");
            hmapParams.put("email", itemParamsEntity.getEmail());
        }
        if (itemParamsEntity != null && itemParamsEntity.getFromAge() != null) {
            sql.append("and TIMESTAMPDIFF(year, p.birthday, CURDATE()) >= :fromAge ");
            hmapParams.put("fromAge", itemParamsEntity.getFromAge());
        }
        if (itemParamsEntity != null && itemParamsEntity.getToAge() != null) {
            sql.append("and TIMESTAMPDIFF(year, p.birthday, CURDATE()) <= :toAge ");
            hmapParams.put("toAge", itemParamsEntity.getToAge());
        }
        if (itemParamsEntity != null && itemParamsEntity.getBirthYear() != null) {
            sql.append("and year(p.birthday) = :birthYear ");
            hmapParams.put("birthYear", itemParamsEntity.getBirthYear());
        }
        if (itemParamsEntity != null && itemParamsEntity.getGenderId() != null) {
            sql.append("and p.gender_id = :genderId ");
            hmapParams.put("genderId", itemParamsEntity.getGenderId());
        }

        if (itemParamsEntity != null && itemParamsEntity.getPhoneNumber() != null) {
            sql.append("and p.phone_number like concat('%', :phoneNumber, '%') ");
            hmapParams.put("phoneNumber", itemParamsEntity.getPhoneNumber());
        }
        if (itemParamsEntity != null && itemParamsEntity.getProvinceCode() != null) {
            sql.append("and p.province_code = :provinceCode ");
            hmapParams.put("provinceCode", itemParamsEntity.getProvinceCode());
        }
        if (itemParamsEntity != null && itemParamsEntity.getWardCode() != null) {
            sql.append("and p.ward_code = :wardCode ");
            hmapParams.put("wardCode", itemParamsEntity.getWardCode());
        }
        if (itemParamsEntity != null && itemParamsEntity.getQueryString() != null) {
            sql.append("and (p.fullname like concat('%', :queryString, '%') or p.phone_number like concat('%', :queryString, '%')) ");
            hmapParams.put("queryString", itemParamsEntity.getQueryString());
        }
        if (itemParamsEntity != null && itemParamsEntity.getDistrictCode() != null) {
            sql.append("and p.district_code = :districtCode ");
            hmapParams.put("districtCode", itemParamsEntity.getDistrictCode());
        }
        if (itemParamsEntity != null && itemParamsEntity.getHealthInsuranceNumber() != null) {
            sql.append("and ph.healthInsurance_number = :healthInsuranceNumber ");
            hmapParams.put("healthInsuranceNumber", itemParamsEntity.getHealthInsuranceNumber());
        }
        if (itemParamsEntity != null && itemParamsEntity.getDoctorId() != null) {
            sql.append("group by p.patient_id");
        }
        sql.append(") result left join (select patient_id, SUM(case when booking_group = 1 then 1 else 0 end) as totalExams, SUM(case when booking_group = 2 then 1 else 0 end) as totalAdvices from booking_informations where is_active = 1 and is_delete = 0 group by patient_id) bi on bi.patient_id = result.patientId");
        List<PatientDTO> listData = (List<PatientDTO>) getListData(sql, hmapParams, null, null, PatientDTO.class);
        return listData;
    }

    //TODO add key healthInsuranceNumber
    @Override
    public Object getPatientByParams(CovidPatientResultDTO params) {
        HashMap<String, Object> hmapParams = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT patient_id as patientId, fullname FROM patients WHERE is_active=1 AND is_delete=0 ");
        if(params.getPersonalPhoneNumber()!=null && !"".equals(params.getPersonalPhoneNumber())) {
            sql.append(" and phone_number=:phoneNumber ");
            hmapParams.put("phoneNumber", params.getPersonalPhoneNumber());
        }
//        if(params.getBirthday()!=null) {
//            Date birthday = new Date(params.getBirthday());
//            sql.append(" and cast(birthday as date) = cast(:birthday as date) ");
//            hmapParams.put("birthday", birthday);
//        }
        if(params.getFullname()!=null && !"".equals(params.getFullname())) {
            sql.append(" and fullname=:fullname ");
            hmapParams.put("fullname", params.getFullname());
        }
//        if(params.getIdentification()!=null && !"".equals(params.getIdentification())) {
//            sql.append(" and identification=:identification ");
//            hmapParams.put("identification", params.getIdentification());
//        }

        return getListData(sql, hmapParams, null, null, PatientDTO.class);
    }

}