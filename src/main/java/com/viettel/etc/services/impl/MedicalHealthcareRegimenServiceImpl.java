package com.viettel.etc.services.impl;import com.viettel.etc.dto.MedicalHealthcareRegimenDTO;import com.viettel.etc.dto.MedicalHealthcareRegimenDetailDTO;import com.viettel.etc.repositories.MedicalHealthcareRegimenRepository;import com.viettel.etc.services.MedicalHealthcareRegimenDetailService;import com.viettel.etc.services.MedicalHealthcareRegimenService;import com.viettel.etc.utils.ErrorApp;import com.viettel.etc.utils.FnCommon;import com.viettel.etc.utils.TeleCareException;import com.viettel.etc.utils.TelecareUserEntity;import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.security.core.Authentication;import org.springframework.stereotype.Service;import java.util.List;import java.util.Objects;/** * Autogen class: * * @author ToolGen * @date Tue Oct 13 09:50:36 ICT 2020 */@Servicepublic class MedicalHealthcareRegimenServiceImpl implements MedicalHealthcareRegimenService {	@Autowired	private MedicalHealthcareRegimenRepository medicalHealthcareRegimenRepository;	@Autowired	private MedicalHealthcareRegimenDetailService medicalHealthcareRegimenDetailService;	/**	 * @param itemParamsEntity params client	 * @return	 */	@Override	public Object getHealthcareRegimens(MedicalHealthcareRegimenDTO itemParamsEntity, Authentication authentication) throws TeleCareException {		TelecareUserEntity userEntity = FnCommon.getTelecareUserInfo(authentication);		ResultSelectEntity dataResult = medicalHealthcareRegimenRepository.getHealthcareRegimens(itemParamsEntity, userEntity);		return dataResult;	}	@Override	public MedicalHealthcareRegimenDTO getDetailHealthcareRegimens(MedicalHealthcareRegimenDTO itemParamsEntity) throws TeleCareException {		MedicalHealthcareRegimenDTO result = medicalHealthcareRegimenRepository.getDetailHealthcareRegimens(itemParamsEntity);		if (result == null) {			throw new TeleCareException(ErrorApp.ERR_MEDICAL_HEALTHCARE_REGIMEN_NOT_EXIST);		}		List<MedicalHealthcareRegimenDetailDTO> regimens = medicalHealthcareRegimenDetailService.getMedicalHealthcareRegimenDetail(itemParamsEntity.getPatientRegimenId());		result.setRegimens(regimens);		return result;	}	@Override	public MedicalHealthcareRegimenDTO getDetailHealthcareRegimensAfterUpdate(MedicalHealthcareRegimenDTO itemParamsEntity) throws TeleCareException {		MedicalHealthcareRegimenDTO result = medicalHealthcareRegimenRepository.getDetailHealthcareRegimensAfterUpdate(itemParamsEntity);		List<MedicalHealthcareRegimenDetailDTO> regimens = medicalHealthcareRegimenDetailService.getMedicalHealthcareRegimenDetail(itemParamsEntity.getPatientRegimenId());		if(Objects.isNull(result)) {			FnCommon.throwsErrorApp(ErrorApp.ERR_MEDICAL_HEALTHCARE_REGIMEN_NOT_EXIST);		}		result.setRegimens(regimens);		return result;	}}