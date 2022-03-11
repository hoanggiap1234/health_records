package com.viettel.etc.services.impl;

import com.viettel.etc.dto.PatientDTO;
import com.viettel.etc.repositories.PatientRepository;
import com.viettel.etc.repositories.tables.entities.CatsRelationshipsEntity;
import com.viettel.etc.repositories.tables.entities.DoctorsEntity;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientDetailEntity;
import com.viettel.etc.repositories.tables.entities.PatientsRelationshipsEntity;
import com.viettel.etc.services.tables.CatsRelationshipsServiceJPA;
import com.viettel.etc.services.tables.DoctorsServiceJPA;
import com.viettel.etc.services.tables.PatientsRelationshipsServiceJPA;
import com.viettel.etc.services.tables.PatientsServiceJPA;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import mockit.MockUp;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

class PatientServiceImplTest {
    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientsServiceJPA patientServiceJPA;

    @Mock
    private DoctorsServiceJPA doctorServiceJPA;

    @Mock
    private PatientsRelationshipsServiceJPA patientRelationshipServiceJPA;

    @Mock
    private CatsRelationshipsServiceJPA catsRelationshipsServiceJPA;

    @InjectMocks
    PatientServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new PatientServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getPatient() throws TeleCareException {
        PatientDTO itemParamsEntity = new PatientDTO();
        Authentication authentication = null;
        Mockito.when(patientServiceJPA.getUserIdFromToken(authentication)).thenReturn(1);
        Mockito.when(patientRepository.getPatient(itemParamsEntity)).thenReturn(new MedicalHealthcarePatientDetailEntity());
        //call service
        MedicalHealthcarePatientDetailEntity actualResult = (MedicalHealthcarePatientDetailEntity) service.getPatient(itemParamsEntity, authentication);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void getPatientRelatives() throws TeleCareException {
        PatientDTO itemParamsEntity = new PatientDTO();
        Integer patientId =1;
        Authentication authentication = null;
        Mockito.when(patientServiceJPA.getUserIdFromToken(authentication)).thenReturn(1);
        Mockito.when(patientServiceJPA.checkAuthentication(1, 1)).thenReturn(true);
        Mockito.when(patientRepository.getPatientRelatives(itemParamsEntity)).thenReturn(new ResultSelectEntity());
        //call service
        Object actualResult =  service.getPatientRelatives(itemParamsEntity, authentication, 1);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void getPatientRelatives_NotAuthenticated() throws TeleCareException {
        PatientDTO itemParamsEntity = new PatientDTO();
        Integer patientId =1;
        Authentication authentication = null;
        Mockito.when(patientServiceJPA.getUserIdFromToken(authentication)).thenReturn(1);
        Mockito.when(patientServiceJPA.checkAuthentication(1, 1)).thenReturn(false);
        //assert
        Assertions.assertThrows(TeleCareException.class, ()->{
            //call service
            Object actualResult =  service.getPatientRelatives(itemParamsEntity, authentication, 1);
        });
    }

    @Test
    void getPatientRelativeInfo() throws TeleCareException {
        PatientDTO itemParamsEntity = new PatientDTO();
        itemParamsEntity.setPatientId(1);
        Authentication authentication = null;
        Mockito.when(patientServiceJPA.getUserIdFromToken(authentication)).thenReturn(1);
        PatientsRelationshipsEntity patientsRelationshipsEntity = new PatientsRelationshipsEntity();
        patientsRelationshipsEntity.setRelationshipId(1);
        Mockito.when(patientRelationshipServiceJPA.findByPatientIdAndPatientParentId(
                itemParamsEntity.getPatientId(), 1)).thenReturn(patientsRelationshipsEntity);
        Mockito.when(catsRelationshipsServiceJPA.findById(
                patientsRelationshipsEntity.getRelationshipId())).thenReturn(java.util.Optional.of(new CatsRelationshipsEntity()));
        Mockito.when(patientRepository.getPatient(itemParamsEntity)).thenReturn(new PatientDTO());
        //call service
        Object actualResult =  service.getPatientRelativeInfo(itemParamsEntity, authentication);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void getPatientRelativeInfo_NullData() throws TeleCareException {
        PatientDTO itemParamsEntity = new PatientDTO();
        itemParamsEntity.setPatientId(1);
        Authentication authentication = null;
        Mockito.when(patientServiceJPA.getUserIdFromToken(authentication)).thenReturn(1);
        Mockito.when(patientRelationshipServiceJPA.findByPatientIdAndPatientParentId(
                itemParamsEntity.getPatientId(), 1)).thenReturn(null);
        //assert
        Assertions.assertThrows(TeleCareException.class, ()->{
            //call service
            Object actualResult =  service.getPatientRelativeInfo(itemParamsEntity, authentication);
        });
    }


    @Test
    void getPatients() throws TeleCareException {
        PatientDTO itemParamsEntity = new PatientDTO();
        itemParamsEntity.setDoctorId(1);
        Authentication authentication = null;
        new MockUp<FnCommon>(){
            @mockit.Mock
            public String getUserIdLogin(Authentication authentication) {
                return "123";
            }
        };
        Mockito.when(patientServiceJPA.existsByKeycloakUserId("1")).thenReturn(true);
        DoctorsEntity doctorsEntity = new DoctorsEntity();
        doctorsEntity.setDoctorId(1);
        Mockito.when(doctorServiceJPA.findByKeycloakUserId("123")).thenReturn(doctorsEntity);
        Mockito.when(patientRepository.getPatients(itemParamsEntity)).thenReturn(new ResultSelectEntity());

        //call service
        ResultSelectEntity actualResult = (ResultSelectEntity) service.getPatients(itemParamsEntity, authentication);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());

    }

}