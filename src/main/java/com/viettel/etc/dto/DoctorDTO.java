package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDTO {

    Integer doctorId;

    Double medicalExaminationFee;

    Integer doctorPhaseId;

    Integer phaseId;
}
