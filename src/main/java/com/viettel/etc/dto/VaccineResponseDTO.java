package com.viettel.etc.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VaccineResponseDTO {

    VaccineResponseDTO.VaccineSubDTO data;

    @Data
    @NoArgsConstructor
    public static class VaccineSubDTO {

        String healthfacilitiesCode;

        String vaccinationFacility;

        String vaccineNameVi;

        Integer numberVaccine;
    }

}
