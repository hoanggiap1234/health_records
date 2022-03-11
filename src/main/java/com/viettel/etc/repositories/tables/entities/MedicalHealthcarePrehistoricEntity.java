package com.viettel.etc.repositories.tables.entities;

import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Autogen class Entity: Create Entity For Table Name Medical_healthcare_prehistoric
 *
 * @author ToolGen
 * @date Thu Sep 17 17:27:43 ICT 2020
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "MEDICAL_HEALTHCARE_PREHISTORIC")
public class MedicalHealthcarePrehistoricEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PREHISTORIC_ID")
	Integer prehistoricId;

	@Column(name = "HISTORIES_ID")
	Integer historiesId;

	@Column(name = "PATIENT_ID")
	Integer patientId;

	@Column(name = "RELATIONSHIP_ID")
	Integer relationshipId;

	@Column(name = "HEALTH_HISTORY")
	String healthHistory;

	@Column(name = "PHONE_NUMBER")
	String phoneNumber;

	@Column(name = "IS_SYNC")
	Integer isSync=Constants.IS_NOT_SYNC;

	@Column(name = "IS_DELETE")
	Integer isDelete=Constants.IS_NOT_DELETE;

	@Column(name = "IS_ACTIVE")
	Integer isActive=Constants.IS_ACTIVE;

	@Column(name = "CREATE_USER_ID")
	Integer createUserId;

	@CreatedDate
	@Column(name = "CREATE_DATE")
	Date createDate;

	@Column(name = "UPDATE_USER_ID")
	Integer updateUserId;

	@LastModifiedDate
	@Column(name = "UPDATE_DATE")
	Date updateDate;

//	@PrePersist
//	protected void onCreate() {
//		this.createDate = new Date();
//		this.isSync = Constants.IS_NOT_SYNC;
//		this.isActive = Constants.IS_ACTIVE;
//		this.isDelete = Constants.IS_NOT_DELETE;
//	}

	@PreUpdate
	protected void onUpdate() {
		this.updateDate = new Date();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MedicalHealthcarePrehistoricEntity that = (MedicalHealthcarePrehistoricEntity) o;
		return relationshipId.equals(that.relationshipId) &&
				healthHistory.equals(that.healthHistory);
	}

	@Override
	public int hashCode() {
		return Objects.hash(relationshipId, healthHistory);
	}
}
