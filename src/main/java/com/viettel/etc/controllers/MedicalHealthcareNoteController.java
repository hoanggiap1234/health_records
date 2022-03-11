package com.viettel.etc.controllers;import com.viettel.etc.dto.MedicalHealthcareNoteDTO;import com.viettel.etc.services.MedicalHealthcareNoteService;import com.viettel.etc.utils.FnCommon;import com.viettel.etc.utils.TeleCareException;import com.viettel.etc.xlibrary.core.constants.FunctionCommon;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.MediaType;import org.springframework.http.ResponseEntity;import org.springframework.security.core.Authentication;import org.springframework.security.core.annotation.AuthenticationPrincipal;import org.springframework.web.bind.annotation.*;import javax.validation.Valid;/** * Autogen class: * * @author ToolGen * @date Thu Aug 27 16:04:48 ICT 2020 */@RestControllerpublic class MedicalHealthcareNoteController {	@Autowired	private MedicalHealthcareNoteService medicalHealthcareNoteService;	/**	 * api lay danh sach cach ghi chu	 *	 * @param authentication: thong tin nguoi dung	 * @param dataParams      params client	 * @return	 */	@GetMapping(value = "/{patientId}/healthcare-notes", produces = MediaType.APPLICATION_JSON_VALUE)	public ResponseEntity<Object> getMedicalHealthcareNote(@AuthenticationPrincipal Authentication authentication,														   @PathVariable Integer patientId, MedicalHealthcareNoteDTO dataParams) {		dataParams.setPatientId(patientId);		Object resultObj = null;		try {			resultObj = medicalHealthcareNoteService.getMedicalHealthcareNote(dataParams, authentication);		} catch (TeleCareException e) {			return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.OK);		}		return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);	}	/**	 * api lay chi tiet ghi chu	 *	 * @param authentication: thong tin nguoi dung	 * @param dataParams      params client	 * @return	 */	@GetMapping(value = "/{patientId}/healthcare-notes/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)	public ResponseEntity<Object> getMedicalHealthcareNote(@AuthenticationPrincipal Authentication authentication,														   @PathVariable Integer patientId, @PathVariable Integer noteId,														   MedicalHealthcareNoteDTO dataParams) {		dataParams.setPatientId(patientId);		dataParams.setNoteId(noteId);		Object resultObj = null;		try {			resultObj = medicalHealthcareNoteService.getMedicalHealthcareNoteDetail(dataParams, authentication);		} catch (TeleCareException e) {			return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.OK);		}		return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);	}	/**	 * api lay chi tiet ghi chu	 *	 * @param authentication: thong tin nguoi dung	 * @param dataParams      params client	 * @return	 */	@PostMapping(value = "/{patientId}/healthcare-notes/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)	public ResponseEntity<Object> updateMedicalHealthcareNote(@AuthenticationPrincipal Authentication authentication,															  @PathVariable Integer patientId, @PathVariable Integer noteId,															  @RequestBody @Valid MedicalHealthcareNoteDTO dataParams) {		dataParams.setPatientId(patientId);		dataParams.setNoteId(noteId);		Object resultObj = null;		try {			resultObj = medicalHealthcareNoteService.updateMedicalHealthcareNote(dataParams, authentication);		} catch (TeleCareException e) {			return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.OK);		}		return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);	}	/**	 * api delete ghi chu	 *	 * @param authentication: thong tin nguoi dung	 * @param dataParams      params client	 * @return	 */	@DeleteMapping(value = "/{patientId}/healthcare-notes/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)	public ResponseEntity<Object> deleteMedicalHealthcareNote(@AuthenticationPrincipal Authentication authentication,															  @PathVariable Integer patientId, @PathVariable Integer noteId,															  MedicalHealthcareNoteDTO dataParams) {		dataParams.setPatientId(patientId);		dataParams.setNoteId(noteId);		Object resultObj = null;		try {			resultObj = medicalHealthcareNoteService.deleteMedicalHealthcareNote(dataParams, authentication);		} catch (TeleCareException e) {			return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.OK);		}		return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);	}	/**	 * api tao ghi chu	 *	 * @param authentication: thong tin nguoi dung	 * @param dataParams      params client	 * @return	 */	@PostMapping(value = "/{patientId}/healthcare-notes", produces = MediaType.APPLICATION_JSON_VALUE)	public ResponseEntity<Object> createMedicalHealthcareNote(@AuthenticationPrincipal Authentication authentication,															  @PathVariable Integer patientId, @RequestBody @Valid MedicalHealthcareNoteDTO dataParams) {		dataParams.setPatientId(patientId);		Object resultObj = null;		try {			resultObj = medicalHealthcareNoteService.createMedicalHealthcareNote(dataParams, authentication);		} catch (TeleCareException e) {			return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.OK);		}		return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);	}}