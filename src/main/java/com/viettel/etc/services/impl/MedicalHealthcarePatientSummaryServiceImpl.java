package com.viettel.etc.services.impl;import com.viettel.etc.controllers.MedicalHealthcareAllergyController;import com.viettel.etc.dto.HealthcareOverviewDto;import com.viettel.etc.dto.MedicalHealthcarePatientSummaryDTO;import com.viettel.etc.repositories.MedicalHealthcarePatientSummaryRepository;import com.viettel.etc.repositories.tables.MedicalHealthcarePatientDetailRepositoryJPA;import com.viettel.etc.repositories.tables.MedicalHealthcarePatientSummaryRepositoryJPA;import com.viettel.etc.repositories.tables.PatientsRepositoryJPA;import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientDetailEntity;import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientSummaryEntity;import com.viettel.etc.repositories.tables.entities.PatientsEntity;import com.viettel.etc.services.MedicalHealthcarePatientSummaryService;import com.viettel.etc.utils.ErrorApp;import com.viettel.etc.utils.FnCommon;import com.viettel.etc.utils.TeleCareException;import org.apache.commons.beanutils.BeanUtils;import org.apache.log4j.Logger;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.security.core.Authentication;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Propagation;import org.springframework.transaction.annotation.Transactional;import java.lang.reflect.Field;import java.util.ArrayList;import java.util.Arrays;import java.util.HashMap;import java.util.List;/** * Autogen class: * * @author ToolGen * @date Mon Sep 14 08:36:29 ICT 2020 */@Servicepublic class MedicalHealthcarePatientSummaryServiceImpl implements MedicalHealthcarePatientSummaryService {	private static final Logger LOGGER = Logger.getLogger(MedicalHealthcareAllergyController.class);	@Autowired	PatientsRepositoryJPA patientsRepositoryJPA;	@Autowired	MedicalHealthcarePatientSummaryRepositoryJPA medicalHealthcarePatientSummaryRepositoryJPA;	@Autowired	MedicalHealthcarePatientDetailRepositoryJPA medicalHealthcarePatientDetailRepositoryJPA;	@Autowired	private MedicalHealthcarePatientSummaryRepository medicalHealthcarePatientSummaryRepository;	/**	 * api get patient overview	 *	 * @param itemParamsEntity params client	 * @return	 */	@Override	public Object getPatientOverview(MedicalHealthcarePatientSummaryDTO itemParamsEntity, Integer patientId) throws TeleCareException {        /*        ==========================================================        itemParamsEntity: params nguoi dung truyen len        ==========================================================        */		Object dataResult = medicalHealthcarePatientSummaryRepository.getPatientOverview(itemParamsEntity);		return dataResult;	}	/**	 * @param dto	 * @param authentication	 * @return	 * @throws TeleCareException	 * @throws Exception	 * @author hungnd	 */	@Override	@Transactional(rollbackFor = Exception.class)	public Object createPatientSummary(MedicalHealthcarePatientSummaryDTO dto, Authentication authentication) throws TeleCareException, Exception {		PatientsEntity patient = patientsRepositoryJPA.findById(dto.getPatientId()).orElse(null);		if (patient == null) {			throw new TeleCareException(ErrorApp.ERR_DATA_PATIENT_NOT_EXIST);		}		// TODO : get create user from authentication		dto.setBmi((dto.getWeight() != null && dto.getHeight() != null && dto.getWeight() != 0 && dto.getHeight() != 0)				? dto.getWeight() / Math.pow(dto.getHeight() / 100, 2) : null);		List<MedicalHealthcarePatientSummaryEntity> updates = new ArrayList<>();		List<MedicalHealthcarePatientSummaryEntity> entities = medicalHealthcarePatientSummaryRepositoryJPA.findByPatientId(dto.getPatientId());		Field[] dtoFields = dto.getClass().getDeclaredFields();		List<String> fieldUpdate = Arrays.asList("bloodSugar", "spo2Score", "bloodPressure", "heartBeat", "temperature", "height", "weight", "bmi", "bloodAbo", "bloodRh", "diseases");		for (Field dtoField : dtoFields) {			dtoField.setAccessible(true);			if (!fieldUpdate.contains(dtoField.getName()) || dtoField.get(dto) == null) {				continue;			}			MedicalHealthcarePatientSummaryEntity entityNew = new MedicalHealthcarePatientSummaryEntity();			for (MedicalHealthcarePatientSummaryEntity entity : entities) {				Field entityField = entity.getClass().getDeclaredField(dtoField.getName());				entityField.setAccessible(true);				if (entityField.get(entity) != null) {					entityNew = entity;					break;				}			}			BeanUtils.setProperty(entityNew, dtoField.getName(), dtoField.get(dto));			BeanUtils.setProperty(entityNew, "resultDate", dto.getResultDate());			BeanUtils.setProperty(entityNew, "patientId", dto.getPatientId());			updates.add(entityNew);		}		if (!updates.isEmpty()) {			medicalHealthcarePatientSummaryRepositoryJPA.saveAll(updates);			insertToTableDetail(dto);		}		HashMap<String, HealthcareOverviewDto.HealthcareIndex> mapIndex = new HashMap<>();		Field[] fields = MedicalHealthcarePatientSummaryEntity.class.getDeclaredFields();		for (MedicalHealthcarePatientSummaryEntity index : updates) {			for (Field field : fields) {				field.setAccessible(true);				try {					if (fieldUpdate.contains(field.getName()) && field.get(index) != null) {						mapIndex.put(field.getName(), new HealthcareOverviewDto.HealthcareIndex(field.get(index), index.getResultDate()));					}				} catch (Exception e) {					LOGGER.info(e);				}			}		}		HealthcareOverviewDto result = new HealthcareOverviewDto();		HealthcareOverviewDto.Patient patient1 = new HealthcareOverviewDto.Patient();		patient1.setId(patient.getPatientId());		patient1.setGenderId(patient.getGenderId());		patient1.setFullname(patient.getFullname());		patient1.setPhoneNumber(patient.getPhoneNumber());		patient1.setBirthday(patient.getBirthday());		result.setPatient(patient1);		result.setHealthcareIndex(mapIndex);		return result;	}	@Override	@Transactional(rollbackFor = Exception.class)	public Object createPatientSummaryRest(MedicalHealthcarePatientSummaryDTO dto, Authentication authentication) throws TeleCareException, Exception {		List<MedicalHealthcarePatientSummaryEntity> updates = new ArrayList<>();		List<MedicalHealthcarePatientSummaryEntity> entities = medicalHealthcarePatientSummaryRepositoryJPA.findByPatientId(dto.getPatientId());		dto.setBmi((dto.getWeight() != null && dto.getHeight() != null && dto.getWeight() != 0 && dto.getHeight() != 0)				? dto.getWeight() / Math.pow(dto.getHeight() / 100, 2) : null);		Field[] dtoFields = dto.getClass().getDeclaredFields();		List<String> fieldUpdate = Arrays.asList("bloodSugar", "spo2Score", "bloodPressure", "heartBeat", "temperature", "height", "weight", "bmi", "bloodAbo", "bloodRh", "diseases");		for (Field dtoField : dtoFields) {			dtoField.setAccessible(true);			if (!fieldUpdate.contains(dtoField.getName()) || dtoField.get(dto) == null) {				continue;			}			MedicalHealthcarePatientSummaryEntity entityNew = new MedicalHealthcarePatientSummaryEntity();			for (MedicalHealthcarePatientSummaryEntity entity : entities) {				Field entityField = entity.getClass().getDeclaredField(dtoField.getName());				entityField.setAccessible(true);				if (entityField.get(entity) != null) {					entityNew = entity;					break;				}			}			BeanUtils.setProperty(entityNew, dtoField.getName(), dtoField.get(dto));			BeanUtils.setProperty(entityNew, "resultDate", dto.getResultDate());			BeanUtils.setProperty(entityNew, "patientId", dto.getPatientId());			updates.add(entityNew);		}		if (!updates.isEmpty()) {			medicalHealthcarePatientSummaryRepositoryJPA.saveAll(updates);			insertToTableDetail(dto);		}		HashMap<String, HealthcareOverviewDto.HealthcareIndex> mapIndex = new HashMap<>();		Field[] fields = MedicalHealthcarePatientSummaryEntity.class.getDeclaredFields();		for (MedicalHealthcarePatientSummaryEntity index : updates) {			for (Field field : fields) {				field.setAccessible(true);				try {					if (fieldUpdate.contains(field.getName()) && field.get(index) != null) {						mapIndex.put(field.getName(), new HealthcareOverviewDto.HealthcareIndex(field.get(index), index.getResultDate()));					}				} catch (Exception e) {					LOGGER.info(e);				}			}		}		HealthcareOverviewDto result = new HealthcareOverviewDto();		HealthcareOverviewDto.Patient patient1 = new HealthcareOverviewDto.Patient();		patient1.setId(dto.getPatientId());		patient1.setGenderId(dto.getGenderId());		patient1.setFullname(dto.getPatientName());		patient1.setPhoneNumber(dto.getPhoneNumber());		patient1.setBirthday(dto.getBirthday());		result.setPatient(patient1);		result.setHealthcareIndex(mapIndex);		return result;	}	/**	 * Insert to table detail	 *	 * @param dto	 * @throws TeleCareException	 * @author hungnd	 */	@Transactional(propagation = Propagation.MANDATORY)	void insertToTableDetail(MedicalHealthcarePatientSummaryDTO dto) throws TeleCareException {		MedicalHealthcarePatientDetailEntity entity = new MedicalHealthcarePatientDetailEntity();		dto.setSummaryId(null);		FnCommon.copyProperties(dto, entity);		if (entity.getClass().getDeclaredFields().length == FnCommon.getNullPropertyNames(entity).length) {			FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);		}		medicalHealthcarePatientDetailRepositoryJPA.save(entity);	}}