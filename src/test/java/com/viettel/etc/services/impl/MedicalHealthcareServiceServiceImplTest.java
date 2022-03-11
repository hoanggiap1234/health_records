package com.viettel.etc.services.impl;

import com.viettel.etc.dto.MedicalHealthcareServiceDTO;
import com.viettel.etc.repositories.MedicalHealthcareServiceRepository;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MedicalHealthcareServiceServiceImplTest {
    @Mock
    private MedicalHealthcareServiceRepository medicalHealthcareServiceRepository;

    @InjectMocks
    MedicalHealthcareServiceServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new MedicalHealthcareServiceServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getHealthcareService() throws TeleCareException {
        MedicalHealthcareServiceDTO itemParamsEntity = new MedicalHealthcareServiceDTO();
        Authentication authentication = null;
        Mockito.when(medicalHealthcareServiceRepository.getHealthcareService(itemParamsEntity)).thenReturn(new ResultSelectEntity());
        //call service
        ResultSelectEntity actualResult = (ResultSelectEntity) service.getHealthcareService(itemParamsEntity, authentication);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());

    }

    @Test
    void getDetailHealthcareService() {
        MedicalHealthcareServiceDTO dataParams = new MedicalHealthcareServiceDTO();
        Mockito.when(medicalHealthcareServiceRepository.getDetailHealthcareService(dataParams)).thenReturn(Optional.ofNullable(new MedicalHealthcareServiceDTO()));
        Mockito.when(medicalHealthcareServiceRepository.getMedicalHealthcareServicesResults(dataParams)).thenReturn(Arrays.asList(new MedicalHealthcareServiceDTO()));
        Mockito.when(medicalHealthcareServiceRepository.getMedicalHealthcareHistoriesAttachments(dataParams)).thenReturn(Arrays.asList(new MedicalHealthcareServiceDTO()));
        //call service
        Optional<MedicalHealthcareServiceDTO> actualResult =  service.getDetailHealthcareService(dataParams);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void getDetailHealthcareServiceHistory() {
        MedicalHealthcareServiceDTO dataParams = new MedicalHealthcareServiceDTO();
        Mockito.when(medicalHealthcareServiceRepository.getDetailHealthcareServiceHistory(dataParams)).thenReturn(Optional.ofNullable(new MedicalHealthcareServiceDTO()));
        //call service
        Optional<MedicalHealthcareServiceDTO> actualResult =  service.getDetailHealthcareServiceHistory(dataParams);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }
}