package com.viettel.etc.repositories.tables.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "MEDICAL_HEALTHCARE_HISTORIES_ATTACHMENTS")
public class MedicalHealthcareHistoriesAttachmentsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ATTACHMENT_ID")
	Integer attachmentId;

	@Column(name = "HISTORIES_ID")
	Integer historiesId;

	@Column(name = "GROUP_TYPE")
	Integer groupType;

	@Column(name = "DRUG_ID")
	Integer drugId;

	@Column(name = "FILE_NAME")
	String fileName;

	@Column(name = "URL")
	String url;

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

	@Column(name = "CREATE_DATE")
	@CreationTimestamp
	Date createDate = new Date();

	@Column(name = "UPDATE_DATE")
	@UpdateTimestamp
	Date updateDate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SERVICE_ID")
	@JsonBackReference
	private MedicalHealthcareServicesEntity sercive;

	@PrePersist
	public void createDefaultProperti() {
		if (this.isActive == null) {
			this.isActive = Constants.IS_ACTIVE;
		}
		if (this.isDelete == null) {
			this.isDelete = Constants.IS_NOT_DELETE;
		}
		if (this.isSync == null) {
			this.isSync = Constants.IS_SYNC;
		}
	}
}
