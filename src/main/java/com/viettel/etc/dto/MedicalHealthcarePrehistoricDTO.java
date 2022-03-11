package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Autogen class DTO:
 *
 * @author ToolGen
 * @date Thu Sep 17 17:27:42 ICT 2020
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MedicalHealthcarePrehistoricDTO {

    Integer prehistoricId;

    Integer historiesId;

    Integer relationshipId;

    String relationshipName;

    String healthHistory;

    Integer patientId;

    String patientName;

    Integer genderId;

    Long birthday;

    List<MedicalHealthcareAllergyDTO> allery;

    List<MedicalHealthcarePrehistoricDTO> prehistoric;

    List<MedicalHealthcarePresurgeryDTO> presurgery;

    Integer isSync;

    Integer isDelete;

    Integer isActive;

    String phoneNumber;

    Integer startrecord;

    Integer pagesize;

    Boolean resultSqlEx;
}
