package com.viettel.etc.controllers;

import com.viettel.etc.dto.MedicalHealthcareHistoriesDTO;
import com.viettel.etc.services.MedicalHealthcareHistoriesService;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Tue Sep 22 16:35:41 ICT 2020
 */
@RestController
public class MedicalHealthcareHistoriesController {
    private static final Logger LOGGER = Logger.getLogger(MedicalHealthcareHistoriesController.class);

    @Autowired
    private MedicalHealthcareHistoriesService medicalHealthcareHistoriesService;

    /**
     * Danh sach cac don thuoc (Ho so suc khoe)
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @GetMapping(value = "/{patientId}/healthcare-drugs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getHealthcareDrugs(@AuthenticationPrincipal Authentication authentication,
                                                                MedicalHealthcareHistoriesDTO dataParams, @PathVariable Integer patientId) {
        dataParams.setPatientId(patientId);
        Object resultObj = medicalHealthcareHistoriesService.getHealthcareDrugs(dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Danh sach cac don thuoc (Ho so suc khoe)
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @GetMapping(value = "/{patientId}/healthcare-drugs/{historiesId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDetailHealthcareDrug(@AuthenticationPrincipal Authentication authentication,
                                                                MedicalHealthcareHistoriesDTO dataParams, @PathVariable Integer patientId, @PathVariable Integer historiesId) {
        dataParams.setPatientId(patientId);
        dataParams.setHistoriesId(historiesId);
        Optional<MedicalHealthcareHistoriesDTO> resultObj = medicalHealthcareHistoriesService.getDetailHealthcareDrug(dataParams);
        if (!resultObj.isPresent()) {
            return new ResponseEntity<>(FnCommon.responseToClient(new TeleCareException(ErrorApp.ERR_DATA_HEALTHCARE_DRUG_NOT_EXIST)), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Danh sach lich tai kham
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @GetMapping(value = "/{patientId}/re-examination", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getReExaminations(@AuthenticationPrincipal Authentication authentication,
                                                     MedicalHealthcareHistoriesDTO dataParams,
                                                    @PathVariable("patientId") Integer patientId) {
        dataParams.setPatientId(patientId);
        Object resultObj;
        try{
            resultObj = medicalHealthcareHistoriesService.getReExaminations(dataParams, authentication);
        } catch (TeleCareException e) {
            LOGGER.info(e);
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}