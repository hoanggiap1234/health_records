package com.viettel.etc.controllers;

import com.viettel.etc.dto.MedicalHealthcareServiceDTO;
import com.viettel.etc.services.MedicalHealthcareServiceService;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.CoreErrorApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.Optional;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Thu Aug 27 15:08:49 ICT 2020
 */
@RestController
public class MedicalHealthcareServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalHealthcareServiceController.class);

    @Autowired
    private MedicalHealthcareServiceService medicalHealthcareServiceService;


    /**
     *
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @GetMapping(value = "/{patientId}/healthcare-services", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getHealthcareService(@AuthenticationPrincipal Authentication authentication,
                                              @PathVariable Integer patientId, MedicalHealthcareServiceDTO dataParams) {
        /*
        ==========================================================
        authenEntity: user info and role
        dataParams: danh sach bien client co the truyen len
        ==========================================================
        */
        dataParams.setPatientId(patientId);
        Object resultObj = null;
        try {
            resultObj = medicalHealthcareServiceService.getHealthcareService(dataParams, authentication);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * api get detail Healthcare Service
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams:     params client
     * @param serviceId:  service id
     * @return
     */
    @GetMapping(value = "/{patientId}/healthcare-services/{serviceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDetailHealthcareService(@AuthenticationPrincipal Authentication authentication,
                                                             MedicalHealthcareServiceDTO dataParams,
                                                             @PathVariable Integer serviceId) {
        /*
        ==========================================================
        authenEntity: user info and role
        dataParams: danh sach bien client co the truyen len
        patientId: patient id
        serviceId: service id
        ==========================================================
        */
        LOGGER.debug(MessageFormat.format("get detail healthcare service with serviceId: {1}", serviceId));
        dataParams.setServiceId(serviceId);

        Optional<MedicalHealthcareServiceDTO> resultDataOpt = medicalHealthcareServiceService.getDetailHealthcareService(dataParams);
        if (!resultDataOpt.isPresent()) {
            return new ResponseEntity<>(FunctionCommon.responseToClient(CoreErrorApp.DATAEMTY, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultDataOpt.get()), HttpStatus.OK);
    }

    /**
     * api get detail Healthcare Service
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams:     params client
     * @param historyId:  history id
     * @param serviceId:  service id
     * @return
     */
    @GetMapping(value = "/healthcare-services/{serviceId}/{historyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDetailHealthcareServiceHistory(@AuthenticationPrincipal Authentication authentication,
                                                             MedicalHealthcareServiceDTO dataParams,
                                                             @PathVariable Integer serviceId,
                                                             @PathVariable Integer historyId) {
        /*
        ==========================================================
        authenEntity: user info and role
        dataParams: danh sach bien client co the truyen len
        patientId: patient id
        serviceId: service id
        ==========================================================
        */
        LOGGER.debug(MessageFormat.format("get detail healthcare service with historyId: {0} and serviceId: {1}", historyId, serviceId));
        dataParams.setServiceId(serviceId);
        dataParams.setHistoriesId(historyId);

        Optional<MedicalHealthcareServiceDTO> resultDataOpt = medicalHealthcareServiceService.getDetailHealthcareServiceHistory(dataParams);
        if (!resultDataOpt.isPresent()) {
            return new ResponseEntity<>(FunctionCommon.responseToClient(CoreErrorApp.DATAEMTY, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultDataOpt.get()), HttpStatus.OK);
    }
}
