package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Doctors
 *
 * @author ToolGen
 * @date Tue Sep 08 09:14:14 ICT 2020
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "DOCTORS")
public class DoctorsEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DOCTOR_ID")
	Integer doctorId;

	@Column(name = "QR_CODE")
	String qrCode;

	@Column(name = "DOCTOR_CODE")
	String doctorCode;

	@Column(name = "FULLNAME")
	String fullname;

	@Column(name = "KEYCLOAK_USER_ID")
	String keycloakUserId;

	@Column(name = "BIRTHDAY")
	Date birthday;

	@Column(name = "GENDER_ID")
	Integer genderId;

	@Column(name = "DOCTOR_TYPE")
	Integer doctorType;

	@Column(name = "PROVINCE_CODE")
	String provinceCode;

	@Column(name = "DISTRICT_CODE")
	String districtCode;

	@Column(name = "WARD_CODE")
	String wardCode;

	@Column(name = "ADDRESS")
	String address;

	@Column(name = "PHONE_NUMBER")
	String phoneNumber;

	@Column(name = "EMAIL")
	String email;

	@Column(name = "CERTIFICATION_CODE")
	String certificationCode;

	@Column(name = "CERTIFICATION_DATE")
	Date certificationDate;

	@Column(name = "POSITION_CODE")
	String positionCode;

	@Column(name = "ACADEMIC_RANK_ID")
	Integer academicRankId;

	@Column(name = "DEGREE_ID")
	Integer degreeId;

	@Column(name = "NATION_CODE")
	String nationCode;

	@Column(name = "ETHNICITY_CODE")
	String ethnicityCode;

	@Column(name = "RELIGION_CODE")
	String religionCode;

	@Column(name = "AVATAR")
	String avatar;

	@Column(name = "DESCRIPTION")
	String description;

	@Column(name = "SUMMARY")
	String summary;

	@Column(name = "IS_SYNC")
	String isSync;

	@Column(name = "IS_ACTIVE")
	Integer isActive;

	@Column(name = "IS_DELETE")
	Integer isDelete;

	@Column(name = "IS_MEDICALEXAMINATION")
	String isMedicalexamination;

	@Column(name = "IS_CONSULTANT")
	String isConsultant;

	@Column(name = "ALLOW_FILTER")
	String allowFilter;

	@Column(name = "ALLOW_SEARCH")
	String allowSearch;

	@Column(name = "ALLOW_BOOKING")
	String allowBooking;

	@Column(name = "CREATE_USER_ID")
	Integer createUserId;

	@Column(name = "CREATE_DATE")
	Date createDate = new Date();

	@Column(name = "UPDATE_USER_ID")
	Integer updateUserId;

	@Column(name = "UPDATE_DATE")
	Date updateDate;

	@Column(name = "HIS_ID")
	String hisid;
}