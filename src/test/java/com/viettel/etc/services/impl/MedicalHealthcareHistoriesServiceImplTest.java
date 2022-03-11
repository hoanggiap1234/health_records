package com.viettel.etc.services.impl;

import com.viettel.etc.dto.*;
import com.viettel.etc.repositories.DrugsRepository;
import com.viettel.etc.repositories.MedicalHealthcareHistoriesRepository;
import com.viettel.etc.repositories.PriceRepository;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.utils.TelecareUserEntity;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import mockit.MockUp;

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
import java.util.List;
import java.util.Optional;

class MedicalHealthcareHistoriesServiceImplTest {
    @Mock
    private MedicalHealthcareHistoriesRepository medicalHealthcareHistoriesRepository;

    @Mock
    private DrugsRepository drugsRepository;

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    MedicalHealthcareHistoriesServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new MedicalHealthcareHistoriesServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getDetailHealthcareDrug() {
        MedicalHealthcareHistoriesDTO itemParamsEntity = new MedicalHealthcareHistoriesDTO();
        Optional<MedicalHealthcareHistoriesDTO> dataResult = Optional.of(new MedicalHealthcareHistoriesDTO());
        Mockito.when(medicalHealthcareHistoriesRepository.getDetailHealthcareDrug(itemParamsEntity)).thenReturn(dataResult);

        DrugsDTO dto = new DrugsDTO();
        List<DrugsDTO> drugsDTOList = new ArrayList<>();
        drugsDTOList.add(new DrugsDTO());
        Mockito.when(drugsRepository.getListDrug(dto)).thenReturn(drugsDTOList);

        //call service
        Optional<MedicalHealthcareHistoriesDTO> actualResult = service.getDetailHealthcareDrug(itemParamsEntity);

        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void getReExaminations() throws TeleCareException {
        MedicalHealthcareHistoriesDTO dto = new MedicalHealthcareHistoriesDTO();
        TelecareUserEntity userSystemEntity = new TelecareUserEntity();
        userSystemEntity.setTelecareUserId(1L);
        Authentication authentication = null;
        new MockUp<FnCommon>() {
            @mockit.Mock
            public TelecareUserEntity getTelecareUserInfo(Authentication authentication) {
                return userSystemEntity;
            }
        };
        Long patientBookingId = userSystemEntity.getTelecareUserId();
        ResultSelectEntity dataResult = new ResultSelectEntity();
        dataResult.setListData(Arrays.asList(new ReExaminationDTO()));
        dataResult.setCount(1);
        Mockito.when(medicalHealthcareHistoriesRepository.getReExaminations(dto, patientBookingId)).thenReturn(dataResult);
        DoctorDTO doctorPrice = new DoctorDTO();
        doctorPrice.setPhaseId(1);
        Mockito.when(priceRepository.getDoctorExamPrice(Mockito.any())).thenReturn(Arrays.asList(doctorPrice));
        ServiceDTO serviceDB = new ServiceDTO();
        serviceDB.setPhaseId(1);
        Mockito.when(priceRepository.getServicePrice(Mockito.any())).thenReturn(Arrays.asList(serviceDB));
        //call service
        ResultSelectEntity actualResult = service.getReExaminations(dto, authentication);

        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
        MatcherAssert.assertThat(actualResult.getCount(), Matchers.equalTo(1));
    }

}