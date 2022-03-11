package com.viettel.etc.services.impl;

import com.viettel.etc.dto.MedicalHealthcareRegimenDTO;
import com.viettel.etc.dto.MedicalHealthcareRegimenDetailDTO;
import com.viettel.etc.repositories.MedicalHealthcareRegimenRepository;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientDetailEntity;
import com.viettel.etc.services.MedicalHealthcareRegimenDetailService;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.utils.TelecareUserEntity;
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
import org.springframework.security.core.Authentication;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MedicalHealthcareRegimenServiceImplTest {
    @Mock
    private MedicalHealthcareRegimenRepository medicalHealthcareRegimenRepository;
    @Mock
    private MedicalHealthcareRegimenDetailService medicalHealthcareRegimenDetailService;

    @InjectMocks
    MedicalHealthcareRegimenServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new MedicalHealthcareRegimenServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getHealthcareRegimens() throws TeleCareException {
        MedicalHealthcareRegimenDTO itemParamsEntity = new MedicalHealthcareRegimenDTO();
        Authentication authentication = null;
        TelecareUserEntity userEntity = new TelecareUserEntity();
        new MockUp<FnCommon>(){
            @mockit.Mock
            public TelecareUserEntity getTelecareUserInfo(Authentication authentication) {
                return userEntity;
            }
        };
        ResultSelectEntity dataResult = new ResultSelectEntity();
        dataResult.setListData(Arrays.asList(new MedicalHealthcareRegimenDTO()));
        dataResult.setCount(1);
        Mockito.when(medicalHealthcareRegimenRepository.getHealthcareRegimens(itemParamsEntity, userEntity)).thenReturn(dataResult);

        //call service
        ResultSelectEntity actualResult = (ResultSelectEntity) service.getHealthcareRegimens(itemParamsEntity, authentication);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
        MatcherAssert.assertThat(actualResult.getCount(), Matchers.equalTo(1));
    }

    @Test
    void getDetailHealthcareRegimens() throws TeleCareException {
        MedicalHealthcareRegimenDTO itemParamsEntity = new MedicalHealthcareRegimenDTO();
        Mockito.when(medicalHealthcareRegimenRepository.getDetailHealthcareRegimens(itemParamsEntity)).thenReturn(new MedicalHealthcareRegimenDTO());
        Mockito.when(medicalHealthcareRegimenDetailService.getMedicalHealthcareRegimenDetail(itemParamsEntity.getPatientRegimenId())).thenReturn(Arrays.asList(new MedicalHealthcareRegimenDetailDTO()));

        //call service
        MedicalHealthcareRegimenDTO actualResult = service.getDetailHealthcareRegimens(itemParamsEntity);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void getDetailHealthcareRegimens_NullData() throws TeleCareException {
        MedicalHealthcareRegimenDTO itemParamsEntity = new MedicalHealthcareRegimenDTO();
        MedicalHealthcareRegimenDTO result = new MedicalHealthcareRegimenDTO();
        Mockito.when(medicalHealthcareRegimenRepository.getDetailHealthcareRegimens(itemParamsEntity)).thenReturn(null);

        Assertions.assertThrows(TeleCareException.class, ()->{
            //call service
            MedicalHealthcareRegimenDTO actualResult = service.getDetailHealthcareRegimens(itemParamsEntity);
        });
    }

    @Test
    void getDetailHealthcareRegimensAfterUpdate() throws TeleCareException {
        MedicalHealthcareRegimenDTO itemParamsEntity = new MedicalHealthcareRegimenDTO();
        MedicalHealthcareRegimenDTO result = new MedicalHealthcareRegimenDTO();
        result.setPatientRegimenId(1);
        Mockito.when(medicalHealthcareRegimenRepository.getDetailHealthcareRegimensAfterUpdate(itemParamsEntity)).thenReturn(result);
        Mockito.when(medicalHealthcareRegimenDetailService.getMedicalHealthcareRegimenDetail(itemParamsEntity.getPatientRegimenId())).thenReturn(Arrays.asList(new MedicalHealthcareRegimenDetailDTO()));

        //call service
        MedicalHealthcareRegimenDTO actualResult = service.getDetailHealthcareRegimensAfterUpdate(itemParamsEntity);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void getDetailHealthcareRegimensAfterUpdate_DataNull() throws TeleCareException {
        MedicalHealthcareRegimenDTO itemParamsEntity = new MedicalHealthcareRegimenDTO();
        Mockito.when(medicalHealthcareRegimenRepository.getDetailHealthcareRegimens(itemParamsEntity)).thenReturn(null);

        Assertions.assertThrows(TeleCareException.class, ()->{
            //call service
            MedicalHealthcareRegimenDTO actualResult = service.getDetailHealthcareRegimensAfterUpdate(itemParamsEntity);
        });
    }
}