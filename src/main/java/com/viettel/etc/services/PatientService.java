package com.viettel.etc.services;

import com.viettel.etc.dto.PatientDTO;
import com.viettel.etc.utils.TeleCareException;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Wed Aug 12 17:57:15 ICT 2020
 */
public interface PatientService {
    

    public Object getPatient(PatientDTO itemParamsEntity, Authentication authentication) throws TeleCareException;

    public Object getPatientRelatives(PatientDTO itemParamsEntity, Authentication authentication, Integer patientId) throws TeleCareException;

    public Object getPatientRelativeInfo(PatientDTO itemParamsEntity, Authentication authentication) throws TeleCareException;

    public Object getPatients(PatientDTO itemParamsEntity, Authentication authentication) throws TeleCareException;

    public Object getPatientInfo(PatientDTO itemParamsEntity);
}