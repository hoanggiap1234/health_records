package com.viettel.etc.controllers;

import com.viettel.etc.dto.PatientsIdentificationDTO;
import com.viettel.etc.services.PatientsIdentificationService;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
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
 * @date Mon Sep 28 17:02:36 ICT 2020
 */
@RestController
public class PatientsIdentificationController {

    @Autowired
    private PatientsIdentificationService patientsIdentificationService;


    /**
     * validate health insurance exits
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @GetMapping(value = "/health-insurances/{healthInsuranceNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> autofillHealthInsuranceInfo(@AuthenticationPrincipal Authentication authentication,
                                                              @PathVariable String healthInsuranceNumber, PatientsIdentificationDTO dataParams) {
        /*
        ==========================================================
        authenEntity: user info and role
        dataParams: danh sach bien client co the truyen len
        ==========================================================
        */
        dataParams.setHealthinsuranceNumber(healthInsuranceNumber);
        try {
            Optional<PatientsIdentificationDTO> resultDataOpt = patientsIdentificationService.autofillHealthInsuranceInfo(dataParams);
            if (resultDataOpt.isPresent()) {
                return new ResponseEntity<>(FunctionCommon.responseToClient(resultDataOpt.get()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(FunctionCommon.responseToClient(null), HttpStatus.OK);
            }
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FunctionCommon.responseToClient(e), HttpStatus.OK);
        }
    }
}
