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
 * @date Thu Aug 27 11:39:09 ICT 2020
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MedicalHealthcareHistoryDTO {

	Integer historiesId;

	Integer bookingId;

	Integer bookingType;

	String hisId;

	Integer patientId;

	Date examinationDate;

	String doctorName;

	String patientName;

	Integer doctorId;

	Integer academicRankId;

	String academicRankCode;

	String academicRankName;

	Integer degreeId;

	String degreeCode;

	String degreeName;

	String healthfacilitiesCode;

	String healthfacilitiesName;

	String reasonsMedicalexamination;

	String symptoms;

	String concludesDisease;

	String treatmentDirection;

	Date reExaminationDate;

	Integer typeOfExamination;

	String diseasesCode;

	String diseasesName;

	String rightEyeWithGlasses;

	String leftEyeWithGlasses;

	String rightEyeWithoutGlasses;

	String leftEyeWithoutGlasses;

	String bodySkin;

	String bodySkinOther;

	String bodyPartHeart;

	String bodyPartRespiratory;

	String bodyPartDigest;

	String bodyPartUrinary;

	String bodyPartOsteoarthritis;

	String bodyPartEndocrine;

	String bodyPartNerve;

	String bodyPartMental;

	String bodyPartSurgical;

	String bodyPartGynecology;

	String bodyPartEarNoseThroat;

	String bodyPartDentomaxillofacial;

	String bodyPartEye;

	String bodyPartSkin;

	String bodyPartNutrition;

	String bodyPartMotive;

	String bodyPartEvaluation;

	String bodyPartOther;

	String typeOfExaminations;

	MedicalHealthcarePatientDetailDTO livingFunction;

	List<MedicalHealthcareServiceDTO> medicalServices;

	List<DrugsDTO> drugs;

	String phoneNumber;

	Integer startrecord;

	Integer pagesize;

	Boolean resultSqlEx;

	String patientAvatar;

	Integer patientGenderId;

	Date patientBirthday;

	String patientPhoneNumber;

	public String getPatientAvatar() {
		return (patientAvatar == null || Base64Util.isBase64String(patientAvatar)) ? patientAvatar
				: Base64Util.toBase64(patientAvatar);
	}

	List<MedicalHealthcareHistoryAttachmentDTO> attachments;

	MedicalHealthcareHistoryAttachmentConsultantFileDTO consultantFile;

	public MedicalHealthcareHistoryAttachmentConsultantFileDTO getConsultantFile() {
		MedicalHealthcareHistoryAttachmentConsultantFileDTO dto = new MedicalHealthcareHistoryAttachmentConsultantFileDTO();
		dto.setAttachmentId(1);
		dto.setName("File ghi âm cuộc gọi.");
		dto.setFile("https://x2convert.com/vi/Listen?l=yKDBnnQBInlyDXXwhXdH&vid=https%3a%2f%2fx2convert.com%2fvi%2fThankyou%3ftoken%3dU2FsdGVkX19hJ9pBG30hxBXrTjz1BmkEDDlBHMlq9nPw3Qzc26t4pAZelQEJuudVNZVYjCt%252blpBgBixuIXScz0Lc%252bbFXzMfItgPUGJ8FDmJoDBNwZ7i7QcPkyEpPbLlTzuWjljLqcp1NAZ%252fEcvuoW9MqX0mAcVMvSG2mO09ZjIi0laWw3UljEN%252f%252btW0EQ%252bLq%26s%3dyoutube%26id%3d%26h%3d7870914174316541921&audio=1");
		return dto;
	}

}
