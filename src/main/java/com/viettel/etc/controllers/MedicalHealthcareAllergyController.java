package com.viettel.etc.controllers;

import com.viettel.etc.dto.MedicalHealthcareAllergyDTO;
import com.viettel.etc.services.MedicalHealthcareAllergyService;
import com.viettel.etc.services.tables.MedicalHealthcareAllergyServiceJPA;
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
 * @date Tue Sep 22 10:56:16 ICT 2020
 */
@RestController
public class MedicalHealthcareAllergyController {

    @Autowired
    private MedicalHealthcareAllergyService medicalHealthcareAllergyService;

    @Autowired
    private MedicalHealthcareAllergyServiceJPA medicalHealthcareAllergyServiceJPA;

    private static final Logger LOGGER = Logger.getLogger(MedicalHealthcareAllergyController.class);
    /**
     * api get medical healthcare allergy
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @GetMapping(value = "medical-healthcare-allergy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMedicalHealthcareAllergy(@AuthenticationPrincipal Authentication authentication,
                                                              MedicalHealthcareAllergyDTO dataParams) {
        /*
        ==========================================================
        authenEntity: user info and role
        dataParams: danh sach bien client co the truyen len
        ==========================================================
        */
        Object resultObj = medicalHealthcareAllergyService.getMedicalHealthcareAllergy(dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * API tạo tiền sử dị ứng
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @PostMapping(value = "/{patientId}/prehistoric/allergy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createPatientAllergy(@AuthenticationPrincipal Authentication authentication,
                                                       @RequestBody @Valid MedicalHealthcareAllergyDTO dataParams,
                                                       @PathVariable Integer patientId) {
        dataParams.setPatientId(patientId);
        Object resultObj = null;
        try {
            resultObj = medicalHealthcareAllergyServiceJPA.createPatientAllergy(dataParams);
        } catch (TeleCareException e) {
            LOGGER.info(e);
            e.printStackTrace();
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * API update tiền sử dị ứng
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @PostMapping(value = "/{patientId}/prehistoric/allergy/{allergyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatePatientAllergy(@AuthenticationPrincipal Authentication authentication,
                                                       @RequestBody @Valid MedicalHealthcareAllergyDTO dataParams,
                                                       @PathVariable Integer patientId,
                                                       @PathVariable Integer allergyId) {
        try {
            dataParams.setPatientId(patientId);
            dataParams.setAllergyId(allergyId);
            medicalHealthcareAllergyServiceJPA.updatePatientAllergy(dataParams, authentication);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.OK);
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(CoreErrorApp.SUCCESS, null), HttpStatus.OK);
    }


    /**
     * API delete tiền sử dị ứng
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @DeleteMapping(value = "/{patientId}/prehistoric/allergy/{allergyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deletePatientAllergy(@AuthenticationPrincipal Authentication authentication,
                                                       MedicalHealthcareAllergyDTO dataParams,
                                                       @PathVariable Integer patientId,
                                                       @PathVariable Integer allergyId) {
        try {
            dataParams.setPatientId(patientId);
            dataParams.setAllergyId(allergyId);
            medicalHealthcareAllergyServiceJPA.deletePatientAllergy(dataParams, authentication);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.OK);
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(CoreErrorApp.SUCCESS, null), HttpStatus.OK);
    }

}
