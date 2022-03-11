package com.viettel.etc.services.impl;

import com.viettel.etc.dto.InsuranceDTO;
import com.viettel.etc.repositories.InsuranceRepository;
import com.viettel.etc.services.tables.PatientsServiceJPA;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

class InsuranceServiceImplTest {
    @Mock
    private InsuranceRepository insuranceRepository;

    @Mock
    private PatientsServiceJPA patientServiceJPA;

    @InjectMocks
    InsuranceServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new InsuranceServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getInsuranceInfo() throws TeleCareException {
        Integer patientId = 1;
        InsuranceDTO itemParamsEntity = new InsuranceDTO();
        itemParamsEntity.setPatientId(1);
        Authentication authentication = null;
        Mockito.when(patientServiceJPA.getUserIdFromToken(authentication)).thenReturn(1);
        Mockito.when(patientServiceJPA.checkAuthentication(patientId, 1)).thenReturn(true);
        Mockito.when(insuranceRepository.getInsuranceInfo(itemParamsEntity)).thenReturn(new Object());

        //call service
        Object actualResult = service.getInsuranceInfo(patientId, itemParamsEntity, authentication);

        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void getInsuranceInfo_NotAuthenticated() throws TeleCareException {
        Integer patientId = 1;
        InsuranceDTO itemParamsEntity = new InsuranceDTO();
        itemParamsEntity.setPatientId(1);
        Authentication authentication = null;
        Mockito.when(patientServiceJPA.getUserIdFromToken(authentication)).thenReturn(1);
        Mockito.when(patientServiceJPA.checkAuthentication(patientId, 1)).thenReturn(false);

        //assert
        Assertions.assertThrows(TeleCareException.class, ()->{
            //call service
            Object actualResult = service.getInsuranceInfo(patientId, itemParamsEntity, authentication);
        });
    }

    @Test
    void getInsuranceInfo_DataNull() throws TeleCareException {
        Integer patientId = 1;
        InsuranceDTO itemParamsEntity = new InsuranceDTO();
        itemParamsEntity.setPatientId(1);
        Authentication authentication = null;
        Mockito.when(patientServiceJPA.getUserIdFromToken(authentication)).thenReturn(1);
        Mockito.when(patientServiceJPA.checkAuthentication(patientId, 1)).thenReturn(true);
        Mockito.when(insuranceRepository.getInsuranceInfo(itemParamsEntity)).thenReturn(null);

        //assert
        Assertions.assertThrows(TeleCareException.class, ()->{
            //call service
            Object actualResult = service.getInsuranceInfo(patientId, itemParamsEntity, authentication);
        });
    }
}