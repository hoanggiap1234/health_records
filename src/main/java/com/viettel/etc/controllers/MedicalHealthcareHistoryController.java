package com.viettel.etc.controllers;

import com.viettel.etc.dto.MedicalHealthcareHistoryDTO;
import com.viettel.etc.services.MedicalHealthcareHistoryService;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.CoreErrorApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Thu Aug 27 11:39:09 ICT 2020
 */
@RestController
public class MedicalHealthcareHistoryController {
	@Autowired
	private MedicalHealthcareHistoryService medicalHealthcareHistoryService;


	/**
	 * @param authentication: thong tin nguoi dung
	 * @param dataParams      params client
	 * @return
	 */
	@GetMapping(value = "/{patientId}/healthcare-histories", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getHealthcareHistory(@AuthenticationPrincipal Authentication authentication,
														@PathVariable Integer patientId, MedicalHealthcareHistoryDTO dataParams) throws TeleCareException {
        /*
        ==========================================================
        authenEntity: user info and role
        dataParams: danh sach bien client co the truyen len
        ==========================================================
        */
		dataParams.setPatientId(patientId);
		Object resultObj = null;
		try {
			resultObj = medicalHealthcareHistoryService.getHealthcareHistory(dataParams, authentication);
		} catch (TeleCareException e) {
			return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.OK);
		}

		return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
	}

	/**
	 * api get detail healthcare histories
	 *
	 * @param authentication: thong tin nguoi dung
	 * @param dataParams:     params client
	 * @param historiesId:      histories id
	 * @return
	 */
	@GetMapping(value = "/{patientId}/healthcare-histories/{historiesId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getDetailHealthcareHistory(@AuthenticationPrincipal Authentication authentication,
															 MedicalHealthcareHistoryDTO dataParams,
															 @PathVariable Integer patientId,
															 @PathVariable Integer historiesId,
															 @RequestParam(name = "phoneNumber") Optional<String> phoneNumber) {
        /*
        ==========================================================
        authenEntity: user info and role
        dataParams: danh sach bien client co the truyen len
        historyId: history id
        ==========================================================
        */
		dataParams.setHistoriesId(historiesId);
		dataParams.setPatientId(patientId);
		if (phoneNumber.isPresent()) {
			dataParams.setPhoneNumber(phoneNumber.get());
		}
		Optional<MedicalHealthcareHistoryDTO> resultDataOpt = medicalHealthcareHistoryService.getDetailHealthcareHistory(dataParams);
		if (!resultDataOpt.isPresent()) {
			return new ResponseEntity<>(FnCommon.responseToClient(CoreErrorApp.DATAEMTY, null), HttpStatus.OK);
		}
		return new ResponseEntity<>(FunctionCommon.responseToClient(resultDataOpt.get()), HttpStatus.OK);
	}
}
