package com.viettel.etc.controllers;

import com.viettel.etc.dto.MedicalHealthcarePresurgeryDTO;
import com.viettel.etc.services.tables.MedicalHealthcarePresurgeryServiceJPA;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.CoreErrorApp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Tue Sep 22 11:31:18 ICT 2020
 */
@RestController
public class MedicalHealthcarePresurgeryController {

    private static final Logger LOGGER = Logger.getLogger(MedicalHealthcarePresurgeryController.class);
    @Autowired
    private MedicalHealthcarePresurgeryServiceJPA medicalHealthcarePresurgeryServiceJPA;

    /**
     * api get medical healthcare presurgery
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @PostMapping(value = "/{patientId}/presurgery", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveMedicalHealthcarePresurgery(@AuthenticationPrincipal Authentication authentication,
                                                                  @RequestBody @Valid MedicalHealthcarePresurgeryDTO dataParams,
                                                                  @PathVariable Integer patientId) {
        dataParams.setPatientId(patientId);
        Object resultObj = null;
        try {
            resultObj = medicalHealthcarePresurgeryServiceJPA.saveMedicalHealthcarePresurgery(dataParams, authentication);
        } catch (TeleCareException e) {
            LOGGER.info(e);
            e.printStackTrace();
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * api update medical healthcare presurgery
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @PostMapping(value = "/{patientId}/presurgery/{presurgeryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatePatientPresurgery(@AuthenticationPrincipal Authentication authentication,
                                                          @RequestBody @Valid MedicalHealthcarePresurgeryDTO dataParams,
                                                          @PathVariable Integer patientId,
                                                          @PathVariable Integer presurgeryId) {
        Object resultObj = null;
        try {
            dataParams.setPatientId(patientId);
            dataParams.setPresurgeryId(presurgeryId);
            resultObj = medicalHealthcarePresurgeryServiceJPA.updatePatientPresurgery(dataParams, authentication);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.OK);
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * API delete medical healthcare presurgery
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @DeleteMapping(value = "/{patientId}/presurgery/{presurgeryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deletePatientPresurgery(@AuthenticationPrincipal Authentication authentication,
                                                          MedicalHealthcarePresurgeryDTO dataParams,
                                                          @PathVariable Integer patientId,
                                                          @PathVariable Integer presurgeryId) {
        try {
            dataParams.setPatientId(patientId);
            dataParams.setPresurgeryId(presurgeryId);
            medicalHealthcarePresurgeryServiceJPA.deletePatientPresurgery(dataParams, authentication);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.OK);
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(CoreErrorApp.SUCCESS, null), HttpStatus.OK);
    }
}
