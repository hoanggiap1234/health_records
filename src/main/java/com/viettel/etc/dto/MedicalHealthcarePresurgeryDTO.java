package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Autogen class DTO:
 *
 * @author ToolGen
 * @date Tue Sep 22 11:31:18 ICT 2020
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MedicalHealthcarePresurgeryDTO {

    Integer presurgeryId;

    Integer historiesId;

    Integer relationshipId;

    String relationshipName;

    String surgicalSurgeryName;

    String healthfacilitiesCode;

    String healthfacilitiesName;

    Integer yearOfSurgery;

    String reasonOfSurgery;

    String sideEffectOfSurgery;

    Integer patientId;

    Integer startrecord;

    Integer pagesize;

    Boolean resultSqlEx;

    Date createDate;

    String phoneNumber;

    Integer isDelete;

    Integer isActive;

    Integer isSync;
}
