package com.viettel.etc.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVaccinationHistoryDTO {
    private VaccinationHistoryData data;

    @Data
    @NoArgsConstructor
    public static class VaccinationHistoryData {
        private Integer count;

        private List<VaccinationHistoryDTO> listData;
    }

    @Data
    @NoArgsConstructor
    public static class VaccinationHistoryDTO {

        private Integer vaccinationId;

        private String healthfacilitiesCode;

        private String healthfacilitiesName;

        private String vaccinationFacility;

        private String vaccineName;

        private Integer injectionsNumber;

        private String batchNumber;

        private Long injectionDate;

    }
}
