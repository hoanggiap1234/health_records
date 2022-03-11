package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.DoctorDTO;
import com.viettel.etc.dto.PatientDTO;
import com.viettel.etc.dto.ServiceDTO;
import com.viettel.etc.repositories.PriceRepository;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class PriceRepositoryImpl extends CommonDataBaseRepository implements PriceRepository  {
    @Override
    public List<DoctorDTO> getDoctorExamPrice(Integer doctorId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dp.phase_id AS doctorPhaseId," +
                "        medical_examination_fee AS medicalExaminationFee," +
                "        cp.phase_id as phaseId" +
                " FROM(" +
                " SELECT doctor_id," +
                "        phase_id," +
                "        medical_examination_fee" +
                " FROM doctors_prices" +
                " WHERE is_active=1 AND is_delete=0 AND doctor_id=:doctorId) AS dp" +
                " LEFT JOIN (SELECT phase_id, healthfacilities_code FROM cats_phases WHERE is_active=1 AND is_delete=0 AND  from_date < CURDATE() <to_date ) AS cp\n" +
                " ON dp.phase_id = cp.phase_id" +
                " ORDER BY dp.phase_id DESC");

        HashMap<String, Object> hmapParams = new HashMap<>();
        hmapParams.put("doctorId", doctorId);
        Integer start = Constants.START_RECORD_DEFAULT;
        Integer pageSize = 50;
        List<DoctorDTO> listData = (List<DoctorDTO>) getListData(sql, hmapParams, start, pageSize, DoctorDTO.class);
        return listData;
    }

    @Override
    public List<ServiceDTO> getServicePrice(Integer serviceId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT cs.service_id as serviceId," +
                "        cs.name as serviceName," +
                "        csp.phase_id AS servicePhaseId," +
                "        prices as servicePrice," +
                "        cp.phase_id as phaseId" +
                " FROM(" +
                " SELECT service_id," +
                "        name" +
                " FROM cats_services" +
                " WHERE is_active=1 AND is_delete=0 AND service_id=:serviceId) cs" +
                " LEFT JOIN(" +
                " SELECT service_id," +
                "        phase_id," +
                "        prices" +
                " FROM cats_services_prices" +
                " WHERE is_active=1 AND is_delete=0 AND service_id=:serviceId) AS csp ON cs.service_id=csp.service_id " +
                " LEFT JOIN (" +
                " SELECT phase_id," +
                "       healthfacilities_code" +
                " FROM cats_phases WHERE is_active=1 AND is_delete=0 AND from_date < CURDATE() <to_date ) AS cp" +
                " ON csp.phase_id = cp.phase_id" +
                " ORDER BY csp.phase_id DESC");

        HashMap<String, Object> hmapParams = new HashMap<>();
        hmapParams.put("serviceId", serviceId);
        Integer start = Constants.START_RECORD_DEFAULT;
        Integer pageSize = 50;
        List<ServiceDTO> listData = (List<ServiceDTO>) getListData(sql, hmapParams, start, pageSize, ServiceDTO.class);
        return listData;
    }
}
