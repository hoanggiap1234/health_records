package com.viettel.etc.services.impl;

import com.viettel.etc.dto.PatientsIdentificationDTO;
import com.viettel.etc.repositories.PatientsIdentificationRepository;
import com.viettel.etc.utils.TeleCareException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PatientsIdentificationServiceImplTest {
    @Mock
    private PatientsIdentificationRepository patientsIdentificationRepository;

    @InjectMocks
    PatientsIdentificationServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new PatientsIdentificationServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void autofillHealthInsuranceInfo() throws TeleCareException {
        PatientsIdentificationDTO itemParamsEntity = new PatientsIdentificationDTO();
        itemParamsEntity.setHealthinsuranceNumber("DN4204132853370");
        Mockito.when(patientsIdentificationRepository.autofillHealthInsuranceInfo(itemParamsEntity)).thenReturn(Optional.ofNullable(new PatientsIdentificationDTO()));
        //call service
        Optional<PatientsIdentificationDTO> actualResult =  service.autofillHealthInsuranceInfo(itemParamsEntity);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void autofillHealthInsuranceInfo_InvalidParam() throws TeleCareException {
        PatientsIdentificationDTO itemParamsEntity = new PatientsIdentificationDTO();
        itemParamsEntity.setHealthinsuranceNumber("test123");
        Mockito.when(patientsIdentificationRepository.autofillHealthInsuranceInfo(itemParamsEntity)).thenReturn(Optional.ofNullable(new PatientsIdentificationDTO()));
        //assert
        Assertions.assertThrows(TeleCareException.class, ()->{
            //call service
            Object actualResult =  service.autofillHealthInsuranceInfo(itemParamsEntity);
        });
    }
}