package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.BookingInformationsDTO;
import com.viettel.etc.repositories.BookingInformationsRepository;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * Autogen class Repository Impl:
 *
 * @author ToolGen
 * @date Mon Sep 14 10:19:45 ICT 2020
 */
@Repository
@Log4j2
public class BookingInformationsRepositoryImpl extends CommonDataBaseRepository implements BookingInformationsRepository {

	/**
	 * lay booking theo id
	 *
	 * @param bookingId: params client truyen len
	 * @return
	 */
	@Override
	public BookingInformationsDTO getById(int bookingId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT " +
				"d.fullname AS appointmentDoctorName , " +
				"d.doctor_id AS appointmentDoctorId, " +
				"h.healthfacilities_code AS healthfacilitiesCode, " +
				"h.name AS healthfacilitiesName, " +
				"bi.symptoms_or_reason AS symptomsOrReason , " +
				"bi.patient_id AS patientId, " +
				"bi.histories_id AS historiesId, " +
				"bi.booking_id AS bookingId " +
				"FROM booking_informations bi " +
				"LEFT JOIN (" +
				"SELECT doctor_id, fullname FROM doctors" +
				") d ON bi.appointment_doctor_id = d.doctor_id " +
				"LEFT JOIN (" +
				"SELECT healthfacilities_code, name FROM cats_healthfacilities" +
				") h ON h.healthfacilities_code = bi.healthfacilities_code " +
				"WHERE bi.booking_id=:bookingId");
		HashMap<String, Object> arrParams = new HashMap<>();
		arrParams.put("bookingId", bookingId);

		return (BookingInformationsDTO) getFirstData(sql, arrParams, BookingInformationsDTO.class);
	}
}
