package com.viettel.etc.services.impl;

import com.viettel.etc.dto.MedicalHealthcareAllergyDTO;
import com.viettel.etc.dto.MedicalHealthcarePrehistoricDTO;
import com.viettel.etc.dto.MedicalHealthcarePresurgeryDTO;
import com.viettel.etc.repositories.MedicalHealthcareAllergyRepository;
import com.viettel.etc.repositories.MedicalHealthcarePrehistoricRepository;
import com.viettel.etc.repositories.MedicalHealthcarePresurgeryRepository;
import com.viettel.etc.repositories.tables.CatsHealthfacilitiesRepositoryJPA;
import com.viettel.etc.repositories.tables.MedicalHealthcareAllergyRepositoryJPA;
import com.viettel.etc.repositories.tables.MedicalHealthcarePrehistoricRepositoryJPA;
import com.viettel.etc.repositories.tables.MedicalHealthcarePresurgeryRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.PatientsEntity;
import com.viettel.etc.services.MedicalHealthcarePrehistoricService;
import com.viettel.etc.services.tables.CatsRelationshipsServiceJPA;
import com.viettel.etc.services.tables.PatientsServiceJPA;
import com.viettel.etc.utils.TeleCareException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Thu Sep 17 17:27:43 ICT 2020
 */
@Service
public class MedicalHealthcarePrehistoricServiceImpl implements MedicalHealthcarePrehistoricService {

	@Autowired
	private MedicalHealthcarePrehistoricRepository medicalHealthcarePrehistoricRepository;

	@Autowired
	private MedicalHealthcarePrehistoricRepositoryJPA medicalHealthcarePrehistoricRepositoryJPA;

	@Autowired
	private MedicalHealthcareAllergyRepositoryJPA medicalHealthcareAllergyRepositoryJPA;

	@Autowired
	private MedicalHealthcarePresurgeryRepositoryJPA medicalHealthcarePresurgeryRepositoryJPA;

	@Autowired
	private MedicalHealthcareAllergyRepository medicalHealthcareAllergyRepository;

	@Autowired
	private MedicalHealthcarePresurgeryRepository medicalHealthcarePresurgeryRepository;

	@Autowired
	private PatientsServiceJPA patientsServiceJPA;

	@Autowired
	private CatsRelationshipsServiceJPA catsRelationshipsServiceJPA;

	@Autowired
	private CatsHealthfacilitiesRepositoryJPA catsHealthfacilitiesRepositoryJPA;

