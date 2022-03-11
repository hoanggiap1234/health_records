package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Autogen class DTO:
 *
 * @author ToolGen
 * @date Thu Sep 10 16:04:19 ICT 2020
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MedicalHealthcarePatientDetailDTO {

	Integer detailId;

	Integer patientId;

	Integer historiesId;

	Integer pulse;

	Double temperature;

	Double bloodSugar;

	Integer spo2Score;

	Integer bloodPressure;

	Integer heartBeat;

	Integer bloodPressureMin;

	Integer bloodPressureMax;

	Integer breath;

	Double weight;

	Double height;

	Double bmi;

	Integer waistCircumference;

	String indexType;

	Long resultDate;

	Integer sourceId;

	String deviceName;

	String deviceImei;

	Boolean isSync;

	Integer startrecord;

	Integer pagesize;

	Boolean resultSqlEx;

//	public Double getBmi() {
//		if (this.weight != null && this.height != null &&
//				this.weight > 0 && this.height > 0) {
//			double heightm = (double) this.height / 100;
//
//			return this.weight / Math.pow(heightm, 2);
//		}
//		return null;
//	}
}
