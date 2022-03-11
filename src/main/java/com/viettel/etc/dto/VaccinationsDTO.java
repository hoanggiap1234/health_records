package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Autogen class DTO: 
 * 
 * @author ToolGen
 * @date Thu Aug 27 09:47:43 ICT 2020
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class VaccinationsDTO {

//    @JsonProperty("vaccination_id")
    Integer vaccinationId;

    String vaccineName;

    String healthfacilitiesCode;

    String healthfacilitiesName;

    String antigen;

    Date injectionDate;

    Integer injectionsNumber;

    String vaccinationFacility;

    String reactionLevel;

    Date reactionTime;

    String result;

    Integer patientId;

    String phoneNumber;
    
    String batchNumber;

    Integer startrecord;

    Integer pagesize;

    Boolean resultSqlEx;

    String Name;

    String fullname;

    String language;

}
