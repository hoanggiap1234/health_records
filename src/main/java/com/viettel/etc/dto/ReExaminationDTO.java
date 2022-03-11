package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReExaminationDTO {
    Integer historiesId;

    Integer BookingId;

    Integer bookingType;

    String fullname;

    Date approveDate;

    String healthfacilitiesCode;

    String healthfacilitiesName;

    String doctorName;

    Integer doctorId;

    String academicRankCode;

    String degreeCode;

    String patientId;

    String healthfacilitiesAddress;

    String healthfacilitiesWard;

    String healthfacilitiesDistrict;

    String healthfacilitiesProvince;

    Double medicalExaminationFee;

    String bookingGroup;

    ServiceDTO services;

    Integer serviceId;
}
