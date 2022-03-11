package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Autogen class DTO:
 *
 * @author ToolGen
 * @date Fri Feb 26 09:26:19 ICT 2021
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CovidPatientResultDTO {

    @NotNull
    String personalPhoneNumber;

    String identification;

    String fullname;

    Long birthday;

    @NotNull
    Integer vaccineId;

    String batchNumber;

    @NotNull
    String injectionPlace;

    @NotNull
    Integer ekipId;

    @NotNull
    Date injectionDate;

    String userName ;

    Integer planId;

    String healthInsuranceNumber;

    String healthfacilitiesCode;

    String vaccinationFacility;

    String vaccineNameVi;

    Integer numberVaccine;

    String healthfacilitiesId;

    String language;

    Boolean syncTelecare;

    String syncTelecareResult;

    Date expiryDate;

    Integer producerId;

    String injection_date;

    String antigen;

    Integer dataSource;

    Integer planIdTC;

    Integer injectionsNumber;
}