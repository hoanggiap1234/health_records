package com.viettel.etc.controllers;

import com.google.common.base.Strings;
import com.viettel.etc.dto.MessageDTO;
import com.viettel.etc.dto.PatientDTO;
import com.viettel.etc.dto.RequestSyncHsskDTO;
import com.viettel.etc.services.HSSKService;
import com.viettel.etc.services.MessageService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Thu Aug 27 09:47:43 ICT 2020
 */
@RestController
public class HSSKController {
    private static final Logger LOGGER = Logger.getLogger(HSSKController.class);

    @Autowired private HSSKService hsskService;

    @Autowired private MessageService messageService;

    @GetMapping(value = "/sync-hssk", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataHSSK(@AuthenticationPrincipal Authentication authentication,
                                              RequestSyncHsskDTO dataParams,
                                              @RequestHeader Optional<String> lang) {
        String language = lang.orElse(Constants.VIETNAM_CODE);
        MessageDTO message = new MessageDTO();;
        StringBuilder notification = new StringBuilder();
        try {
            RequestSyncHsskDTO dto = new RequestSyncHsskDTO();
            LocalDateTime now = LocalDate.now().atStartOfDay();
            LocalDateTime defaultSyncTime = now.minusDays(30);
            dto.setToDate((dataParams==null || dataParams.getToDate()==null)? now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() : dataParams.getToDate());
            dto.setFromDate((dataParams==null || dataParams.getFromDate()==null)? defaultSyncTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() : dataParams.getFromDate());

            if(dto.getToDate() - dto.getFromDate() > 86400000L * 30) {
                throw new TeleCareException(messageService.getMessage(Constants.ERROR_SYNC_INTERVAL_OUT_OF_RANGE, language));
            }
            PatientDTO patientInfo = hsskService.getPatientInfo(authentication, language);
            if(patientInfo==null) {
                throw new TeleCareException(messageService.getMessage(Constants.ERROR_PATIENT_NOT_EXIST,language));
            }
            Integer patientId = patientInfo.getPatientId();
            if(Strings.isNullOrEmpty(patientInfo.getPid())) {
                throw new TeleCareException(messageService.getMessage(Constants.ERROR_PID_NOT_FOUND, language));
            }
            String PID = patientInfo.getPid();
            String token = FnCommon.getTokenHSSK();
            dto.setPatientId(patientId);
            dto.setToken(token);
            dto.setPID(PID);
            dto.setPhoneNumber(patientInfo.getPhoneNumber());
            int countSuccess = 0;
            notification.append("?????ng b??? th??nh c??ng:");
            try{
                hsskService.getDiseaseHistoryInfo(dto);
                notification.append(" ti???n s??? b???n th??n,");
                countSuccess++;
            } catch (Exception e) {
                e.printStackTrace();
//                notification.append(" ti???n s??? kh??ng th??nh c??ng,");
            }
            try {
                hsskService.getDiseaseHistoryFamilyInfo(dto);
                notification.append(" ti???n s??? gia ????nh,");
                countSuccess++;
            } catch (Exception e) {
                e.printStackTrace();
//                notification.append(" ti???n s??? gia ????nh kh??ng th??nh c??ng,");
            }
            try{
                hsskService.getMedicalAllergyInfo(dto);
                notification.append(" ti???n s??? d??? ???ng,");
                countSuccess++;
            } catch (Exception e) {
                e.printStackTrace();
//                notification.append(" ti???n s??? d??? ???ng kh??ng th??nh c??ng,");
            }
            try{
                hsskService.getPresurgeryInfo(dto);
                notification.append(" ti???n s??? ph???u thu???t,");
                countSuccess++;
            } catch (Exception e) {
                e.printStackTrace();
//                notification.append(" ti???n s??? ph???u thu???t kh??ng th??nh c??ng,");
            }
            try{
                hsskService.getImmunizationInfo(dto, patientInfo);
                notification.append(" ti???n s??? ti??m ch???ng,");
                countSuccess++;
            } catch (Exception e) {
                e.printStackTrace();
//                notification.append(" ti???n s??? ti??m ch???ng kh??ng th??nh c??ng,");
            }
            try{
                hsskService.getPatientHistoryInfo(dto);
                notification.append(" l???ch s??? kh??m ch???a b???nh,");
                countSuccess++;
            } catch (Exception e) {
                e.printStackTrace();
//                notification.append(" l???ch s??? kh??m ch???a b???nh kh??ng th??nh c??ng,");
            }
            notification.deleteCharAt(notification.length() - 1);
            if(countSuccess==0) {
                throw new TeleCareException(messageService.getMessage(Constants.ERROR_SYNC_DATA_HSSK, language));
            } else if(countSuccess==6) {
                message = messageService.getMessage(Constants.SUCCESS_SYNC_DATA_HSSK,language);
            } else {
                message.setDescription(notification.toString());
                message.setCode(1);
            }
        } catch (TeleCareException e) {
            LOGGER.info(e);
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(FnCommon.responseToClient(message), HttpStatus.OK);
    }

//    @GetMapping(value = "/sync-hssk-hue", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> getDataHSSKHue(@AuthenticationPrincipal Authentication authentication,
//                                              HueRequestDTO dataParams,
//                                              @RequestHeader Optional<String> lang) {
//        String language = lang.orElse(Constants.VIETNAM_CODE);
//        TelecareUserEntity telecareUserEntity = FnCommon.getTelecareUserInfo(authentication);
//        try {
//            if(telecareUserEntity==null || !telecareUserEntity.isAdmin()) {
//                return new ResponseEntity<>(FunctionCommon.responseToClient(ErrorApp.ERR_USER_NOT_PERMISSION), HttpStatus.BAD_REQUEST);
//            }
//        } catch (TeleCareException e) {
//            return new ResponseEntity<>(FunctionCommon.responseToClient(ErrorApp.ERR_USER_NOT_PERMISSION), HttpStatus.BAD_REQUEST);
//        }
//        MessageDTO message;
//        StringBuilder notification = new StringBuilder();
//        try {
//            Integer patientId = dataParams.getPatientId();
//            String PID = dataParams.getPid();
//            String token = FnCommon.getTokenHSSK();
//            int countSuccess = 0;
//            notification.append("?????ng b??? th??ng tin");
//            try{
//                hsskService.getDiseaseHistoryInfo(PID, patientId, token);
//                notification.append(" ti???n s??? th??nh c??ng,");
//                countSuccess++;
//            } catch (Exception e) {
//                e.printStackTrace();
//                notification.append(" ti???n s??? kh??ng th??nh c??ng,");
//            }
//            try {
//                hsskService.getDiseaseHistoryFamilyInfo(PID, patientId, token);
//                notification.append(" ti???n s??? gia ????nh th??nh c??ng,");
//                countSuccess++;
//            } catch (Exception e) {
//                e.printStackTrace();
//                notification.append(" ti???n s??? gia ????nh kh??ng th??nh c??ng,");
//            }
//            try{
//                hsskService.getMedicalAllergyInfo(PID, patientId, token);
//                notification.append(" ti???n s??? d??? ???ng th??nh c??ng,");
//                countSuccess++;
//            } catch (Exception e) {
//                e.printStackTrace();
//                notification.append(" ti???n s??? d??? ???ng kh??ng th??nh c??ng,");
//            }
//            try{
//                hsskService.getPresurgeryInfo(PID, patientId, token);
//                notification.append(" ti???n s??? ph???u thu???t th??nh c??ng,");
//                countSuccess++;
//            } catch (Exception e) {
//                e.printStackTrace();
//                notification.append(" ti???n s??? ph???u thu???t kh??ng th??nh c??ng,");
//            }
//            try{
//                hsskService.getImmunizationInfo(PID, patientId, token);
//                notification.append(" ti???n s??? ti??m ch???ng th??nh c??ng,");
//                countSuccess++;
//            } catch (Exception e) {
//                e.printStackTrace();
//                notification.append(" ti???n s??? ti??m ch???ng kh??ng th??nh c??ng,");
//            }
//            try{
//                hsskService.getPatientHistoryInfo(PID, patientId, token);
//                notification.append(" l???ch s??? kh??m ch???a b???nh th??nh c??ng,");
//                countSuccess++;
//            } catch (Exception e) {
//                e.printStackTrace();
//                notification.append(" l???ch s??? kh??m ch???a b???nh kh??ng th??nh c??ng,");
//            }
//            notification.deleteCharAt(notification.length() - 1);
//            if(countSuccess==0) {
//                throw new TeleCareException(messageService.getMessage(Constants.ERROR_SYNC_DATA_HSSK, language));
//            } else {
//                message = messageService.getMessage(Constants.SUCCESS_SYNC_DATA_HSSK,language);
//            }
//        } catch (TeleCareException e) {
//            LOGGER.info(e);
//            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(FnCommon.responseToClient(message, notification.toString()), HttpStatus.OK);
//
//    }
}
