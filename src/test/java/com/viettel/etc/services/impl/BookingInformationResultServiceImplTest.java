package com.viettel.etc.services.impl;

import com.viettel.etc.dto.BookingInformationResultDTO;
import com.viettel.etc.dto.MedicalHealthcareNoteDTO;
import com.viettel.etc.repositories.BookingInformationResultRepository;
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

class BookingInformationResultServiceImplTest {
    @Mock
    private BookingInformationResultRepository bookingInformationResultRepository;

    @Mock
    private PatientsServiceJPA patientsServiceJPA;

    @InjectMocks
    BookingInformationResultServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BookingInformationResultServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getBookingInformationResult() throws TeleCareException {
        BookingInformationResultDTO itemParamsEntity = new BookingInformationResultDTO();
        Authentication authentication = null;
        itemParamsEntity.setPatientId(1);
        Mockito.when(patientsServiceJPA.getUserIdFromToken(Mockito.any())).thenReturn(1);
        Mockito.when(patientsServiceJPA.checkRelationship(itemParamsEntity.getPatientId(), 1)).thenReturn(true);
        Mockito.when(bookingInformationResultRepository.getBookingInformationResult(itemParamsEntity)).thenReturn(new ResultSelectEntity());

        //call service
        ResultSelectEntity actualResult = (ResultSelectEntity) service.getBookingInformationResult(itemParamsEntity, authentication);

        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void getBookingInformationResult_HasNoRelationship() throws TeleCareException {
        BookingInformationResultDTO itemParamsEntity = new BookingInformationResultDTO();
        Authentication authentication = null;
        itemParamsEntity.setPatientId(1);
        Mockito.when(patientsServiceJPA.getUserIdFromToken(Mockito.any())).thenReturn(1);
        Mockito.when(patientsServiceJPA.checkRelationship(itemParamsEntity.getPatientId(), 1)).thenReturn(false);

        //assert
        Assertions.assertThrows(TeleCareException.class, ()->{
            //call service
            service.getBookingInformationResult(itemParamsEntity, authentication);
        });
    }
}