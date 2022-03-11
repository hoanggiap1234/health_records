package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Autogen class DTO:
 *
 * @author ToolGen
 * @date Mon Sep 14 10:19:45 ICT 2020
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BookingInformationsDTO {

	Integer bookingId;

	Integer bookingType;

	Integer bookingGroup;

	String ticketCode;

	Integer bookingNumber;

	String qrCode;

	String healthfacilitiesCode;

	String healthfacilitiesName;

	Integer doctorId;

	Integer patientBookingId;

	Integer patientId;

	Integer serviceId;

	Integer sourceId;

	String symptomsOrReason;

	Date registerDate;

	String registerTimeTxt;

	String registerTime;

	Integer timeslotId;

	Integer consultantTime;

	Integer bookingStatus;

	String appointmentDate;

	String appointmentTime;

	String appointmentTimeApproved;

	Integer appointmentDoctorId;

	String appointmentDoctorName;

	Date medicalexaminationDate;

	Integer approveUserId;

	Date approveDate;

	String reasonReject;

	Integer rejectUserId;

	Date rejectDate;

	Long totalMoney;

	Long discountRates;

	Integer discountType;

	Long discountMoney;

	Long voucherMoney;

	Integer exchangePoints;

	Long exchangeMoney;

	Integer pointsPlus;

	Long totalPayment;

	Integer historiesId;

	Integer paymentsFormId;

	String isDelete;

	String isActive;

	Integer createUserId;

	Date createDate;

	Integer updateUserId;

	Date updateDate;

	Integer patientHistoriesId;

	Integer startrecord;

	Integer pagesize;

	Boolean resultSqlEx;

	String doctorName;

	String positionCode;

	String academicRankId;

	String academicRankName;

	String degreeId;

	String degreeName;

	Integer healthFacilitiesCode;

	String healthFacilitiesName;

	String patientName;

	String patientPhoneNumber;

	String patientAddress;

	String patientWard;

	String patientDistrict;

	String patientProvince;
}
