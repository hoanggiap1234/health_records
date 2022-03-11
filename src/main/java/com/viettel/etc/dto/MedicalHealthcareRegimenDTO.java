package com.viettel.etc.dto;import com.fasterxml.jackson.annotation.JsonInclude;import com.fasterxml.jackson.annotation.JsonInclude.Include;import java.util.Date;import java.util.List;import lombok.Data;import lombok.NoArgsConstructor;/** * Autogen class DTO: * * @author ToolGen * @date Tue Oct 13 09:50:35 ICT 2020 */@Data@NoArgsConstructor@JsonInclude(Include.NON_NULL)public class MedicalHealthcareRegimenDTO {    Integer patientId;    String patientName;    Date patientBirthday;    Integer patientGenderId;    String patientPhoneNumber;    Integer patientRegimenId;    Integer doctorId;    String doctorName;    String degreeCode;    String academicRankCode;    String name;    Integer groupId;    String groupName;    String description;    Integer totalTime;    Date fromDate;    Date toDate;    Integer status;    Integer startrecord;    Integer pagesize;    Boolean resultSqlEx;    String queryString;    Boolean isActiveBoolean;    Integer isActive;    List<MedicalHealthcareRegimenDetailDTO> regimens;    public Integer getIsActive() {        if (isActiveBoolean != null) {            this.setIsActive(isActiveBoolean ? 1 : 0);            this.setIsActiveBoolean(null);        }        return isActive;    }}