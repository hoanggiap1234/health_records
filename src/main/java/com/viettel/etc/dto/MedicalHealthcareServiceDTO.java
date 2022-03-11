package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.viettel.etc.utils.Base64Util;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Autogen class DTO:
 *
 * @author ToolGen
 * @date Thu Aug 27 15:08:49 ICT 2020
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MedicalHealthcareServiceDTO {
	Integer serviceId;
	Integer historiesId;
	Integer patientId;
	String patientName;
	String indexName;

	String serviceName;
	Integer groupId;
	String concludes;
	String results;
	String suggestions;
	String linkViewDicom;
	String hisId;
	Date decisionDate;
	Integer healthfacilitiesCode;
	String healthfacilitiesName;
	String indexCode;
	String value;
	String fileName;
	String url;
	Integer startrecord;
	Integer pagesize;
	Boolean resultSqlEx;
	String phoneNumber;
	List<MedicalHealthcareServiceDTO> indexs;
	List<MedicalHealthcareServiceDTO> attachments;
	String unit;
	Integer quantity;
	Integer isSync;
	String diseasesCode;
	String diseasesName;

	public String getUrl() {
		return (url == null || Base64Util.isBase64String(url)) ? url
				: Base64Util.toBase64(url);
	}
}