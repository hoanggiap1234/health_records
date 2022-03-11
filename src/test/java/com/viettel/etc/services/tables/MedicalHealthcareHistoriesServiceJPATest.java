package com.viettel.etc.services.tables;

import com.viettel.etc.dto.BookingInformationsDTO;
import com.viettel.etc.dto.MedicalHealthcarePatientSummaryDTO;
import com.viettel.etc.kafka.domain.ConsultantResultDTO;
import com.viettel.etc.kafka.domain.healthcare_result.*;
import com.viettel.etc.kafka.service.KafkaService;
import com.viettel.etc.repositories.BookingInformationsRepository;
import com.viettel.etc.repositories.tables.*;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

class MedicalHealthcareHistoriesServiceJPATest {
    @Mock
    MedicalHealthcareHistoriesRepositoryJPA medicalHealthcareHistories;

    @Mock
    BookingInformationsRepository bookingInformationsRepository;

    @Mock
    MedicalHealthcareDrugsRepositoryJPA medicalHealthcareDrugsRepositoryJPA;

    @Mock
    MedicalHealthcareHistoriesAttachmentsRepositoryJPA medicalHealthcareHistoriesAttachmentsRepositoryJPA;

    @Mock
    MedicalHealthcareServicesRepositoryJPA healthcareServicesRepositoryJPA;

    @Mock
    MedicalHealthcareHistoriesIcdRepositoryJPA historiesIcdRepositoryJPA;
    @Mock
    MedicalHealthcarePatientDetailRepositoryJPA medicalHealthcarePatientDetailRepositoryJPA;
    @Mock
    CatsHealthfacilitiesRepositoryJPA healthfacilitiesRepositoryJPA;
    @Mock
    MedicalHealthcareServicesResultsRepositoryJPA healthcareServicesResultsRepositoryJPA;
    @Mock
    KafkaService kafkaService;
    @Mock
    MedicalHealthcarePatientSummaryRepositoryJPA medicalHealthcarePatientSummaryRepositoryJPA;

    @InjectMocks
    MedicalHealthcareHistoriesServiceJPA service;

