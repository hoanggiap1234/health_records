package com.viettel.etc.controllers;

import com.viettel.etc.dto.MedicalHealthcarePrehistoricDTO;
import com.viettel.etc.services.MedicalHealthcarePrehistoricService;
import com.viettel.etc.services.tables.MedicalHealthcarePrehistoricServiceJPA;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Thu Sep 17 17:27:42 ICT 2020
 */
@RestController
public class MedicalHealthcarePrehistoricController {

    @Autowired
    private MedicalHealthcarePrehistoricService medicalHealthcarePrehistoricService;

    @Autowired
    private MedicalHealthcarePrehistoricServiceJPA medicalHealthcarePrehistoricServiceJPA;

    private static final Logger LOGGER = Logger.getLogger(MedicalHealthcareAllergyController.class);
    /**
     * api get patient prehistoric
     *
     * @param authentication
     * @param dataParams
     * @param patientId      là id bệnh nhân
     * @return
     */
    @GetMapping(value = "/{patientId}/prehistoric", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPatientPrehistoric(@AuthenticationPrincipal Authentication authentication,
                                                        MedicalHealthcarePrehistoricDTO dataParams,
                                                        @PathVariable Integer patientId) {
        Object resultObj = null;
        try {
            resultObj = medicalHealthcarePrehistoricService.getPatientPrehistoric(dataParams, authentication, patientId);
        } catch (TeleCareException e) {
            LOGGER.info(e);
            e.printStackTrace();
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * api create patient prehistoric
     *
     * @param authentication
     * @param dataParams
     * @param patientId      là id bệnh nhân
     * @return
     */
    @PostMapping(value = "/{patientId}/prehistoric", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createPatientPrehistoric(@AuthenticationPrincipal Authentication authentication,
                                                           @RequestBody @Valid MedicalHealthcarePrehistoricDTO dataParams,
                                                           @PathVariable Integer patientId) {
        Object resultObj = null;
        try {
            resultObj = medicalHealthcarePrehistoricServiceJPA.createPatientPrehistoric(dataParams, authentication, patientId);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * api update patient prehistoric
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @PostMapping(value = "{patientId}/prehistoric/{prehistoricId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatePatientPrehistoric(@AuthenticationPrincipal Authentication authentication,
                                                           @PathVariable Integer prehistoricId,
                                                           @PathVariable Integer patientId,
                                                           @RequestBody MedicalHealthcarePrehistoricDTO dataParams) {
        /*
        ==========================================================
        authenEntity: user info and role
        dataParams: danh sach bien client co the truyen len
        ==========================================================
        */
        Object resultObj = null;
        try {
            dataParams.setPrehistoricId(prehistoricId);
            resultObj = medicalHealthcarePrehistoricServiceJPA.updatePatientPrehistoric(dataParams, authentication);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * api delete patient prehistoric
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @DeleteMapping(value = "{patientId}/prehistoric/{prehistoricId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deletePatientPrehistoric(@AuthenticationPrincipal Authentication authentication,
                                                           @PathVariable Integer prehistoricId,
                                                           @PathVariable Integer patientId,
                                                           MedicalHealthcarePrehistoricDTO dataParams) {
        try {
            dataParams.setPrehistoricId(prehistoricId);
            medicalHealthcarePrehistoricServiceJPA.deletePatientPrehistoric(dataParams, authentication);
            return new ResponseEntity<>(FnCommon.responseToClient("Xóa thành công"), HttpStatus.OK);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }
    }
}
