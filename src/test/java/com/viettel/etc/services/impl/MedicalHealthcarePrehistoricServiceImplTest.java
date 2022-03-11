package com.viettel.etc.services.impl;

import com.viettel.etc.dto.MedicalHealthcareAllergyDTO;
import com.viettel.etc.dto.MedicalHealthcarePrehistoricDTO;
import com.viettel.etc.repositories.MedicalHealthcareAllergyRepository;
import com.viettel.etc.repositories.MedicalHealthcarePrehistoricRepository;
import com.viettel.etc.repositories.MedicalHealthcarePresurgeryRepository;
import com.viettel.etc.repositories.tables.entities.PatientsEntity;
import com.viettel.etc.services.tables.PatientsServiceJPA;
import com.viettel.etc.utils.TeleCareException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MedicalHealthcarePrehistoricServiceImplTest {
    @Mock
    private MedicalHealthcarePrehistoricRepository medicalHealthcarePrehistoricRepository;

    @Mock
    private MedicalHealthcareAllergyRepository medicalHealthcareAllergyRepository;

    @Mock
    private MedicalHealthcarePresurgeryRepository medicalHealthcarePresurgeryRepository;

    @Mock
    private PatientsServiceJPA patientsServiceJPA;

    @InjectMocks
    MedicalHealthcarePrehistoricServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new MedicalHealthcarePrehistoricServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getPatientPrehistoric() throws TeleCareException {
        MedicalHealthcarePrehistoricDTO itemParamsEntity = new MedicalHealthcarePrehistoricDTO();
        Authentication authentication = null;
        Integer patientId = 1;
        Mockito.when(patientsServiceJPA.findById(itemParamsEntity.getPatientId())).thenReturn(Optional.ofNullable(new PatientsEntity()));
        Mockito.when(medicalHealthcareAllergyRepository.getMedicalHealthcareAllergy(new MedicalHealthcareAllergyDTO())).thenReturn(Arrays.asList(new MedicalHealthcareAllergyDTO()));

        //call service
        Object actualResult = service.getPatientPrehistoric(itemParamsEntity, authentication, patientId);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }
}