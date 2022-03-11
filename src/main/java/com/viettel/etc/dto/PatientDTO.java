package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.viettel.etc.utils.Base64Util;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Autogen class DTO:
 *
 * @author ToolGen
 * @date Wed Aug 12 17:57:15 ICT 2020
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PatientDTO {

	String fullname;
	String pid;
	String provinceName;
	String wardName;
	Integer healthInsuranceId;
	String healthInsuranceNumber;
	Long totalExams;
	Long totalAdvices;
	String districtName;
	Integer patientId;
	String email;
	String identification;
	Double height;
	Double weight;
	Double bmi;
	String bloodABO;
	String bloodRh;
	String healthHistory;
	String nationName;
	String ethnicityName;
	String religionName;
	Date insurenceFromDate;
	Date insurenceToDate;
	Date fiveYearsDate;
	String areaCode;
	String healthfacilitiesCode;
	String healthfacilitiesName;
	String jobName;
	Integer startrecord;
	Integer pagesize;
	Boolean resultSqlEx;
	String avatar;
	Date birthday;
	String relationshipName;
	String provinceCode;
	String districtCode;
	String wardCode;
	String nationCode;
	String ethnicityCode;
	String religionCode;
	String jobId;
	String phoneNumber;
	Integer relationshipId;
	Integer genderId;
	String address;
	Integer doctorId;
	Integer toAge;
	Integer fromAge;
	Integer birthYear;
	String queryString;
	String keycloakUserId;
	Integer summaryId;
	Integer prehistoricId;
	String language;

	public PatientDTO(String fullname, Integer patientId, String email) {
		this.fullname = fullname;
		this.patientId = patientId;
		this.email = email;
	}

	public String getAvatar() {
		return (avatar == null || Base64Util.isBase64String(avatar)) ? avatar
				: Base64Util.toBase64(avatar);
	}

}