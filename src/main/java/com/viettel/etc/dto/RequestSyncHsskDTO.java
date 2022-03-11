package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestSyncHsskDTO {
    private Long fromDate;
    private Long toDate;
    private String PID;
    private String token;
    private Integer patientId;
    private String phoneNumber;
}
