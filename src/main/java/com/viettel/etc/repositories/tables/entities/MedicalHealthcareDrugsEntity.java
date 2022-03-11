package com.viettel.etc.repositories.tables.entities;

import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * Autogen class Entity: Create Entity For Table Name Medical_healthcare_drugs
 *
 * @author ToolGen
 * @date Mon Sep 14 08:50:55 ICT 2020
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "MEDICAL_HEALTHCARE_DRUGS")
public class MedicalHealthcareDrugsEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DRUG_ID")
	Integer drugId;

	@Column(name = "HISTORIES_ID")
	Integer historiesId;

	@Column(name = "HEALTHFACILITIES_CODE")
	String healthfacilitiesCode;

	@Column(name = "PATIENT_ID")
	Integer patientId;

	@Column(name = "DRUG_NAME")
	String drugName;

	@Column(name = "DRUG_CODE")
	String drugCode;

	@Column(name = "UNIT")
	String unit;

	@Column(name = "METHOD_CODE")
	String methodCode;

	@Column(name = "QUANTITY")
	Integer quantity;

	@Column(name = "MORNING_AMOUNT")
	Integer morningAmount;

	@Column(name = "NOON_AMOUNT")
	Integer noonAmount;

	@Column(name = "AFTERNOON_AMOUNT")
	Integer afternoonAmount;

	@Column(name = "EVENING_AMOUNT")
	Integer eveningAmount;

	@Column(name = "DOSAGE_DESCRIPTION")
	String dosageDescription;

	@Column(name = "DECISION_DATE")
	LocalDateTime decisionDate;

	@Column(name = "NOTES")
	String notes;

	@Column(name = "IS_DELETE")
	Integer isDelete;

	@Column(name = "IS_ACTIVE")
	Integer isActive;

	@Column(name = "IS_SYNC")
	Integer isSync;

	@Column(name = "CREATE_USER_ID")
	Integer createUserId;

	@Column(name = "UPDATE_USER_ID")
	Integer updateUserId;

	@Column(name = "PHONE_NUMBER")
	String phoneNumber;
	@Column(name = "GROUP_ID")
	Integer groupId;

	@Column(name = "CREATE_DATE")
	@CreationTimestamp
	Date createDate = new Date();

	@Column(name = "UPDATE_DATE")
	@UpdateTimestamp
	Date updateDate;

	@PrePersist
	public void createDefaultProperti() {
		if (this.isActive == null) {
			this.isActive = Constants.IS_ACTIVE;
		}
		if (this.isDelete == null) {
			this.isDelete = Constants.IS_NOT_DELETE;
		}
		if (this.isSync == null) {
			this.isSync = Constants.IS_NOT_SYNC;
		}
		if (this.createDate == null) {
			this.createDate = new Date();
		}
	}

//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		MedicalHealthcareDrugsEntity that = (MedicalHealthcareDrugsEntity) o;
//		return historiesId.equals(that.historiesId) &&
//				drugCode.equals(that.drugCode);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(historiesId, drugCode);
//	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MedicalHealthcareDrugsEntity that = (MedicalHealthcareDrugsEntity) o;
		return historiesId.equals(that.historiesId) && drugName.equals(that.drugName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(historiesId, drugName);
	}
}