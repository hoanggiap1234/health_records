package com.viettel.etc.repositories;import com.viettel.etc.dto.MedicalHealthcareRegimenDTO;import com.viettel.etc.utils.TeleCareException;import com.viettel.etc.utils.TelecareUserEntity;import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;/** * Autogen class Repository Interface: * * @author toolGen * @date Tue Oct 13 09:50:36 ICT 2020 */public interface MedicalHealthcareRegimenRepository {	public ResultSelectEntity getHealthcareRegimens(MedicalHealthcareRegimenDTO itemParamsEntity, TelecareUserEntity userEntity) throws TeleCareException;	public MedicalHealthcareRegimenDTO getDetailHealthcareRegimens(MedicalHealthcareRegimenDTO itemParamsEntity);	public MedicalHealthcareRegimenDTO getDetailHealthcareRegimensAfterUpdate(MedicalHealthcareRegimenDTO itemParamsEntity);}