	@Override
	public Object getPatientPrehistoric(MedicalHealthcarePrehistoricDTO itemParamsEntity, Authentication authentication, Integer patientId) throws TeleCareException {
		// phuc vu luong demo
//        Integer currentPatientId = patientsServiceJPA.getUserIdFromToken(authentication);
//        if (!currentPatientId.equals(patientId)) {
//            FnCommon.throwsErrorApp(ErrorApp.ERR_USER_PERMISSION);
//        }
		itemParamsEntity.setPatientId(patientId);

		MedicalHealthcarePrehistoricDTO result = new MedicalHealthcarePrehistoricDTO();
		result.setPatientId(itemParamsEntity.getPatientId());

		Optional<PatientsEntity> patientsEntity = patientsServiceJPA.findById(itemParamsEntity.getPatientId());
		patientsEntity.ifPresent(entity -> {
			result.setPatientName(entity.getFullname());
			result.setGenderId(patientsEntity.get().getGenderId());
			result.setBirthday((patientsEntity.get().getBirthday() != null) ? patientsEntity.get().getBirthday().getTime() : null);
		});

//        List<MedicalHealthcareAllergyEntity> medicalHealthcareAllergyEntityList = medicalHealthcareAllergyRepositoryJPA.findByPatientIdOrPhoneNumberAndIsActiveAndIsDelete(itemParamsEntity.getPatientId(), itemParamsEntity.getPhoneNumber(), Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
//        List<MedicalHealthcareAllergyDTO> medicalHealthcareAllergyDTOList = MapperUtils.mapAll(medicalHealthcareAllergyEntityList, MedicalHealthcareAllergyDTO.class);
		MedicalHealthcareAllergyDTO medicalHealthcareAllergyDTO = new MedicalHealthcareAllergyDTO();
		medicalHealthcareAllergyDTO.setPatientId(itemParamsEntity.getPatientId());
		medicalHealthcareAllergyDTO.setPhoneNumber(itemParamsEntity.getPhoneNumber());
		medicalHealthcareAllergyDTO.setStartrecord(itemParamsEntity.getStartrecord());
		medicalHealthcareAllergyDTO.setPagesize(itemParamsEntity.getPagesize());
		result.setAllery(medicalHealthcareAllergyRepository.getMedicalHealthcareAllergy(medicalHealthcareAllergyDTO));

//        List<MedicalHealthcarePrehistoricDTO> medicalHealthcarePrehistoricDTOList = new ArrayList<>();
//        List<MedicalHealthcarePrehistoricEntity> medicalHealthcarePrehistoricEntityList =
//                medicalHealthcarePrehistoricRepositoryJPA.findByPatientIdAndIsActiveAndIsDelete(itemParamsEntity.getPatientId(), Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
//        medicalHealthcarePrehistoricEntityList.forEach(medicalHealthcarePrehistoricEntity -> {
//            MedicalHealthcarePrehistoricDTO dto = new MedicalHealthcarePrehistoricDTO();
//            dto.setRelationshipId(medicalHealthcarePrehistoricEntity.getRelationshipId());
//            dto.setRelationshipName(getRelationshipName(medicalHealthcarePrehistoricEntity.getRelationshipId()));
//            dto.setHealthHistory(medicalHealthcarePrehistoricEntity.getHealthHistory());
//            medicalHealthcarePrehistoricDTOList.add(dto);
//        });
		result.setPrehistoric(medicalHealthcarePrehistoricRepository.getMedicalHealthcarePrehistoric(itemParamsEntity));

//        List<MedicalHealthcarePresurgeryEntity> medicalHealthcarePresurgeryEntityList = medicalHealthcarePresurgeryRepositoryJPA.findByPatientIdAndIsActiveAndIsDelete(itemParamsEntity.getPatientId(), Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
//        List<MedicalHealthcarePresurgeryDTO> medicalHealthcarePresurgeryDTOList = MapperUtils.mapAll(medicalHealthcarePresurgeryEntityList, MedicalHealthcarePresurgeryDTO.class);
//        if (medicalHealthcarePresurgeryDTOList != null) {
//            medicalHealthcarePresurgeryDTOList.forEach(medicalHealthcarePresurgeryDTO -> {
//                medicalHealthcarePresurgeryDTO.setRelationshipName(getRelationshipName(medicalHealthcarePresurgeryDTO.getRelationshipId()));
//                CatsHealthfacilitiesEntity catsHealthfacilitiesEntity = catsHealthfacilitiesRepositoryJPA.findByHealthfacilitiesCodeAndIsActiveAndIsDelete(medicalHealthcarePresurgeryDTO.getHealthfacilitiesCode(), Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
//                medicalHealthcarePresurgeryDTO.setHealthfacilitiesName(catsHealthfacilitiesEntity.getName());
//                medicalHealthcarePresurgeryDTO.setPatientId(null);
//                medicalHealthcarePresurgeryDTO.setCreateDate(null);
//                medicalHealthcarePresurgeryDTO.setIsDelete(null);
//                medicalHealthcarePresurgeryDTO.setIsActive(null);
//                medicalHealthcarePresurgeryDTO.setIsSync(null);
//            });
//        }
		MedicalHealthcarePresurgeryDTO medicalHealthcarePresurgeryDTO = new MedicalHealthcarePresurgeryDTO();
		medicalHealthcarePresurgeryDTO.setPatientId(itemParamsEntity.getPatientId());
		medicalHealthcarePresurgeryDTO.setPhoneNumber(itemParamsEntity.getPhoneNumber());
		medicalHealthcarePresurgeryDTO.setStartrecord(itemParamsEntity.getStartrecord());
		medicalHealthcarePresurgeryDTO.setPagesize(itemParamsEntity.getPagesize());
		result.setPresurgery(medicalHealthcarePresurgeryRepository.getMedicalHealthcarePresurgery(medicalHealthcarePresurgeryDTO));

		return result;
	}


//    private String getRelationshipName(Integer relationshipId) {
//        Optional<CatsRelationshipsEntity> optionalCatsRelationshipsEntity = catsRelationshipsServiceJPA.findById(relationshipId);
//        return optionalCatsRelationshipsEntity.map(CatsRelationshipsEntity::getName).orElse(null);
//    }
}
