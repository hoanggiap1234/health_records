package com.viettel.etc.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CovidPatientDTO {

    private String fullname;

    private String personalPhoneNumber;

    private Date birthday;

    private String identification;

    private String healthInsuranceNumber;

}
