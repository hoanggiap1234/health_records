package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class CovidNotificationDTO {
    private String fullname;

    private String phoneNumber;

    private String injectionDate;

    private Integer contentType;

    private Integer patientId;
}
