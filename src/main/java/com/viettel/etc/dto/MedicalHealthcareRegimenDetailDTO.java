package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalHealthcareRegimenDetailDTO {

    private Integer detailId;

    private Integer detailNameId;

    private String detailName;

    private Integer detailType;

    private Integer repeatStatus;

    private Integer repeatNumber;

    private String fromTime;

    private Integer timeDistance;

    private String fromDayofweek;

    private Date fromDate;

    private Date toDate;

    private Integer fromDay;

    private Integer fromMonth;

    private String description;

    //    public void setDetailType(Integer detailType) throws TeleCareException {
//        List<Integer> detailTypes = Arrays.asList(1, 2, 3, 4, 5);
//        if (!detailTypes.contains(detailType)) {
//            FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
//        }
//        this.detailType = detailType;
//    }
//
//    public void setDetailNameId(Integer detailNameId) throws TeleCareException {
//        // type = 1 is direction => have detailNameId to direction type
//        if (detailType != null && this.detailType == 1) {
//            List<Integer> detailNameIds = Arrays.asList(1, 2, 3);
//            if (!detailNameIds.contains(detailNameId)) {
//                FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
//            }
//            this.detailNameId = detailNameId;
//        }
//        // type != 1 => detailNameId = 0;
//    }
//
//    public void setDetailName(String detailName) {
//        if (detailType != null && this.detailType == 1) {
//            switch (this.detailNameId) {
//                case 1:
//                    detailName = "Huyết áp";
//                    break;
//                case 2:
//                    detailName = "Đường huyết";
//                    break;
//                case 3:
//                    detailName = "Nhịp tim";
//                    break;
//            }
//        }
//        this.detailName = detailName;
//    }
//
//    public void setRepeatStatus(Integer repeatStatus) throws TeleCareException {
//        List<Integer> repeatStatuses = Arrays.asList(1, 2, 3);
//        if (!repeatStatuses.contains(repeatStatus)) {
//            FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
//        }
//        this.repeatStatus = repeatStatus;
//    }
//
//    public void setRepeatNumber(Integer repeatNumber) throws TeleCareException {
//        if (repeatStatus != null && (this.repeatStatus == 2 || this.repeatStatus == 3)) {
//            List<Integer> repeatNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//            if (!repeatNumbers.contains(repeatNumber)) {
//                FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
//            }
//            this.repeatNumber = repeatNumber;
//        }
//    }
//
//    public void setFromDayofweek(String fromDayofweek) {
//        if (repeatStatus != null && this.repeatStatus == 3) {
//            this.fromDayofweek = fromDayofweek;
//        }
//    }
//
//    public void setTimeDistance(Integer timeDistance) {
//        if (repeatStatus != null && (this.repeatStatus == 2 || this.repeatStatus == 3)) {
//            this.timeDistance = timeDistance;
//        }
//    }
    public void validateRegimenSubDTO() throws TeleCareException {
        List<Integer> detailTypes = Arrays.asList(1, 2, 3, 4, 5);
        if (!detailTypes.contains(detailType)) {
            FnCommon.throwsErrorApp(ErrorApp.ERROR_DETAIL_TYPE_NOT_EXIST);
        }
        // type = 1 is direction => have detailNameId to direction type
        if (this.detailType == 1) {
            List<Integer> detailNameIds = Arrays.asList(1, 2, 3);
            if (!detailNameIds.contains(detailNameId)) {
                this.detailNameId = 0;
            }
        }
        // type != 1 => detailNameId = 0;
        if (this.detailType == 1) {
            switch (this.detailNameId) {
                case 1:
                    detailName = "Huyết áp";
                    break;
                case 2:
                    detailName = "Đường huyết";
                    break;
                case 3:
                    detailName = "Nhịp tim";
                    break;
            }
        }

        List<Integer> repeatStatuses = Arrays.asList(1, 2, 3);
        if (!repeatStatuses.contains(repeatStatus)) {
            FnCommon.throwsErrorApp(ErrorApp.ERR_REPEAT_STATUS_NOT_EXIST);
        }

        if (this.repeatStatus == 2 || this.repeatStatus == 3) {
            List<Integer> repeatNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            if (!repeatNumbers.contains(repeatNumber)) {
                FnCommon.throwsErrorApp(ErrorApp.ERR_REPEAT_NUMBER_NOT_EXIST);
            }
        }

        if (this.repeatStatus != 3) {
            this.fromDayofweek = null;
        }

        if (!(this.repeatStatus == 2 || this.repeatStatus == 3)) {
            this.timeDistance = null;
        }
    }

}
