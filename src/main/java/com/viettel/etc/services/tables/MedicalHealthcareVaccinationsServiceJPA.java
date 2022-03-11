package com.viettel.etc.services.tables;

import com.google.gson.Gson;
import com.viettel.etc.controllers.MedicalHealthcareHistoriesController;
import com.viettel.etc.dto.*;
import com.viettel.etc.kafka.service.KafkaService;
import com.viettel.etc.repositories.PatientRepository;
import com.viettel.etc.repositories.tables.DoctorsHealthfacilitiesRepositoryJPA;
import com.viettel.etc.repositories.tables.DoctorsRepositoryJPA;
import com.viettel.etc.repositories.tables.MedicalHealthcareVaccinationsRepositoryJPA;
import com.viettel.etc.repositories.tables.PatientsRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.DoctorsEntity;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcareVaccinationsEntity;
import com.viettel.etc.services.MessageService;
import com.viettel.etc.utils.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


/**
 * Autogen class: Create Service For Table Name Medical_healthcare_vaccinations
 *
 * @author ToolGen
 * @date Fri Feb 26 15:43:24 ICT 2021
 */
@Service
public class MedicalHealthcareVaccinationsServiceJPA {

    @Autowired
    MedicalHealthcareVaccinationsRepositoryJPA vaccineServiceJPA;

    @Autowired
    PatientsRepositoryJPA patientRepositoryJPA;

    @Autowired
    DoctorsRepositoryJPA doctorsRepositoryJPA;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    MedicalHealthcareVaccinationsRepositoryJPA medicalHealthcareVaccinationsRepositoryJPA;

    @Autowired
    DoctorsHealthfacilitiesRepositoryJPA doctorsHealthfacilitiesRepositoryJPA;

    @Autowired
    MessageService messageService;

    @Autowired
    KafkaService kafkaService;

    private static final Logger LOGGER = Logger.getLogger(MedicalHealthcareHistoriesController.class);

