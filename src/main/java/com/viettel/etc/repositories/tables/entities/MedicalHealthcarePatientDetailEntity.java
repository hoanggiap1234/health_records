package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Autogen class Entity: Create Entity For Table Name Medical_healthcare_patient_detail
 *
 * @author ToolGen
 * @date Thu Sep 10 16:04:22 ICT 2020
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "MEDICAL_HEALTHCARE_PATIENT_DETAIL")
public class MedicalHealthcarePatientDetailEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DETAIL_ID")
	Integer detailId;

	@Column(name = "PATIENT_ID")
	Integer patientId;

	@Column(name = "HISTORIES_ID")
	Integer historiesId;

	@Column(name = "HEART_BEAT")
	Integer heartBeat;

	@Column(name = "BLOOD_PRESSURE_MIN")
	Integer bloodPressureMin;

	@Column(name = "BLOOD_PRESSURE_MAX")
	Integer bloodPressureMax;

	@Column(name = "PULSE")
	Integer pulse;

	@Column(name = "TEMPERATURE")
	Double temperature;

	@Column(name = "BLOOD_SUGAR")
	Double bloodSugar;

	@Column(name = "SPO2_SCORE")
	Integer spo2Score;

	@Column(name = "BREATH")
	Integer breath;

	@Column(name = "WEIGHT")
	Double weight;

	@Column(name = "HEIGHT")
	Double height;

	@Column(name = "BMI")
	Double bmi;

	@Column(name = "KCAL")
	Double kcal;

	@Column(name = "WALK")
	Integer walk;

	@Column(name = "WAIST_CIRCUMFERENCE")
	Integer waistCircumference;

	@Column(name = "RESULT_DATE")
	Date resultDate;

	@Column(name = "SOURCE_ID")
	Integer sourceId;

	@Column(name = "DEVICE_NAME")
	String deviceName;

	@Column(name = "DEVICE_IMEI")
	String deviceImei;

	@Column(name = "IS_SYNC")
	Boolean isSync = false;

	@Column(name = "IS_DELETE")
	Boolean isDelete = false;

	@Column(name = "IS_ACTIVE")
	Boolean isActive = true;

	@Column(name = "CREATE_USER_ID")
	Integer createUserId;

	@Column(name = "CREATE_DATE")
	Date createDate = new Date();

	@Column(name = "UPDATE_USER_ID")
	Integer updateUserId;

	@Column(name = "UPDATE_DATE")
	Date updateDate;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MedicalHealthcarePatientDetailEntity that = (MedicalHealthcarePatientDetailEntity) o;
		return patientId.equals(that.patientId) &&
				Objects.equals(historiesId, that.historiesId) &&
				resultDate.getTime()==that.resultDate.getTime();
	}

	@Override
	public int hashCode() {
		return Objects.hash(patientId, historiesId, resultDate);
	}
}
