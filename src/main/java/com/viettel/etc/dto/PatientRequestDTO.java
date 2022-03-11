package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientRequestDTO {

    public PatientRequestDTO(String fullname, String phoneNumber, String email, String password) {
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public PatientRequestDTO(String fullname, String phoneNumber, String email) {
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    Integer patientId;

    String patientCode;

    String hisId;

    String pid;

    String qrCode;

    String fullname;

    Date birthday;

    Integer genderId;

    String nationCode;

    String ethnicityCode;

    String religionCode;

    Integer jobId;

    String avatar;

    String identification;

    Date identificationDate;

    String identificationPlace;

    String phoneNumber;

    String email;

    String provinceCode;

    String districtCode;

    String wardCode;

    String address;

    String hisCode;

    Integer isActive;

    Integer isDelete;

    Integer isSync;

    Integer createUserId;

    Date createDate;

    Date updateDate;

    Integer updateUserId;

    String password;

    Integer relationshipId;

}
