package com.viettel.etc.controllers;

import com.viettel.etc.dto.MedicalHealthcareRegimenDTO;
import com.viettel.etc.services.MedicalHealthcareRegimenService;
import com.viettel.etc.utils.FnCommon;
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

@RestController
public class MedicalHealthcareRegimenDetailController {
    @Autowired
    private MedicalHealthcareRegimenService medicalHealthcareRegimenService;

    @GetMapping(
            value = "/{patientId}/healthcare-regimens/{patientRegimenId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getVaccinationsHistory(
            @AuthenticationPrincipal Authentication authentication,
            @PathVariable Integer patientId,
            @PathVariable Integer patientRegimenId,
            MedicalHealthcareRegimenDTO medicalHealthcareRegimenDTO) {
        Object resultObj = null;
        try {
            resultObj = medicalHealthcareRegimenService.getDetailHealthcareRegimens(medicalHealthcareRegimenDTO);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
