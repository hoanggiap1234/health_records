package com.viettel.etc.services.impl;

import com.viettel.etc.controllers.MedicalHealthcareAllergyController;
import com.viettel.etc.dto.IndexType;
import com.viettel.etc.dto.MedicalHealthcarePatientDetailDTO;
import com.viettel.etc.dto.MedicalHealthcarePatientSummaryDTO;
import com.viettel.etc.repositories.MedicalHealthcarePatientDetailRepository;
import com.viettel.etc.repositories.tables.MedicalHealthcarePatientDetailRepositoryJPA;
import com.viettel.etc.repositories.tables.MedicalHealthcarePatientSummaryRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientDetailEntity;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientSummaryEntity;
import com.viettel.etc.services.MedicalHealthcarePatientDetailService;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Thu Sep 10 16:04:21 ICT 2020
 */
@Service
public class MedicalHealthcarePatientDetailServiceImpl implements MedicalHealthcarePatientDetailService {

	private static final Logger LOGGER = Logger.getLogger(MedicalHealthcareAllergyController.class);

	@Autowired
	MedicalHealthcarePatientSummaryRepositoryJPA medicalHealthcarePatientSummaryRepositoryJPA;
	@Autowired
	private MedicalHealthcarePatientDetailRepository medicalHealthcarePatientDetailRepository;
	@Autowired
	private MedicalHealthcarePatientDetailRepositoryJPA medicalHealthcarePatientDetailRepositoryJPA;

	/**
	 * api get medical healthcare patient detail by history
	 *
	 * @param itemParamsEntity params client
	 * @return
	 */
	@Override
	public Optional<MedicalHealthcarePatientDetailDTO> getMedicalHealthcarePatientDetailHistory(MedicalHealthcarePatientDetailDTO itemParamsEntity) {
        /*
        ==========================================================
        itemParamsEntity: params nguoi dung truyen len
        ==========================================================
        */
		return medicalHealthcarePatientDetailRepository.getMedicalHealthcarePatientDetailHistory(itemParamsEntity);
	}

	@Override
	public ResultSelectEntity getHealthIndexLatest(MedicalHealthcarePatientDetailDTO dataParams) throws TeleCareException {
		return medicalHealthcarePatientDetailRepository.getHealthIndexLatest(dataParams);
	}

	@Override
	public MedicalHealthcarePatientDetailEntity delete(MedicalHealthcarePatientDetailDTO dataParams, Authentication authentication) throws TeleCareException {
		Optional<MedicalHealthcarePatientDetailEntity> resultEntityOpt = medicalHealthcarePatientDetailRepositoryJPA.findById(dataParams.getDetailId());
		if (!resultEntityOpt.isPresent()) {
			FnCommon.throwsErrorApp(ErrorApp.ERR_DATA_PATIENT_NOT_EXIST);
		}

		MedicalHealthcarePatientDetailEntity entity = resultEntityOpt.get();
		if (dataParams.getIndexType() != null) {
			List<String> indexTypes = Arrays.asList("bloodSugar", "spo2Score", "bloodPressure",
					"heartBeat", "temperature", "height", "weight", "bmi", "bloodAbo", "bloodRh", "diseases");
			if (!indexTypes.contains(dataParams.getIndexType())) {
				FnCommon.throwsErrorApp(ErrorApp.ERROR_TYPE_INDEX_NOT_EXIST);
			}
			if (dataParams.getIndexType().equals(IndexType.BLOOD_PRESSURE.getColumn())) {
				entity.setBloodPressureMax(null);
				entity.setBloodPressureMin(null);
			} else {
				try {
					BeanUtils.setProperty(entity, dataParams.getIndexType(), null);
				} catch (Exception e) {
					LOGGER.info(e);
					FnCommon.throwsErrorApp(ErrorApp.UNKNOW_ERROR);
				}
			}
		} else {
			entity.setIsDelete(true);
		}

		medicalHealthcarePatientDetailRepositoryJPA.save(entity);

		return entity;
	}

