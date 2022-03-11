package com.viettel.etc.services.impl;

import com.viettel.etc.dto.HealthcareOverviewDto;
import com.viettel.etc.dto.MedicalHealthcarePatientSummaryDTO;
import com.viettel.etc.repositories.MedicalHealthcarePatientSummaryRepository;
import com.viettel.etc.repositories.tables.MedicalHealthcarePatientDetailRepositoryJPA;
import com.viettel.etc.repositories.tables.MedicalHealthcarePatientSummaryRepositoryJPA;
import com.viettel.etc.repositories.tables.PatientsRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientDetailEntity;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientSummaryEntity;
import com.viettel.etc.repositories.tables.entities.PatientsEntity;
import com.viettel.etc.utils.TeleCareException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

class MedicalHealthcarePatientSummaryServiceImplTest {
    @Mock
    PatientsRepositoryJPA patientsRepositoryJPA;
    @Mock
    MedicalHealthcarePatientSummaryRepositoryJPA medicalHealthcarePatientSummaryRepositoryJPA;
    @Mock
    MedicalHealthcarePatientDetailRepositoryJPA medicalHealthcarePatientDetailRepositoryJPA;
    @Mock
    private MedicalHealthcarePatientSummaryRepository medicalHealthcarePatientSummaryRepository;

    @InjectMocks
    MedicalHealthcarePatientSummaryServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new MedicalHealthcarePatientSummaryServiceImpl();
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void getPatientOverview() throws TeleCareException {
        MedicalHealthcarePatientSummaryDTO itemParamsEntity = new MedicalHealthcarePatientSummaryDTO();
        Integer patientId = 1;
        Mockito.when(medicalHealthcarePatientSummaryRepository.getPatientOverview(itemParamsEntity)).thenReturn(new MedicalHealthcarePatientDetailEntity());
        //call service
        MedicalHealthcarePatientDetailEntity actualResult = (MedicalHealthcarePatientDetailEntity) service.getPatientOverview(itemParamsEntity, patientId);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void createPatientSummary() throws Exception {
        service = Mockito.spy(new MedicalHealthcarePatientSummaryServiceImpl(){
            @Override
            public void insertToTableDetail(MedicalHealthcarePatientSummaryDTO dto){

            }
        });
        MockitoAnnotations.initMocks(this);
        MedicalHealthcarePatientSummaryDTO dto = new MedicalHealthcarePatientSummaryDTO();
        dto.setPatientId(1);
        Authentication authentication = null;

        PatientsEntity patient = new PatientsEntity();
        patient.setPatientId(1);
        Mockito.when(patientsRepositoryJPA.findById(dto.getPatientId())).thenReturn(java.util.Optional.of(patient));
        List<MedicalHealthcarePatientSummaryEntity> entities = new ArrayList<>();
        Mockito.when(medicalHealthcarePatientSummaryRepositoryJPA.findByPatientId(dto.getPatientId())).thenReturn(entities);

        //call service
        HealthcareOverviewDto actualResult = (HealthcareOverviewDto) service.createPatientSummary(dto, authentication);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void insertToTableDetail() throws TeleCareException {
        MedicalHealthcarePatientSummaryDTO dto = new MedicalHealthcarePatientSummaryDTO();
        service.insertToTableDetail(dto);
    }

    @Test
    void createPatientSummaryRest() throws Exception {
        MedicalHealthcarePatientSummaryDTO dto = new MedicalHealthcarePatientSummaryDTO();
        dto.setPatientId(1);
        dto.setWeight(1.1);
        dto.setHeight(1.1);
        dto.setPatientId(1);
        dto.setResultDate(new Date(1L));
        Authentication authentication =null;
        Mockito.when(medicalHealthcarePatientSummaryRepositoryJPA.findByPatientId(dto.getPatientId())).thenReturn(Arrays.asList(new MedicalHealthcarePatientSummaryEntity()));

        //call service
        HealthcareOverviewDto actualResult = (HealthcareOverviewDto) service.createPatientSummaryRest(dto, authentication);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }
}