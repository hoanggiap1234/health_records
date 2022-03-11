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
 * @date Mon Sep 28 17:02:36 ICT 2020
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PatientsIdentificationDTO {

    Integer id;

    String pid;

    String fullname;

    Integer genderId;

    Date birthday;

    String provinceCode;

    String provinceName;

    String districtCode;

    String districtName;

    String wardCode;

    String wardName;

    String phoneNumber;

    String identification;

    String healthinsuranceNumber;

    String healthfacilitiesCode;

    String healthfacilitiesName;

    Date fromDate;

    Date toDate;

    String areaCode;

    Date fiveYearsDate;

    Integer isCheck;

    String isDelete;

    String isActive;

    Integer createUserId;

    Date createDate;

    Integer updateUserId;

    Long updateDate;

    Integer startrecord;

    Integer pagesize;

    Boolean resultSqlEx;
}
