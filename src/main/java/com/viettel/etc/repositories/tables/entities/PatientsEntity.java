package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

import static com.viettel.etc.utils.Constants.*;

/**
 * Autogen class Entity: Create Entity For Table Name Patients
 *
 * @author ToolGen
 * @date Wed Aug 12 17:57:16 ICT 2020
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "PATIENTS")
public class PatientsEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PATIENT_ID")
	Integer patientId;
	@Column(name = "PATIENT_CODE")
	String patientCode;
	@Column(name = "HIS_ID")
	String hisId;
	@Column(name = "PID")
	String pid;
	@Column(name = "QR_CODE")
	String qrCode;
	@Column(name = "FULLNAME")
	String fullname;
	@Column(name = "BIRTHDAY")
	Date birthday;
	@Column(name = "GENDER_ID")
	Integer genderId;
	@Column(name = "NATION_CODE")
	String nationCode;
	@Column(name = "ETHNICITY_CODE")
	String ethnicityCode;
	@Column(name = "RELIGION_CODE")
	String religionCode;
	@Column(name = "JOB_ID")
	Integer jobId;
	@Column(name = "AVATAR")
	String avatar;
	@Column(name = "IDENTIFICATION")
	String identification;
	@Column(name = "IDENTIFICATION_DATE")
	Date identificationDate;
	@Column(name = "IDENTIFICATION_PLACE")
	String identificationPlace;
	@Column(name = "PHONE_NUMBER")
	String phoneNumber;
	@Column(name = "EMAIL")
	String email;
	@Column(name = "PROVINCE_CODE")
	String provinceCode;
	@Column(name = "DISTRICT_CODE")
	String districtCode;
	@Column(name = "WARD_CODE")
	String wardCode;
	@Column(name = "ADDRESS")
	String address;
	@Column(name = "HIS_CODE")
	String hisCode;
	@Column(name = "IS_ACTIVE")
	Integer isActive = IS_ACTIVE;
	@Column(name = "IS_DELETE")
	Integer isDelete = IS_NOT_DELETE;
	@Column(name = "IS_SYNC")
	Integer isSync = IS_NOT_SYNC;
	@Column(name = "CREATE_USER_ID")
	Integer createUserId;
	@Column(name = "CREATE_DATE", insertable = false, updatable = false)
	Date createDate;
	@Column(name = "UPDATE_DATE")
	Date updateDate;
	@Column(name = "UPDATE_USER_ID")
	Integer updateUserId;
	@Column(name = "KEYCLOAK_USER_ID")
	String keycloakUserId;

	public PatientsEntity(int patientId, String patientCode, String fullname, String phoneNumber, String email) {
		this.patientId = patientId;
		this.patientCode = patientCode;
		this.fullname = fullname;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

}