    @BeforeEach
    void setUp() {
        service = new MedicalHealthcareHistoriesServiceJPA();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveHealthcareHistories() throws Exception {
        service = Mockito.spy(new MedicalHealthcareHistoriesServiceJPA(){
            @Override
            void saveHealcareServiceHealthcareHistories(MedicalHealthcareHistoriesEntity histories, ConsultantResultDTO dto){

            }

            @Override
            void saveHistoriesIcdHealthcareHistories(int createdUser, int hisId, int bookingId, ConsultantResultDTO dto) {

            }

            @Override
            void saveHealthcareDetailHealthcareHistories(MedicalHealthcareHistoriesEntity historiesEntity, ConsultantResultDTO dto) {

            }

            @Override
            void saveAttachmentHealthcareHistories(MedicalHealthcareHistoriesEntity historiesEntity, ConsultantResultDTO dto) {

            }

        });
        MockitoAnnotations.initMocks(this);

        int bookingId = 1;
        ConsultantResultDTO dto = new ConsultantResultDTO();

        BookingInformationsDTO booking = new BookingInformationsDTO();
        booking.setHistoriesId(1);
        booking.setPatientId(1);
        booking.setAppointmentDoctorName("test");
        booking.setAppointmentDoctorId(1);
        booking.setHealthfacilitiesCode("1");
        booking.setHealthfacilitiesName("test");
        booking.setSymptomsOrReason("test");
        dto.setExaminationDate(new Date());
        dto.setFinishExaminationDate(new Date());
        dto.setConcludesDisease("test");
        dto.setTreatmentDirection("test");
        dto.setReExaminationDate(new Date());
        dto.setTypeOfExamination(1);
        dto.setActionBy(1);
        Mockito.when(bookingInformationsRepository.getById(bookingId)).thenReturn(booking);
        MedicalHealthcareHistoriesEntity historiesEntity = new MedicalHealthcareHistoriesEntity();
        historiesEntity.setHistoriesId(1);
        Mockito.when(medicalHealthcareHistories.findById(booking.getHistoriesId())).thenReturn(java.util.Optional.of(historiesEntity));

        //call service
        service.saveHealthcareHistories(bookingId, dto);
    }

    @Test
    void saveHealthcareInsuranceResult() throws Exception {
        service = Mockito.spy(new MedicalHealthcareHistoriesServiceJPA(){
            @Override
            void saveHealthcareDetail(MedicalHealthcareHistoriesEntity historiesEntity, HealthcareInsuranceResultEntity dto){

            }

            @Override
            void saveHealthcareDrugs(MedicalHealthcareHistoriesEntity histories, HealthcareInsuranceResultEntity dto)  {

            }

            @Override
            void saveHistoriesIcd(Integer createdUser, int hisId, int bookingId, HealthcareInsuranceResultEntity dto) {

            }

            @Override
            void saveHealcareService(MedicalHealthcareHistoriesEntity histories, HealthcareInsuranceResultEntity dto) {

            }

        });
        MockitoAnnotations.initMocks(this);

        HealthcareInsuranceResultEntity dto = new HealthcareInsuranceResultEntity();
        dto.setActionBy(1);
        GeneralInformation generalInfor = new GeneralInformation();
        generalInfor.setBookingId(1);
        generalInfor.setReExaminationDate(new Date());
        generalInfor.setHealthfacilitiesCode("test");
        dto.setGeneralInformation(generalInfor);
        BookingInformationsDTO booking = new BookingInformationsDTO();
        booking.setBookingId(1);
        booking.setHistoriesId(1);
        booking.setPatientId(1);

        Mockito.when(bookingInformationsRepository.getById(generalInfor.getBookingId())).thenReturn(booking);
        MedicalHealthcareHistoriesEntity historiesDB = new MedicalHealthcareHistoriesEntity();
        Mockito.when(medicalHealthcareHistories.findById(booking.getHistoriesId())).thenReturn(java.util.Optional.of(historiesDB));

        CatsHealthfacilitiesEntity healthfaciliti = new CatsHealthfacilitiesEntity();
        healthfaciliti.setName("test");
        healthfaciliti.setHealthfacilitiesCode("test");
        Mockito.when(healthfacilitiesRepositoryJPA
                .findByHealthfacilitiesCodeAndIsDelete(generalInfor.getHealthfacilitiesCode(), Constants.IS_NOT_DELETE)).thenReturn(healthfaciliti);
        MedicalHealthcareHistoriesEntity result = new MedicalHealthcareHistoriesEntity();
        result.setHistoriesId(1);
        Mockito.when(medicalHealthcareHistories.save(Mockito.any())).thenReturn(result);

        service.saveHealthcareInsuranceResult(dto);
    }

    @Test
    void saveMedicalDrugHealthcareHistories() {
        MedicalHealthcareHistoriesEntity historiesEntity = new MedicalHealthcareHistoriesEntity();
        historiesEntity.setHistoriesId(1);
        historiesEntity.setPatientId(1);
        historiesEntity.setHealthfacilitiesCode("1");
        historiesEntity.setCreateUserId(1);
        ConsultantResultDTO dto = new ConsultantResultDTO();
        dto.setExaminationDate(new Date());
        dto.setDrugs(Arrays.asList(new ConsultantResultDTO.Drug()));

        service.saveMedicalDrugHealthcareHistories(historiesEntity, dto);
    }

    @Test
    void saveAttachmentHealthcareHistories() {
        MedicalHealthcareHistoriesEntity historiesEntity = new MedicalHealthcareHistoriesEntity();
        historiesEntity.setCreateUserId(1);
        historiesEntity.setHistoriesId(1);
        ConsultantResultDTO dto = new ConsultantResultDTO();
        dto.setActionBy(1);
        dto.setAttachments(Arrays.asList(new ConsultantResultDTO.Attacments()));
        service.saveAttachmentHealthcareHistories(historiesEntity, dto);
    }

    @Test
    void saveHealcareServiceHealthcareHistories() {
        MedicalHealthcareHistoriesEntity histories = new MedicalHealthcareHistoriesEntity();
        histories.setHistoriesId(1);
        histories.setPatientId(1);
        histories.setCreateUserId(1);
        ConsultantResultDTO dto = new ConsultantResultDTO();
        ConsultantResultDTO.Subclinical serviceInformation= new ConsultantResultDTO.Subclinical();
        serviceInformation.setServiceCode("1");
        serviceInformation.setGroupId(1);
        ConsultantResultDTO.Attacments attachment = new ConsultantResultDTO.Attacments();
        attachment.setFileName("test");
        attachment.setUrl("test");
        serviceInformation.setAttachments(Arrays.asList(attachment));
        serviceInformation.setIndexs(Arrays.asList(new ConsultantResultDTO.ServiceIndex()));
        dto.setSubclinical(Arrays.asList(serviceInformation));

        MedicalHealthcareServicesEntity entity = new MedicalHealthcareServicesEntity();
        entity.setServiceCode("1");
        Mockito.when(healthcareServicesRepositoryJPA
                .findByHistoriesId(histories.getHistoriesId())).thenReturn(Arrays.asList());

        service.saveHealcareServiceHealthcareHistories(histories, dto);
    }

    @Test
    void saveHistoriesIcdHealthcareHistories() {
        int createdUser =1;
        int hisId =1;
        int bookingId=1;
        ConsultantResultDTO dto = new ConsultantResultDTO();
        ConsultantResultDTO.Diagnostic diagnostic = new ConsultantResultDTO.Diagnostic();
        diagnostic.setDiseases(Arrays.asList(new ConsultantResultDTO.Diseases()));
        dto.setDiagnostic(diagnostic);
        List<MedicalHealthcareHistoriesIcdEntity> entitiesOld = new ArrayList<>();
        MedicalHealthcareHistoriesIcdEntity entity = new MedicalHealthcareHistoriesIcdEntity();
        entity.setDiseasesCode("1");
        Mockito.when(historiesIcdRepositoryJPA.findByHistoriesId(hisId)).thenReturn(entitiesOld);

        service.saveHistoriesIcdHealthcareHistories(createdUser, hisId, bookingId, dto);
    }

    @Test
    void saveHealthcareDetailHealthcareHistories() throws Exception {
        service = Mockito.spy(new MedicalHealthcareHistoriesServiceJPA(){
            @Override
            public void createPatientSummary(MedicalHealthcarePatientSummaryDTO dto){

            }
        });
        MockitoAnnotations.initMocks(this);

        MedicalHealthcareHistoriesEntity historiesEntity = new MedicalHealthcareHistoriesEntity();
        historiesEntity.setCreateUserId(1);
        historiesEntity.setHistoriesId(1);
        ConsultantResultDTO dto = new ConsultantResultDTO();
        ConsultantResultDTO.LivingFunction livingFunction = new ConsultantResultDTO.LivingFunction();
        livingFunction.setBmi(1.0);
        dto.setLivingFunction(livingFunction);
        MedicalHealthcarePatientDetailEntity detailDB = new MedicalHealthcarePatientDetailEntity();
        Mockito.when(medicalHealthcarePatientDetailRepositoryJPA
                .findByHistoriesId(historiesEntity.getHistoriesId())).thenReturn(detailDB);
        service.saveHealthcareDetailHealthcareHistories(historiesEntity, dto);
    }

    @Test
    void saveHealthcareDetail() throws Exception {
        MedicalHealthcareHistoriesEntity historiesEntity = new MedicalHealthcareHistoriesEntity();
        historiesEntity.setHistoriesId(1);
        historiesEntity.setPatientId(1);
        historiesEntity.setCreateUserId(1);
        HealthcareInsuranceResultEntity dto = new HealthcareInsuranceResultEntity();
        GeneralInformation generalInformation = new GeneralInformation();
        generalInformation.setWeight(1.0);
        dto.setGeneralInformation(generalInformation);
        Mockito.when(medicalHealthcarePatientDetailRepositoryJPA
                .findByHistoriesId(historiesEntity.getHistoriesId())).thenReturn(new MedicalHealthcarePatientDetailEntity());
        service.saveHealthcareDetail(historiesEntity, dto);
    }

    @Test
    void saveHistoriesIcd() {
        Integer createdUser=1;
        int hisId =1;
        int bookingId=1;
        HealthcareInsuranceResultEntity dto = new HealthcareInsuranceResultEntity();
        DiseaseInformation diseaseInformation = new DiseaseInformation();
        diseaseInformation.setDiseasesCode("1");
        dto.setDiseaseInformations(Arrays.asList(diseaseInformation));

        List<MedicalHealthcareHistoriesIcdEntity> entitiesOld = new ArrayList<>();
        MedicalHealthcareHistoriesIcdEntity entity = new MedicalHealthcareHistoriesIcdEntity();
        entity.setDiseasesCode("1");
        entitiesOld.add(entity);
        Mockito.when(historiesIcdRepositoryJPA.findByHistoriesId(hisId)).thenReturn(entitiesOld);

        service.saveHistoriesIcd(createdUser, hisId, bookingId, dto);
    }

    @Test
    void saveHealcareService() {
        MedicalHealthcareHistoriesEntity histories = new MedicalHealthcareHistoriesEntity();
        histories.setHistoriesId(1);

        HealthcareInsuranceResultEntity dto = new HealthcareInsuranceResultEntity();
        ServiceInformation serviceInformation = new ServiceInformation();
        serviceInformation.setServiceCode("1");
        dto.setServiceInformations(Arrays.asList(serviceInformation));

        GeneralInformation generalInformation = new GeneralInformation();
        generalInformation.setHealthfacilitiesCode("1");
        generalInformation.setWeight(1.0);
        dto.setGeneralInformation(generalInformation);

        ClinicalResults clinicalResults = new ClinicalResults();
        dto.setClinicalResults(Arrays.asList(clinicalResults));
        clinicalResults.setServiceCode("1");

        List<MedicalHealthcareServicesEntity> entitiesOld = new ArrayList<>();
        MedicalHealthcareServicesEntity entity = new MedicalHealthcareServicesEntity();
        entity.setServiceCode("1");
        Mockito.when(healthcareServicesRepositoryJPA
                .findByHistoriesId(histories.getHistoriesId())).thenReturn(entitiesOld);

        List<MedicalHealthcareServicesResultsEntity> resultEntitieOld = new ArrayList<>();
        MedicalHealthcareServicesResultsEntity entity1 = new MedicalHealthcareServicesResultsEntity();
        entity1.setServiceCode("1");
        resultEntitieOld.add(entity1);
        service.saveHealcareService(histories, dto);
    }

    @Test
    void saveHealthcareDrugs() {
        MedicalHealthcareHistoriesEntity histories = new MedicalHealthcareHistoriesEntity();
        histories.setHistoriesId(1);
        histories.setPatientId(1);
        histories.setCreateUserId(1);

        HealthcareInsuranceResultEntity dto = new HealthcareInsuranceResultEntity();
        dto.setDrugInformations(Arrays.asList(new DrugInformation()));
        GeneralInformation generalInformation = new GeneralInformation();
        generalInformation.setHealthfacilitiesCode("1");
        dto.setGeneralInformation(generalInformation);

        DrugInformation drugInformation = new DrugInformation();
        drugInformation.setDecisionDate(new Date());
        drugInformation.setDrugCode("1");

        List<MedicalHealthcareDrugsEntity> drugsEntitiesOld = new ArrayList<>();
        MedicalHealthcareDrugsEntity drugsEntity = new MedicalHealthcareDrugsEntity();
        drugsEntity.setDrugCode("1");
        Mockito.when(medicalHealthcareDrugsRepositoryJPA
                .findByHistoriesId(histories.getHistoriesId())).thenReturn(drugsEntitiesOld);

        service.saveHealthcareDrugs(histories, dto);
    }

}