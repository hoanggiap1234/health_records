package com.viettel.etc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HSSKErrorMessage {
    private ErrorMessage mess;

    @Data
    @NoArgsConstructor
    public static class ErrorMessage {
        private String code;
        private String description;
        private String messCode;
    }
}
