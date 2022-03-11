package com.viettel.etc.repositories;

import com.viettel.etc.dto.CovidPatientResultDTO;
import com.viettel.etc.dto.PatientDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

import java.util.List;


/**
 * Autogen class Repository Interface: 
 * 
 * @author toolGen
 * @date Wed Aug 12 17:57:15 ICT 2020
 */
public interface PatientRepository {

    public Object getPatient(PatientDTO itemParamsEntity);

    public ResultSelectEntity getPatientRelatives(PatientDTO itemParamsEntity);

    public ResultSelectEntity getPatients(PatientDTO itemParamsEntity);

    public List<PatientDTO> exportPatientData(PatientDTO itemParamsEntity);

    Object getPatientByParams(CovidPatientResultDTO params);

}