package com.viettel.etc.controllers;

import com.viettel.etc.dto.MedicalHealthcarePatientDetailDTO;
import com.viettel.etc.services.MedicalHealthcarePatientDetailService;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.CoreErrorApp;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Thu Sep 10 16:04:19 ICT 2020
 */
@RestController

public class MedicalHealthcarePatientDetailController {

	@Autowired
	private MedicalHealthcarePatientDetailService medicalHealthcarePatientDetailService;

	private static final Logger LOGGER = Logger.getLogger(MedicalHealthcareAllergyController.class);
	/**
	 * api get medical healthcare patient detail by history
	 *
	 * @param authentication: thong tin nguoi dung
	 * @param dataParams      params client
	 * @param historiesId
	 * @return
	 */
	@GetMapping(value = "/patient-detail/{historiesId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getMedicalHealthcarePatientDetailHistory(@AuthenticationPrincipal Authentication authentication,
																		   @PathVariable Integer historiesId,
																		   MedicalHealthcarePatientDetailDTO dataParams) {
        /*
        ==========================================================
        authenEntity: user info and role
        dataParams: danh sach bien client co the truyen len
        ==========================================================
        */
		dataParams.setHistoriesId(historiesId);
		Optional<MedicalHealthcarePatientDetailDTO> resultDataOpt = medicalHealthcarePatientDetailService.getMedicalHealthcarePatientDetailHistory(dataParams);
		if (!resultDataOpt.isPresent()) {
			return new ResponseEntity<>(new TeleCareException(ErrorApp.ERR_DATA_PATIENT_NOT_EXIST), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(FunctionCommon.responseToClient(resultDataOpt.get()), HttpStatus.OK);
	}

	/**
	 * Api grabs history due to the last 10 stats
	 *
	 * @param authentication: thong tin nguoi dung
	 * @param dataParams      params client
	 * @param patientId:      patient id
	 * @return
	 */
	@GetMapping(value = "/{patientId}/health-index/latest", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getHealthIndexLatest(@AuthenticationPrincipal Authentication authentication,
													   @PathVariable Integer patientId,
													   MedicalHealthcarePatientDetailDTO dataParams) {
        /*
        ==========================================================
        authenEntity: user info and role
        dataParams: danh sach bien client co the truyen len
        ==========================================================
        */
		dataParams.setPatientId(patientId);
		dataParams.setStartrecord(0);
		dataParams.setPagesize(10);
		try {
			ResultSelectEntity resultSelectEntity = medicalHealthcarePatientDetailService.getHealthIndexLatest(dataParams);
			return new ResponseEntity<>(FunctionCommon.responseToClient(resultSelectEntity), HttpStatus.OK);
		} catch (TeleCareException e) {
			return new ResponseEntity<>(FunctionCommon.responseToClient(e), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e);
			return new ResponseEntity<>(FnCommon.responseToClient(ErrorApp.UNKNOW_ERROR), HttpStatus.OK);
		}
	}

	/**
	 * Api grabs history due to the last 10 stats
	 *
	 * @param authentication: thong tin nguoi dung
	 * @param dataParams      params client
	 * @param patientId:      patient id
	 * @return
	 */
	@GetMapping(value = "/{patientId}/health-index", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getHealthIndexList(@AuthenticationPrincipal Authentication authentication,
													 @PathVariable Integer patientId,
													 MedicalHealthcarePatientDetailDTO dataParams) {
        /*
        ==========================================================
        authenEntity: user info and role
        dataParams: danh sach bien client co the truyen len
        ==========================================================
        */
		dataParams.setPatientId(patientId);
		try {
			ResultSelectEntity resultSelectEntity = medicalHealthcarePatientDetailService.getHealthIndexLatest(dataParams);
			return new ResponseEntity<>(FunctionCommon.responseToClient(resultSelectEntity), HttpStatus.OK);
		} catch (TeleCareException e) {
			return new ResponseEntity<>(FunctionCommon.responseToClient(e), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e);
			return new ResponseEntity<>(FnCommon.responseToClient(ErrorApp.UNKNOW_ERROR), HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/{patientId}/health-index/{detailId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteHealthIndex(@AuthenticationPrincipal Authentication authentication,
													@PathVariable Integer patientId,
													@PathVariable Integer detailId,
													MedicalHealthcarePatientDetailDTO dataParams) {
		dataParams.setPatientId(patientId);
		dataParams.setDetailId(detailId);
		try {
			medicalHealthcarePatientDetailService.delete(dataParams, authentication);
			return new ResponseEntity<>(FunctionCommon.responseToClient(CoreErrorApp.SUCCESS, null), HttpStatus.OK);
		} catch (TeleCareException e) {
			return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e);
			return new ResponseEntity<>(FnCommon.responseToClient(ErrorApp.UNKNOW_ERROR), HttpStatus.OK);
		}
	}

	@PostMapping(value = "/{patientId}/health-index", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveHealthIndex(@AuthenticationPrincipal Authentication authentication,
												  @RequestBody MedicalHealthcarePatientDetailDTO dataParams,
												  @PathVariable Integer patientId,
												  @RequestParam("indexType") Optional<String> indexType) {
		try {
			dataParams.setPatientId(patientId);
			dataParams.setIndexType(indexType.orElse(null));
			medicalHealthcarePatientDetailService.createHealthIndex(dataParams, authentication);
			return new ResponseEntity<>(FunctionCommon.responseToClient(CoreErrorApp.SUCCESS, null), HttpStatus.OK);
		} catch (TeleCareException e) {
			return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e);
			return new ResponseEntity<>(FnCommon.responseToClient(ErrorApp.UNKNOW_ERROR), HttpStatus.OK);
		}
	}

	@PostMapping(value = "/{patientId}/health-index/{detailId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateHealthIndex(@AuthenticationPrincipal Authentication authentication,
													@RequestBody MedicalHealthcarePatientDetailDTO dataParams,
													@PathVariable Integer patientId,
													@PathVariable Integer detailId,
													@RequestParam("indexType") Optional<String> indexType) {
		try {
			dataParams.setPatientId(patientId);
			dataParams.setIndexType(indexType.orElse(null));
			dataParams.setDetailId(detailId);
			medicalHealthcarePatientDetailService.updateHealthIndex(dataParams, authentication);
			return new ResponseEntity<>(FunctionCommon.responseToClient(CoreErrorApp.SUCCESS, null), HttpStatus.OK);
		} catch (TeleCareException e) {
			return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e);
			return new ResponseEntity<>(FnCommon.responseToClient(ErrorApp.UNKNOW_ERROR), HttpStatus.OK);
		}
	}
}
