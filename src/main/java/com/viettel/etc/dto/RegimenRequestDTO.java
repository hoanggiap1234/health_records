package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegimenRequestDTO {

    private Integer doctorId;

    private Integer patientId;

    private Integer groupId;

    private Integer regimenId;

    private String name;

    private List<RegimenDetailRequestDTO> regimens;
}
