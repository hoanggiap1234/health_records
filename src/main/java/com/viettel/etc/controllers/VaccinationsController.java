package com.viettel.etc.controllers;

import com.viettel.etc.dto.CovidPatientResultDTO;
import com.viettel.etc.dto.VaccinationsDTO;
import com.viettel.etc.repositories.tables.MedicalHealthcareVaccinationsRepositoryJPA;
import com.viettel.etc.services.MessageService;
import com.viettel.etc.services.VaccinationsService;
import com.viettel.etc.services.tables.MedicalHealthcareVaccinationsServiceJPA;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Thu Aug 27 09:47:43 ICT 2020
 */
@RestController
public class VaccinationsController {
    private static final Logger LOGGER = Logger.getLogger(VaccinationsController.class);
    @Autowired private VaccinationsService vaccinationsService;

    @Autowired
    private MedicalHealthcareVaccinationsServiceJPA vaccineServiceJPA;

    @Autowired
    private MessageService messageService;

    /**
    * api get vaccinations history
    *
    * @param authentication: thong tin nguoi dung
    * @param dataParams params client
    * @return
    */
    @GetMapping(
    value = "/{patientId}/vaccinations",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getVaccinationsHistory(
                                @AuthenticationPrincipal Authentication authentication,
                                @PathVariable Integer patientId,
                                VaccinationsDTO dataParams,  @RequestHeader Optional<String> lang) {
        String language = lang.orElse(Constants.VIETNAM_CODE);
        dataParams.setLanguage(language);
        try {
            ResultSelectEntity resultObj = vaccinationsService.getVaccinationsHistory(dataParams, patientId, authentication);
            return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
        } catch (TeleCareException e){
            LOGGER.info(e);
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            LOGGER.info(e);
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(ErrorApp.UNKNOW_ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{patientId}/vaccinations-covid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getVaccinationsCovidHistory(@AuthenticationPrincipal Authentication authentication, @PathVariable Integer patientId,
                                                              VaccinationsDTO dataParams,  @RequestHeader Optional<String> lang) {
        String language = lang.orElse(Constants.VIETNAM_CODE);
        dataParams.setLanguage(language);
        try {
            Object resultObj = vaccinationsService.getVaccinationsCovidHistory(dataParams, patientId, authentication);
            return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
        } catch (TeleCareException e){
            LOGGER.info(e);
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            LOGGER.info(e);
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(ErrorApp.UNKNOW_ERROR), HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * api get detail vaccinations history
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams:     params client
     * @param patientId:      patient id
     * @param vaccinationsId:  vaccination id
     * @return
     */
    @GetMapping(value = "/{patientId}/vaccinations/{vaccinationsId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDetailVaccinationsHistory(@AuthenticationPrincipal Authentication authentication,
                                                               VaccinationsDTO dataParams,
                                                               @PathVariable Integer patientId,
                                                               @PathVariable Integer vaccinationsId) {
        /*
        ==========================================================
        authenEntity: user info and role
        dataParams: danh sach bien client co the truyen len
        patientId: patient id
        vaccinationId: vaccination id
        ==========================================================
        */
        dataParams.setPatientId(patientId);
        dataParams.setVaccinationId(vaccinationsId);

        Optional<VaccinationsDTO> resultDataOpt = vaccinationsService.getDetailVaccinationsHistory(dataParams);
        if (!resultDataOpt.isPresent()) {
            return new ResponseEntity<>(new TeleCareException(ErrorApp.ERR_DATA_PATIENT_NOT_EXIST), HttpStatus.OK);
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultDataOpt.get()), HttpStatus.OK);
    }

    @PostMapping(value = "/vaccinated", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveVaccine(@AuthenticationPrincipal Authentication authentication,
                                              @RequestBody CovidPatientResultDTO covidPatientResultDTO,
                                              @RequestHeader Optional<String> lang) {

        try {
            String language = lang.orElse(Constants.VIETNAM_CODE);
            covidPatientResultDTO.setLanguage(language);
            vaccineServiceJPA.saveVaccine(authentication, covidPatientResultDTO);
            return new ResponseEntity<>(FnCommon.responseToClient(messageService.getMessage(Constants.SUCCESS_CREATE_COVID_PATIENT,
                    Constants.VIETNAM_CODE)), HttpStatus.OK);
        } catch (TeleCareException e) {
            LOGGER.info(e);
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.info(e);
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(messageService.getMessage(Constants.FAIL_CREATE_COVID_PATIENT,
                    Constants.VIETNAM_CODE)), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/vaccinated-telecare", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveVaccineHssk(@AuthenticationPrincipal Authentication authentication,
                                              @RequestBody CovidPatientResultDTO covidPatientResultDTO) {

        try {
            vaccineServiceJPA.save(covidPatientResultDTO, authentication);
            return new ResponseEntity<>(FnCommon.responseToClient(messageService.getMessage(Constants.SUCCESS_CREATE_COVID_PATIENT,
                    Constants.VIETNAM_CODE)), HttpStatus.OK);
        } catch (TeleCareException e) {
            LOGGER.info(e);
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.info(e);
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(messageService.getMessage(Constants.FAIL_CREATE_COVID_PATIENT,
                    Constants.VIETNAM_CODE)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/vaccinated-import", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> importVaccinatedResult(@AuthenticationPrincipal Authentication authentication,
                                                  @RequestBody List<CovidPatientResultDTO> data) {
        try {
            vaccineServiceJPA.saveListResult(data, authentication);
            return new ResponseEntity<>(FnCommon.responseToClient(messageService.getMessage(Constants.SUCCESS_CREATE_COVID_PATIENT,
                    Constants.VIETNAM_CODE)), HttpStatus.OK);
        } catch (TeleCareException e) {
            LOGGER.info(e);
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.info(e);
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(ErrorApp.UNKNOW_ERROR), HttpStatus.BAD_REQUEST);
        }
    }
}
