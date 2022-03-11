package com.viettel.etc.repositories.tables.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.Objects;

/**
 * Autogen class Entity: Create Entity For Table Name Medical_healthcare_vaccinations
 * 
 * @author ToolGen
 * @date Thu Feb 04 09:45:44 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "MEDICAL_HEALTHCARE_VACCINATIONS")
public class MedicalHealthcareVaccinationsEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VACCINATION_ID")
    Integer vaccinationId;

    @Column(name = "PATIENT_ID")
    Integer patientId;

    @Column(name = "VACCINE_NAME")
    String vaccineName;

    @Column(name = "HEALTHFACILITIES_CODE")
    String healthfacilitiesCode;

    @Column(name = "ANTIGEN")
    String antigen;

    @Column(name = "PHONE_NUMBER")
    String phoneNumber;

    @Column(name = "INJECTION_DATE")
    Date injectionDate;

    @Column(name = "INJECTIONS_NUMBER")
    Integer injectionsNumber;

    @Column(name = "VACCINATION_FACILITY")
    String vaccinationFacility;

    @Column(name = "REACTION_LEVEL")
    String reactionLevel;

    @Column(name = "REACTION_TIME")
    String reactionTime;

    @Column(name = "RESULT")
    String result;

    @Column(name = "FULLNAME")
    String fullname;

    @Column(name = "IS_DELETE")
    Integer isDelete = Constants.IS_NOT_DELETE;

    @Column(name = "IS_ACTIVE")
    Integer isActive = Constants.IS_ACTIVE;

    @Column(name = "IS_SYNC")
    Integer isSync = Constants.IS_NOT_SYNC;

    @Column(name = "CREATE_USER_ID")
    Integer createUserId;

    @CreationTimestamp
    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "UPDATE_USER_ID")
    Integer updateUserId;

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE")
    Date updateDate;

    @Column(name = "BATCH_NUMBER")
    String batchNumber;

//    @PrePersist
//    protected void onCreate() {
//        this.createDate = new Date();
//        this.isSync = Constants.IS_NOT_SYNC;
//        this.isActive = Constants.IS_ACTIVE;
//        this.isDelete = Constants.IS_NOT_DELETE;
//    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalHealthcareVaccinationsEntity that = (MedicalHealthcareVaccinationsEntity) o;
        return vaccineName.equals(that.vaccineName) &&
                healthfacilitiesCode.equals(that.healthfacilitiesCode) &&
                injectionDate.getTime()==that.injectionDate.getTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(vaccineName, healthfacilitiesCode, injectionDate);
    }
}