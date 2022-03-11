package com.viettel.etc.repositories.tables.entities;

import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "DOCTORS_HEALTHFACILITIES")
public class DoctorsHealthfacilitiesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Integer id;

    @Column(name = "DOCTOR_ID")
    Integer doctorId;

    @Column(name = "HEALTHFACILITIES_CODE")
    String healthfacilitiesCode;

    @Column(name = "CREATE_USER_ID")
    Integer createUserId;

    @Column(name = "UPDATE_USER_ID")
    Integer updateUserId;

    @Column(name = "IS_DELETE")
    Boolean isDelete = Constants.IS_NOT_DELETE_BOOLEAN;

    @Column(name = "IS_ACTIVE")
    Boolean isActive = Constants.IS_ACTIVE_BOOLEAN;

    @Column(name = "UPDATE_DATE")
    @UpdateTimestamp
    Date updateDate;

    @Column(name = "CREATE_DATE")
    @CreationTimestamp
    Date createDate;
}
