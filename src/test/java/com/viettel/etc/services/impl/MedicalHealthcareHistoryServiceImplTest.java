package com.viettel.etc.services.impl;

import com.viettel.etc.dto.*;
import com.viettel.etc.repositories.DrugsRepository;
import com.viettel.etc.repositories.MedicalHealthcareHistoryRepository;
import com.viettel.etc.repositories.MedicalHealthcarePatientDetailRepository;
import com.viettel.etc.repositories.MedicalHealthcareServiceRepository;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class MedicalHealthcareHistoryServiceImplTest {
    @Mock
    private MedicalHealthcareHistoryRepository medicalHealthcareHistoryRepository;

    @Mock
    private MedicalHealthcarePatientDetailRepository medicalHealthcarePatientDetailRepository;

    @Mock
    private MedicalHealthcareServiceRepository medicalHealthcareServiceRepository;

    @Mock
    private DrugsRepository drugsRepository;

    @InjectMocks
    MedicalHealthcareHistoryServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new MedicalHealthcareHistoryServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getDetailHealthcareHistory() {
        MedicalHealthcareHistoryDTO dataParams = new MedicalHealthcareHistoryDTO();
        MedicalHealthcareHistoryDTO medicalHealthcareHistoryDTO = new MedicalHealthcareHistoryDTO();
        medicalHealthcareHistoryDTO.setPatientId(1);
        Optional<MedicalHealthcareHistoryDTO> resultDataOpt = Optional.ofNullable(medicalHealthcareHistoryDTO);
        Mockito.when(medicalHealthcareHistoryRepository.getDetailHealthcareHistory(dataParams)).thenReturn(resultDataOpt);

        MedicalHealthcareHistoryAttachmentDTO medicalHealthcareHistoryAttachmentDTO = new MedicalHealthcareHistoryAttachmentDTO();
        ResultSelectEntity resultSelectAttachment = new ResultSelectEntity();
        Mockito.when(medicalHealthcareHistoryRepository.getMedicalHealthcareHistoryAttachments(medicalHealthcareHistoryAttachmentDTO)).thenReturn(resultSelectAttachment);

        Optional<MedicalHealthcarePatientDetailDTO> medicalHealthcarePatientDetailDTOOpt = Optional.ofNullable(new MedicalHealthcarePatientDetailDTO());
        MedicalHealthcarePatientDetailDTO medicalHealthcarePatientDetailDTO = new MedicalHealthcarePatientDetailDTO();
        Mockito.when(medicalHealthcarePatientDetailRepository.getMedicalHealthcarePatientDetailHistory(medicalHealthcarePatientDetailDTO)).thenReturn(medicalHealthcarePatientDetailDTOOpt);

        MedicalHealthcareServiceDTO medicalHealthcareServiceDTO = new MedicalHealthcareServiceDTO();
        List<MedicalHealthcareServiceDTO> medicalHealthcareServices = new ArrayList<>();
        Mockito.when(medicalHealthcareServiceRepository.getListDetailHealthcareServiceByHistory(medicalHealthcareServiceDTO)).thenReturn(medicalHealthcareServices);

        DrugsDTO drugsDTO = new DrugsDTO();
        ResultSelectEntity dataResult = new ResultSelectEntity();
        dataResult.setListData(Arrays.asList(new DrugsDTO()));
        dataResult.setCount(1);
        Mockito.when(drugsRepository.getDrugs(drugsDTO)).thenReturn(dataResult);

        //call service
        Optional<MedicalHealthcareHistoryDTO> actualResult = service.getDetailHealthcareHistory(dataParams);

        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());

    }
}