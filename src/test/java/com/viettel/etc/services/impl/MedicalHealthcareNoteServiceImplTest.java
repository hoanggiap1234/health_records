package com.viettel.etc.services.impl;

import com.viettel.etc.dto.DrugsDTO;
import com.viettel.etc.dto.MedicalHealthcareNoteDTO;
import com.viettel.etc.repositories.MedicalHealthcareNoteRepository;
import com.viettel.etc.repositories.tables.BookingInformationsRepositoryJPA;
import com.viettel.etc.repositories.tables.MedicalHealthcareNotesRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcareNotesEntity;
import com.viettel.etc.services.tables.PatientsServiceJPA;
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

class MedicalHealthcareNoteServiceImplTest {
    @Mock
    MedicalHealthcareNotesRepositoryJPA medicalHealthcareNotesRepositoryJPA;
    @Mock
    private MedicalHealthcareNoteRepository medicalHealthcareNoteRepository;
    @Mock
    private PatientsServiceJPA patientsServiceJPA;
    @Mock
    private BookingInformationsRepositoryJPA bookingInformationRepositoryJPA;

    @InjectMocks
    MedicalHealthcareNoteServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new MedicalHealthcareNoteServiceImpl();
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void getMedicalHealthcareNote_IsDoctor() {
        MedicalHealthcareNoteDTO itemParamsEntity = new MedicalHealthcareNoteDTO();
        itemParamsEntity.setPatientId(1);
        TelecareUserEntity userSystemEntity = new TelecareUserEntity(){
            @Override
            public boolean isDoctor() throws TeleCareException {
                return true;
            }
        };
        userSystemEntity.setTelecareUserId(1L);
        Authentication authentication = null;

        new MockUp<FnCommon>(){
            @mockit.Mock
            public  TelecareUserEntity getTelecareUserInfo(Authentication authentication) {
                return userSystemEntity;
            }
        };

        Mockito.when(bookingInformationRepositoryJPA
                .existsByDoctorIdAndPatientIdAndIsActiveAndIsDelete(1, 1, 1, 0 ))
                .thenReturn(false);
        //assert
        Assertions.assertThrows(TeleCareException.class, () ->{
            //call service
            service.getMedicalHealthcareNote(itemParamsEntity,authentication);
        });
    }