	@Override
	public MedicalHealthcarePatientDetailEntity createHealthIndex(MedicalHealthcarePatientDetailDTO dataParams, Authentication authentication) throws Exception {
		if (dataParams.getIndexType() != null) {
			this.validateInputWithIndexType(dataParams);
		}

		MedicalHealthcarePatientDetailEntity entity = new MedicalHealthcarePatientDetailEntity();
		FnCommon.copyProperties(dataParams, entity);
		if (dataParams.getResultDate() != null) {
			entity.setResultDate(Objects.isNull(dataParams.getResultDate()) || !FnCommon.isDate(dataParams.getResultDate()) ? new java.util.Date() : new java.util.Date((dataParams.getResultDate())));
		}
		entity.setIsDelete(false);
		entity.setIsActive(true);
		entity.setIsSync(false);
		entity.setCreateDate(Date.valueOf(LocalDate.now()));
		entity.setUpdateDate(Date.valueOf(LocalDate.now()));
		String userId = FnCommon.getUserIdLogin(authentication);
		if (Objects.nonNull(userId) && userId.matches("[0-9]+")) {
			entity.setCreateUserId(Integer.valueOf(userId));
			entity.setUpdateUserId(Integer.valueOf(userId));
		}

		medicalHealthcarePatientDetailRepositoryJPA.save(entity);
		MedicalHealthcarePatientSummaryDTO detailDTO = new MedicalHealthcarePatientSummaryDTO();
		FnCommon.copyProperties(entity, detailDTO);
		if (entity.getBloodPressureMax() != null && entity.getBloodPressureMin() != null) {
			detailDTO.setBloodPressure(entity.getBloodPressureMax() + "/" + entity.getBloodPressureMin());
		}
		createPatientSummary(detailDTO);

		return entity;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void createPatientSummary(MedicalHealthcarePatientSummaryDTO dto) throws Exception {
		List<MedicalHealthcarePatientSummaryEntity> updates = new ArrayList<>();

		List<MedicalHealthcarePatientSummaryEntity> entities = medicalHealthcarePatientSummaryRepositoryJPA.findByPatientId(dto.getPatientId());

		Field[] dtoFields = dto.getClass().getDeclaredFields();
		List<String> fieldUpdate = Arrays.asList("bloodSugar", "spo2Score", "bloodPressure", "heartBeat", "temperature", "height", "weight", "bmi", "bloodAbo", "bloodRh", "diseases");
		for (Field dtoField : dtoFields) {
			dtoField.setAccessible(true);
			if (!fieldUpdate.contains(dtoField.getName()) || dtoField.get(dto) == null) {
				continue;
			}
			MedicalHealthcarePatientSummaryEntity entityNew = new MedicalHealthcarePatientSummaryEntity();
			for (MedicalHealthcarePatientSummaryEntity entity : entities) {
				Field entityField = entity.getClass().getDeclaredField(dtoField.getName());
				entityField.setAccessible(true);
				if (entityField.get(entity) != null) {
					entityNew = entity;
					break;
				}
			}
			BeanUtils.setProperty(entityNew, dtoField.getName(), dtoField.get(dto));
			BeanUtils.setProperty(entityNew, "resultDate", dto.getResultDate());
			BeanUtils.setProperty(entityNew, "patientId", dto.getPatientId());

			updates.add(entityNew);
		}


		if (!updates.isEmpty()) {
			medicalHealthcarePatientSummaryRepositoryJPA.saveAll(updates);
		}
	}

	@Override
	public MedicalHealthcarePatientDetailEntity updateHealthIndex(MedicalHealthcarePatientDetailDTO dataParams, Authentication authentication) throws Exception {
		if (dataParams.getIndexType() != null) {
			this.validateInputWithIndexType(dataParams);
		}
		Optional<MedicalHealthcarePatientDetailEntity> entityOpt = medicalHealthcarePatientDetailRepositoryJPA.findById(dataParams.getDetailId());
		if (!entityOpt.isPresent()) {
			FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
		}
		MedicalHealthcarePatientDetailEntity entity = entityOpt.get();
		FnCommon.copyProperties(dataParams, entity);

		if (Objects.nonNull(dataParams.getResultDate())) {
			entity.setResultDate(!FnCommon.isDate(dataParams.getResultDate()) ? Date.valueOf(LocalDate.now()) : Date.valueOf(FnCommon.convertToLocalDate(dataParams.getResultDate())));
		}
		entity.setUpdateDate(Date.valueOf(LocalDate.now()));
		String userId = FnCommon.getUserIdLogin(authentication);
		if (Objects.nonNull(userId) && userId.matches("[0-9]+")) {
			entity.setUpdateUserId(Integer.valueOf(userId));
		}

		medicalHealthcarePatientDetailRepositoryJPA.save(entity);
		MedicalHealthcarePatientSummaryDTO detailDTO = new MedicalHealthcarePatientSummaryDTO();
		FnCommon.copyProperties(entity, detailDTO);

		createPatientSummary(detailDTO);

		return entity;
	}

	private void validateInputWithIndexType(MedicalHealthcarePatientDetailDTO dataParams) throws TeleCareException {
		IndexType indexType = IndexType.valueOfIgnoreCase(dataParams.getIndexType());
		if (IndexType.BLOOD_SUGAR.equals(indexType) && Objects.isNull(dataParams.getBloodSugar())) {
			FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
		}
		if (IndexType.SPO2_SCORE.equals(indexType) && Objects.isNull(dataParams.getSpo2Score())) {
			FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
		}
		if (IndexType.BLOOD_PRESSURE.equals(indexType) && (Objects.isNull(dataParams.getBloodPressureMin()) || Objects.isNull(dataParams.getBloodPressureMax()))) {
			FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
		}
		if (IndexType.HEART_BEAT.equals(indexType) && Objects.isNull(dataParams.getHeartBeat())) {
			FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
		}
		if (IndexType.TEMPERATURE.equals(indexType) && Objects.isNull(dataParams.getTemperature())) {
			FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
		}
		if (IndexType.HEIGHT.equals(indexType) && Objects.isNull(dataParams.getHeight())) {
			FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
		}
		if (IndexType.WEIGHT.equals(indexType) && Objects.isNull(dataParams.getWeight())) {
			FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
		}
		if (IndexType.BMI.equals(indexType) && Objects.isNull(dataParams.getBmi())) {
			FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
		}
	}
}
