package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Patients_healthinsurances
 * 
 * @author ToolGen
 * @date Tue Aug 18 10:12:25 ICT 2020
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "PATIENTS_HEALTHINSURANCES")
public class PatientsHealthinsurancesEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HEALTHINSURANCE_ID")
    Integer healthinsuranceId;

    @Column(name = "PATIENT_ID")
    Integer patientId;

    @Column(name = "HEALTHINSURANCE_NUMBER")
    String healthinsuranceNumber;

    @Column(name = "HEALTHFACILITIES_CODE")
    String healthfacilitiesCode;

    @Column(name = "FROM_DATE")
    Date fromDate;

    @Column(name = "TO_DATE")
    Date toDate;

    @Column(name = "AREA_CODE")
    String areaCode;

    @Column(name = "FIVE_YEARS_DATE")
    Date fiveYearsDate;

    @Column(name = "IS_DELETE", insertable = false)
    Integer isDelete;

    @Column(name = "IS_ACTIVE", insertable = false)
    Integer isActive;

    @Column(name = "CREATE_USER_ID")
    Integer createUserId;

    @Column(name = "CREATE_DATE", insertable = false)
    Date createDate;

    @Column(name = "UPDATE_USER_ID")
    Integer updateUserId;

    @Column(name = "UPDATE_DATE")
    Date updateDate;

    @Column(name = "IS_CHECK", insertable = false)
    Integer is_check;
}