    @Test
    void getMedicalHealthcareNote_IsPatient() throws TeleCareException  {
        MedicalHealthcareNoteDTO itemParamsEntity = new MedicalHealthcareNoteDTO();
        itemParamsEntity.setPatientId(1);
        TelecareUserEntity userSystemEntity = new TelecareUserEntity(){
            @Override
            public boolean isPatient() throws TeleCareException {
                return true;
            }
            @Override
            public boolean isDoctor() throws TeleCareException {
                return false;
            }
        };
        userSystemEntity.setTelecareUserId(1L);
        Authentication authentication = null;

        new MockUp<FnCommon>(){
            @mockit.Mock
            public  TelecareUserEntity getTelecareUserInfo(Authentication authentication) {
                return userSystemEntity;
            }
        };

        Mockito.when(patientsServiceJPA.checkRelationship(itemParamsEntity.getPatientId(), 1)).thenReturn(true);

        ResultSelectEntity dataResult = new ResultSelectEntity();
        dataResult.setListData(Arrays.asList(new DrugsDTO()));
        dataResult.setCount(1);
        Mockito.when(medicalHealthcareNoteRepository.getMedicalHealthcareNote(itemParamsEntity)).thenReturn(dataResult);

        //call service
        ResultSelectEntity actualResult = (ResultSelectEntity) service.getMedicalHealthcareNote(itemParamsEntity, authentication);

        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void getMedicalHealthcareNote_IsPatient_HasNoRelationship() throws TeleCareException  {
        MedicalHealthcareNoteDTO itemParamsEntity = new MedicalHealthcareNoteDTO();
        itemParamsEntity.setPatientId(1);
        TelecareUserEntity userSystemEntity = new TelecareUserEntity(){
            @Override
            public boolean isPatient() throws TeleCareException {
                return true;
            }
            @Override
            public boolean isDoctor() throws TeleCareException {
                return false;
            }
        };
        userSystemEntity.setTelecareUserId(1L);
        Authentication authentication = null;

        new MockUp<FnCommon>(){
            @mockit.Mock
            public  TelecareUserEntity getTelecareUserInfo(Authentication authentication) {
                return userSystemEntity;
            }
        };

        Mockito.when(patientsServiceJPA.checkRelationship(itemParamsEntity.getPatientId(), 1)).thenReturn(false);

        //assert
        Assertions.assertThrows(TeleCareException.class, () ->{
            //call service
            service.getMedicalHealthcareNote(itemParamsEntity,authentication);
        });
    }

    @Test
    void getMedicalHealthcareNoteDetail() throws TeleCareException {
        MedicalHealthcareNoteDTO itemParamsEntity = new MedicalHealthcareNoteDTO();
        Authentication authentication = null;
        itemParamsEntity.setPatientId(1);
        Mockito.when(patientsServiceJPA.getUserIdFromToken(Mockito.any())).thenReturn(1);
        Mockito.when(patientsServiceJPA.checkRelationship(itemParamsEntity.getPatientId(), 1)).thenReturn(true);
        Mockito.when(medicalHealthcareNoteRepository.getMedicalHealthcareNoteDetail(itemParamsEntity)).thenReturn(new Object());

        //call service
        Object actualResult =  service.getMedicalHealthcareNoteDetail(itemParamsEntity, authentication);

        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void getMedicalHealthcareNoteDetail_HasNoRelationship() throws TeleCareException {
        MedicalHealthcareNoteDTO itemParamsEntity = new MedicalHealthcareNoteDTO();
        Authentication authentication = null;
        itemParamsEntity.setPatientId(1);
        Mockito.when(patientsServiceJPA.getUserIdFromToken(Mockito.any())).thenReturn(1);
        Mockito.when(patientsServiceJPA.checkRelationship(itemParamsEntity.getPatientId(), 1)).thenReturn(false);

        //assert
        Assertions.assertThrows(TeleCareException.class, () ->{
            //call service
            service.getMedicalHealthcareNoteDetail(itemParamsEntity,authentication);
        });

    }

    @Test
    void createMedicalHealthcareNote() throws TeleCareException{
        MedicalHealthcareNoteDTO itemParamsEntity = new MedicalHealthcareNoteDTO();
        Authentication authentication = null;
        itemParamsEntity.setPatientId(1);
        Mockito.when(patientsServiceJPA.getUserIdFromToken(Mockito.any())).thenReturn(1);
        Mockito.when(patientsServiceJPA.checkRelationship(itemParamsEntity.getPatientId(),1)).thenReturn(true);
        //call service
        MedicalHealthcareNoteDTO actualResult = (MedicalHealthcareNoteDTO) service.createMedicalHealthcareNote(itemParamsEntity, authentication);

        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void createMedicalHealthcareNote_HasNoRelationship() throws TeleCareException{
        MedicalHealthcareNoteDTO itemParamsEntity = new MedicalHealthcareNoteDTO();
        Authentication authentication = null;
        TelecareUserEntity userSystemEntity = new TelecareUserEntity(){
            @Override
            public boolean isDoctor() throws TeleCareException {
                return false;
            }
        };
        new MockUp<FnCommon>(){
            @mockit.Mock
            public  TelecareUserEntity getTelecareUserInfo(Authentication authentication) {
                return userSystemEntity;
            }
        };
        itemParamsEntity.setPatientId(1);
        Mockito.when(patientsServiceJPA.getUserIdFromToken(Mockito.any())).thenReturn(1);
        Mockito.when(patientsServiceJPA.checkRelationship(itemParamsEntity.getPatientId(),1)).thenReturn(false);
        //assert
        Assertions.assertThrows(TeleCareException.class, () ->{
            //call service
            service.createMedicalHealthcareNote(itemParamsEntity,authentication);
        });
    }

    @Test
    void updateMedicalHealthcareNote() throws TeleCareException {
        MedicalHealthcareNoteDTO dto = new MedicalHealthcareNoteDTO();
        Authentication authentication = null;
        dto.setPatientId(1);
        dto.setNoteId(1);

        Mockito.when(patientsServiceJPA.getUserIdFromToken(Mockito.any())).thenReturn(1);
        Mockito.when(patientsServiceJPA.checkRelationship(dto.getPatientId(),1)).thenReturn(true);

        Mockito.when(medicalHealthcareNotesRepositoryJPA.findByNoteIdAndPatientId(dto.getNoteId(), dto.getPatientId()))
                .thenReturn(new MedicalHealthcareNotesEntity());
        //call service
        MedicalHealthcareNoteDTO actualResult = (MedicalHealthcareNoteDTO) service.updateMedicalHealthcareNote(dto, authentication);

        //assert
        MatcherAssert.assertThat(actualResult, Matchers.notNullValue());
    }

    @Test
    void updateMedicalHealthcareNote_NullData() throws TeleCareException {
        MedicalHealthcareNoteDTO dto = new MedicalHealthcareNoteDTO();
        Authentication authentication = null;
        dto.setPatientId(1);
        dto.setNoteId(1);

        Mockito.when(patientsServiceJPA.getUserIdFromToken(Mockito.any())).thenReturn(1);
        Mockito.when(patientsServiceJPA.checkRelationship(dto.getPatientId(),1)).thenReturn(true);

        Mockito.when(medicalHealthcareNotesRepositoryJPA.findByNoteIdAndPatientId(dto.getNoteId(), dto.getPatientId()))
                .thenReturn(null);
        Assertions.assertThrows(TeleCareException.class, ()->{
            //call service
            service.updateMedicalHealthcareNote(dto,authentication);
        });
    }

    @Test
    void updateMedicalHealthcareNote_HasNoRelationship() throws TeleCareException {
        MedicalHealthcareNoteDTO dto = new MedicalHealthcareNoteDTO();
        Authentication authentication = null;
        dto.setPatientId(1);
        Mockito.when(patientsServiceJPA.getUserIdFromToken(Mockito.any())).thenReturn(1);
        Mockito.when(patientsServiceJPA.checkRelationship(dto.getPatientId(),1)).thenReturn(false);

        Assertions.assertThrows(TeleCareException.class, ()->{
            //call service
            service.updateMedicalHealthcareNote(dto,authentication);
        });
    }

    @Test
    void deleteMedicalHealthcareNote() throws TeleCareException {
        MedicalHealthcareNoteDTO itemParamsEntity = new MedicalHealthcareNoteDTO();
        Authentication authentication = null;
        itemParamsEntity.setPatientId(1);
        itemParamsEntity.setNoteId(1);
        Mockito.when(patientsServiceJPA.getUserIdFromToken(Mockito.any())).thenReturn(1);
        Mockito.when(patientsServiceJPA.checkRelationship(itemParamsEntity.getPatientId(),1)).thenReturn(true);
        Mockito.when(medicalHealthcareNotesRepositoryJPA.findByNoteIdAndPatientId(itemParamsEntity.getNoteId(), itemParamsEntity.getPatientId()))
                .thenReturn(new MedicalHealthcareNotesEntity());
        //call service
        Boolean actualResult = (Boolean) service.deleteMedicalHealthcareNote(itemParamsEntity, authentication);

        //assert
        MatcherAssert.assertThat(actualResult, Matchers.equalTo(true));

    }

    @Test
    void deleteMedicalHealthcareNote_HasNoRelationship() throws TeleCareException {
        MedicalHealthcareNoteDTO dto = new MedicalHealthcareNoteDTO();
        Authentication authentication = null;
        dto.setPatientId(1);
        TelecareUserEntity userSystemEntity = new TelecareUserEntity(){
            @Override
            public boolean isDoctor() throws TeleCareException {
                return false;
            }
        };
        new MockUp<FnCommon>(){
            @mockit.Mock
            public  TelecareUserEntity getTelecareUserInfo(Authentication authentication) {
                return userSystemEntity;
            }
        };
        Mockito.when(patientsServiceJPA.getUserIdFromToken(Mockito.any())).thenReturn(1);
        Mockito.when(patientsServiceJPA.checkRelationship(dto.getPatientId(),1)).thenReturn(false);

        Assertions.assertThrows(TeleCareException.class, ()->{
            //call service
            service.deleteMedicalHealthcareNote(dto,authentication);
        });
    }

    @Test
    void deleteMedicalHealthcareNote_NullData() throws TeleCareException {
        MedicalHealthcareNoteDTO itemParamsEntity = new MedicalHealthcareNoteDTO();
        Authentication authentication = null;
        itemParamsEntity.setPatientId(1);
        itemParamsEntity.setNoteId(1);
        Mockito.when(patientsServiceJPA.getUserIdFromToken(Mockito.any())).thenReturn(1);
        Mockito.when(patientsServiceJPA.checkRelationship(itemParamsEntity.getPatientId(),1)).thenReturn(true);
        Mockito.when(medicalHealthcareNotesRepositoryJPA.findByNoteIdAndPatientId(itemParamsEntity.getNoteId(), itemParamsEntity.getPatientId()))
                .thenReturn(null);
        Assertions.assertThrows(TeleCareException.class, ()->{
            //call service
            service.deleteMedicalHealthcareNote(itemParamsEntity,authentication);
        });

    }
}