    public void saveVaccine(Authentication authentication, CovidPatientResultDTO covidPatientResultDTO) throws Exception {
        boolean isUpdate = true;
        String language = covidPatientResultDTO.getLanguage();
        TelecareUserEntity telecareUserEntity = FnCommon.getTelecareUserInfo(authentication);
        if (telecareUserEntity == null || (!telecareUserEntity.isDoctor() && !telecareUserEntity.isNurs())) {
            throw new TeleCareException(ErrorApp.ERR_USER_PERMISSION);
        }
        Integer doctorId = telecareUserEntity.getTelecareUserId().intValue();
        if (!telecareUserEntity.isAdmin()) {
            this.validateDoctorHealthFacility(covidPatientResultDTO, language, doctorId);
        }

        // save vao lich su tiem chung cua HSSK
        covidPatientResultDTO.setSyncTelecare(Constants.SYNC_TELECARE);
        covidPatientResultDTO.setSyncTelecareResult(Constants.SYNC_TELECARE_RESULT);
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + FnCommon.getTokenHSSK());
        HttpResponse response = RequestUtil.sendRequest(HttpMethod.POST, System.getenv("HSSK_VACCINES_URL") + "/api/v1/immunization/covid-patient/vaccinated",
                covidPatientResultDTO, headers);
//        HttpResponse response = RequestUtil.sendRequest(HttpMethod.POST, "http://localhost:9006/covid-patient/vaccinated",
//                covidPatientResultDTO, headers);
        if (response == null || !((response.getStatus() == HttpStatus.OK || response.getStatus() == HttpStatus.CREATED)
                && response.getBody() != null)) {
            String messCode = ErrorApp.FAIL_CREATE_COVID_PATIENT.name();
            if(response!=null) {
                HSSKErrorMessage hsskMessage = new Gson().fromJson(response.getBody(), HSSKErrorMessage.class);
                if(hsskMessage.getMess()!=null) {
                    messCode = hsskMessage.getMess().getMessCode();
                }
            }
            throw new TeleCareException(ErrorApp.FAIL_CREATE_COVID_PATIENT, messageService.getMessage(messCode, language));
        }
        VaccineResponseDTO data1 = new Gson().fromJson(response.getBody(), VaccineResponseDTO.class);
        VaccineResponseDTO.VaccineSubDTO data = data1.getData();
        if (data == null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS);
        }

        /* save lich su tiem chung ben Telecare*/
        MedicalHealthcareVaccinationsEntity vaccinationEntity = vaccineServiceJPA.findFirstByVaccineNameAndPhoneNumberAndInjectionDate(data.getVaccineNameVi(), covidPatientResultDTO.getPersonalPhoneNumber(), covidPatientResultDTO.getInjectionDate());
        if (vaccinationEntity == null) {
            vaccinationEntity = new MedicalHealthcareVaccinationsEntity();
            isUpdate = false;
        } else {
            vaccinationEntity.setUpdateDate(new Date());
        }
        FnCommon.copyProperties(covidPatientResultDTO, vaccinationEntity);
        FnCommon.copyProperties(data, vaccinationEntity);
        vaccinationEntity.setVaccineName(data.getVaccineNameVi());
        vaccinationEntity.setInjectionsNumber(data.getNumberVaccine());
        vaccinationEntity.setPhoneNumber(covidPatientResultDTO.getPersonalPhoneNumber());
        List<PatientDTO> patientsInfo = (List<PatientDTO>) patientRepository.getPatientByParams(covidPatientResultDTO);
        PatientDTO patientInfo = null;
        if (patientsInfo != null && patientsInfo.size() == 1) {
            patientInfo = patientsInfo.get(0);
            if (patientInfo != null) {
                vaccinationEntity.setPatientId(patientInfo.getPatientId());
            } else {
                LOGGER.info("Chua co tai khoan tren Telecare!");
            }
        }
        try {
            vaccineServiceJPA.save(vaccinationEntity);
        } catch (Exception e) {
            // neu xay ra loi => gui sang HSSK ban ghi chua duoc dong bo
            covidPatientResultDTO.setSyncTelecare(Constants.NOT_SYNC_TELECARE);
            covidPatientResultDTO.setSyncTelecareResult(Constants.NOT_SYNC_TELECARE_RESULT);
            RequestUtil.sendRequest(HttpMethod.POST, System.getenv("HSSK_VACCINES_URL") + "/api/v1/immunization/covid-patient/vaccinated",
                    covidPatientResultDTO, headers);
            throw new TeleCareException(ErrorApp.ERROR_SAVE_DATA, messageService.getMessage(Constants.ERROR_SAVE_DATA, Constants.VIETNAM_CODE));
        }

        //if not update => send to Account service to create notice
        if (!isUpdate) {
            sendNotiToAccountServiceByKafka(covidPatientResultDTO, patientInfo);
        }
    }

    private void sendNotiToAccountServiceByKafka(CovidPatientResultDTO covidPatientResultDTO, PatientDTO patientInfo) throws TeleCareException {
        CovidNotificationDTO covidNotificationDTO = CovidNotificationDTO.builder()
                .phoneNumber(covidPatientResultDTO.getPersonalPhoneNumber())
                .contentType(Constants.COVID_CONTENT_TYPE)
                .injectionDate(LocalDateTime.ofInstant(covidPatientResultDTO.getInjectionDate().toInstant(), ZoneId.systemDefault()).toString())
//                .fullname(patientInfo!=null?patientInfo.getFullname() : "")
                .patientId(patientInfo != null ? patientInfo.getPatientId() : null)
                .fullname(covidPatientResultDTO.getFullname() != null ? covidPatientResultDTO.getFullname() : "")
                .build();
        kafkaService.sendCovidNoti(covidNotificationDTO.getPhoneNumber(), covidNotificationDTO);
    }

    private void validateDoctorHealthFacility(CovidPatientResultDTO covidPatientResultDTO, String language, Integer doctorId) throws TeleCareException {
        String healthfacilitiesId = covidPatientResultDTO.getHealthfacilitiesId();
        // !=null => validate, =null => continue
        if (healthfacilitiesId != null) {
            Boolean isDoctorInHealthFacility =
                    doctorsHealthfacilitiesRepositoryJPA.existsByDoctorIdAndHealthfacilitiesCodeAndIsActiveAndIsDelete(doctorId, healthfacilitiesId, Constants.IS_ACTIVE_BOOLEAN, Constants.IS_NOT_DELETE_BOOLEAN);
            if (!isDoctorInHealthFacility) {
                throw new TeleCareException(ErrorApp.DOCTOR_IS_NOT_IN_HEALTH_FACILITY, messageService.getMessage(Constants.DOCTOR_IS_NOT_IN_HEALTH_FACILITY, language));
            }
        }
    }

    // api cho hssk
    public void save(CovidPatientResultDTO resultDTO, Authentication authentication) throws TeleCareException {
        TelecareUserEntity userEntity = FnCommon.getTelecareUserInfo(authentication);
        boolean isUpdate = true;
        if (userEntity == null || (!userEntity.isAdmin())) {
            throw new TeleCareException(ErrorApp.ERR_USER_PERMISSION);
        }
        MedicalHealthcareVaccinationsEntity vaccinationEntity = vaccineServiceJPA.findFirstByVaccineNameAndPhoneNumberAndInjectionDate(
                resultDTO.getVaccineNameVi(), resultDTO.getPersonalPhoneNumber(), resultDTO.getInjectionDate());
        if (vaccinationEntity == null) {
            vaccinationEntity = new MedicalHealthcareVaccinationsEntity();
            isUpdate = false;
        }
        FnCommon.copyProperties(resultDTO, vaccinationEntity);
        vaccinationEntity.setHealthfacilitiesCode(resultDTO.getHealthfacilitiesId());
        vaccinationEntity.setVaccineName(resultDTO.getVaccineNameVi());
        vaccinationEntity.setInjectionsNumber(resultDTO.getInjectionsNumber());
        vaccinationEntity.setPhoneNumber(resultDTO.getPersonalPhoneNumber());
        List<PatientDTO> patientsInfo = (List<PatientDTO>) patientRepository.getPatientByParams(resultDTO);
        PatientDTO patientInfo = null;
        if (patientsInfo != null && patientsInfo.size() == 1) {
            patientInfo = patientsInfo.get(0);
            if (patientInfo != null) {
                vaccinationEntity.setPatientId(patientInfo.getPatientId());
            } else {
                LOGGER.info("Chua co tai khoan tren Telecare!");
            }
        }
        vaccineServiceJPA.save(vaccinationEntity);
        //if not update => send to Account service to create notice
        if (!isUpdate) {
            sendNotiToAccountServiceByKafka(resultDTO, patientInfo);
        }
    }

    public void saveListResult(List<CovidPatientResultDTO> data, Authentication authentication) throws TeleCareException {
        TelecareUserEntity userEntity = FnCommon.getTelecareUserInfo(authentication);
        if (userEntity == null || (userEntity != null && !userEntity.isAdmin())) {
            throw new TeleCareException(ErrorApp.ERR_USER_PERMISSION, messageService.getMessage(Constants.ERR_USER_PERMISSION, Constants.VIETNAM_CODE));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        data.forEach(e -> {
            MedicalHealthcareVaccinationsEntity entity = null;
            try {
                Date injectionDate = sdf.parse(e.getInjection_date());
                entity = vaccineServiceJPA.findFirstByVaccineNameAndPhoneNumberAndInjectionDate(e.getVaccineNameVi(), e.getPersonalPhoneNumber(), injectionDate);
                if (entity == null) {
                    entity = new MedicalHealthcareVaccinationsEntity();
                }
                FnCommon.copyProperties(e, entity);
                entity.setVaccineName(e.getVaccineNameVi());
                entity.setPhoneNumber(e.getPersonalPhoneNumber().replaceAll(" ", ""));
                entity.setInjectionDate(injectionDate);
                entity.setFullname(e.getFullname());
                vaccineServiceJPA.save(entity);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
