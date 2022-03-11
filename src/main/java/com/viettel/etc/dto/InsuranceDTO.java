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
 * @date Tue Aug 18 10:12:25 ICT 2020
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class InsuranceDTO {

    Integer patientId;

    Integer healthinsuranceId;

    String healthinsuranceNumber;

    Date fromDate;

    Date toDate;

    Date fiveYearsDate;

    String areaCode;

    String healthfacilitiesCode;

    String healthfacilytiesName;

    String provinceCode;

    String provinceName;

    String districtCode;

    String districtName;

    String wardCode;

    String wardName;

    Integer startrecord;

    Integer pagesize;

    Boolean resultSqlEx;

    String name;

    String phoneNumber;

    Boolean isExist;
}