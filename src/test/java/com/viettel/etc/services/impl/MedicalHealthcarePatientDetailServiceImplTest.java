package com.viettel.etc.services.impl;

import com.viettel.etc.dto.IndexType;
import com.viettel.etc.dto.MedicalHealthcarePatientDetailDTO;
import com.viettel.etc.dto.MedicalHealthcarePatientSummaryDTO;
import com.viettel.etc.repositories.MedicalHealthcarePatientDetailRepository;
import com.viettel.etc.repositories.tables.MedicalHealthcarePatientDetailRepositoryJPA;
import com.viettel.etc.repositories.tables.MedicalHealthcarePatientSummaryRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientDetailEntity;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientSummaryEntity;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.utils.TelecareUserEntity;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MedicalHealthcarePatientDetailServiceImplTest {
    @Mock
    MedicalHealthcarePatientSummaryRepositoryJPA medicalHealthcarePatientSummaryRepositoryJPA;
    @Mock
    private MedicalHealthcarePatientDetailRepository medicalHealthcarePatientDetailRepository;
    @Mock
    private MedicalHealthcarePatientDetailRepositoryJPA medicalHealthcarePatientDetailRepositoryJPA;

    @InjectMocks
    MedicalHealthcarePatientDetailServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new MedicalHealthcarePatientDetailServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void delete() throws TeleCareException {
        MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();
        dataParams.setIndexType("bloodPressure");
        dataParams.setDetailId(1);
        Authentication authentication = null;
        MedicalHealthcarePatientDetailEntity entity = new MedicalHealthcarePatientDetailEntity();
        entity.setDetailId(1);
        Mockito.when(medicalHealthcarePatientDetailRepositoryJPA.findById(1)).thenReturn(Optional.of(entity));

        //call service
        MedicalHealthcarePatientDetailEntity actualResult = service.delete(dataParams, authentication);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void delete_NotPresent() throws TeleCareException {
        MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();
        dataParams.setIndexType("test");
        dataParams.setDetailId(1);
        Authentication authentication = null;
        MedicalHealthcarePatientDetailEntity entity = new MedicalHealthcarePatientDetailEntity();
        entity.setDetailId(1);
        Mockito.when(medicalHealthcarePatientDetailRepositoryJPA.findById(1)).thenReturn(Optional.ofNullable(entity));

        //assert
        Assertions.assertThrows(TeleCareException.class, () ->{
            //call service
            service.delete(dataParams,authentication);
        });
    }

    @Test
    void createHealthIndex_InvalidIndexType() {
        MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();
        dataParams.setIndexType("test");
        Authentication authentication = null;
        //assert
        Assertions.assertThrows(TeleCareException.class, () ->{
            //call service
            service.createHealthIndex(dataParams,authentication);
        });
    }

    @Test
    void createHealthIndex() throws Exception {
        service = Mockito.spy(new MedicalHealthcarePatientDetailServiceImpl(){
            @Override
            public void createPatientSummary(MedicalHealthcarePatientSummaryDTO dto){

            }
        });
        MockitoAnnotations.initMocks(this);

        MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();
        dataParams.setIndexType("bloodSugar");
        dataParams.setBloodSugar(0.111);
        dataParams.setDetailId(1);
        Authentication authentication = null;
        MedicalHealthcarePatientDetailEntity entity = new MedicalHealthcarePatientDetailEntity();
        entity.setDetailId(1);
        new MockUp<FnCommon>(){
            @mockit.Mock
            public String getUserIdLogin(Authentication authentication) {
                return "123";
            }
        };
        //call service
        MedicalHealthcarePatientDetailEntity actualResult = service.createHealthIndex(dataParams, authentication);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void createPatientSummary() throws Exception {
        MedicalHealthcarePatientSummaryDTO dto = new MedicalHealthcarePatientSummaryDTO();
        dto.setPatientId(1);
        List<MedicalHealthcarePatientSummaryEntity> entities = new ArrayList<>();
        MedicalHealthcarePatientSummaryEntity entity = new MedicalHealthcarePatientSummaryEntity();
        entity.setSummaryId(1);
        entities.add(entity);
        Mockito.when(medicalHealthcarePatientSummaryRepositoryJPA.findByPatientId(dto.getPatientId())).thenReturn(entities);
        service.createPatientSummary(dto);
    }

    @Test
    void updateHealthIndex() throws Exception {
        MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();
        Authentication authentication = null;
        dataParams.setIndexType("bloodSugar");
        dataParams.setBloodSugar(0.111);
        dataParams.setDetailId(1);
        dataParams.setPatientId(1);
        dataParams.setResultDate(1234564511L);
        MedicalHealthcarePatientDetailEntity entity = new MedicalHealthcarePatientDetailEntity();
        entity.setDetailId(1);
        Mockito.when(medicalHealthcarePatientDetailRepositoryJPA.findById(1)).thenReturn(Optional.of(entity));
        //call service
        MedicalHealthcarePatientDetailEntity actualResult = service.updateHealthIndex(dataParams, authentication);
        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }
}