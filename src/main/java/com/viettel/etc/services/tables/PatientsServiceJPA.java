package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.CatsRelationshipsRepositoryJPA;
import com.viettel.etc.repositories.tables.MedicalHealthcarePatientDetailRepositoryJPA;
import com.viettel.etc.repositories.tables.MedicalHealthcarePatientSummaryRepositoryJPA;
import com.viettel.etc.repositories.tables.PatientsRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.PatientsEntity;
import com.viettel.etc.services.KeycloakService;
import com.viettel.etc.services.PatientService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Patients
 *
 * @author ToolGen
 * @date Wed Aug 12 17:57:15 ICT 2020
 */
@Service
public class PatientsServiceJPA {

	private static final Logger LOG = LoggerFactory.getLogger(PatientsServiceJPA.class);

	@Autowired
	PatientsRepositoryJPA patients;

	@Autowired
	PatientService patientService;

	@Autowired
	PatientsRelationshipsServiceJPA patientRelationshipServiceJPA;

	@Autowired
	KeycloakService keycloakService;

	@Autowired
	SysUsersServiceJPA sysUserServiceJPA;

	@Autowired
	OtpServiceJPA otpServiceJPA;

	@Autowired
	PatientsHealthinsurancesServiceJPA patientsHealthinsurancesServiceJPA;

	@Autowired
	MedicalHealthcarePrehistoricServiceJPA medicalHealthcarePrehistoricServiceJPA;

	@Autowired
	MedicalHealthcarePatientSummaryServiceJPA medicalHealthcarePatientSummaryServiceJPA;

	@Autowired
	MedicalHealthcarePatientSummaryRepositoryJPA medicalHealthcarePatientSummaryRepositoryJPA;

	@Autowired
	MedicalHealthcarePatientDetailRepositoryJPA medicalHealthcarePatientDetailRepositoryJPA;

	@Autowired
	private CatsRelationshipsRepositoryJPA catsRelationshipsRepositoryJPA;

	public List<PatientsEntity> findAll() {
		return this.patients.findAll();
	}

	public Optional<PatientsEntity> findById(Integer id) {
		return this.patients.findById(id);
	}

	public void deleteById(Integer id) {
		this.patients.deleteById(id);
	}

	public PatientsEntity getOne(Integer id) {
		return this.patients.getOne(id);
	}

	public Boolean existsById(Integer id) {
		return this.patients.existsById(id);
	}

	public Integer getPatientIdByKeycloakId(String keycloakId) {
		return patients.findByKeycloakUserId(keycloakId).getPatientId();
	}

	public Boolean existsByKeycloakUserId(String keycloakId) {
		return patients.existsByKeycloakUserId(keycloakId);
	}

	/**
	 * @param patientIdParam: patient id param is patient or patient relative
	 * @param patientIdToken: patient id logged
	 */
	public Boolean checkRelationship(Integer patientIdParam, Integer patientIdToken) throws TeleCareException {
		Boolean hasRelationship = true;
		if (!patientIdToken.equals(patientIdParam)) {
			hasRelationship = patientRelationshipServiceJPA.existsByPatientIdAndPatientParentId(patientIdParam,
					patientIdToken);
		}
		return hasRelationship;
	}

	public Boolean checkAuthentication(Integer patientIdParam, Integer patientIdToken) throws TeleCareException {
		// TODO @author tungvv2 check xem id trong token với id truyền lên của patient có giống nhau ko?
		//  => thêm mã lỗi
		Boolean authenticated = true;
		if (!patientIdToken.equals(patientIdParam)) {
			authenticated = false;
		}
		return authenticated;
	}

	public Integer getUserIdFromToken(Authentication authentication) throws TeleCareException {
		// current patient logged
		String keyCloakId = FnCommon.getUserIdLogin(authentication);
		if (keyCloakId == null) {
			FnCommon.throwsErrorApp(ErrorApp.ERR_DATA_PATIENT_NOT_EXIST);
		}
		PatientsEntity patientsEntity = patients.findByKeycloakUserId(keyCloakId);
		if (patientsEntity == null) {
			throw new TeleCareException(ErrorApp.ERR_DATA_PATIENT_NOT_EXIST);
		}
		Integer currentPatientId = patientsEntity.getPatientId();
		return currentPatientId;
	}

	public boolean existByPhone(String phone) {
		return patients.existsByPhoneNumberAndIsActiveAndIsDelete(phone, Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
	}
}
