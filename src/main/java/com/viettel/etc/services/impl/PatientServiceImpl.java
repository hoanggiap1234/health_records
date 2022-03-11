package com.viettel.etc.services.impl;

import com.viettel.etc.dto.PatientDTO;
import com.viettel.etc.repositories.PatientRepository;
import com.viettel.etc.repositories.tables.PatientsRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.CatsRelationshipsEntity;
import com.viettel.etc.repositories.tables.entities.DoctorsEntity;
import com.viettel.etc.repositories.tables.entities.PatientsRelationshipsEntity;
import com.viettel.etc.services.PatientService;
import com.viettel.etc.services.tables.CatsRelationshipsServiceJPA;
import com.viettel.etc.services.tables.DoctorsServiceJPA;
import com.viettel.etc.services.tables.PatientsRelationshipsServiceJPA;
import com.viettel.etc.services.tables.PatientsServiceJPA;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.entities.ExcellDataEntity;
import com.viettel.etc.xlibrary.core.entities.ExcellHeaderEntity;
import com.viettel.etc.xlibrary.core.entities.ExcellSheet;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Wed Aug 12 17:57:15 ICT 2020
 */
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientsServiceJPA patientServiceJPA;

    @Autowired
    private DoctorsServiceJPA doctorServiceJPA;

    @Autowired
    private PatientsRelationshipsServiceJPA patientRelationshipServiceJPA;

    @Autowired
    private CatsRelationshipsServiceJPA catsRelationshipsServiceJPA;

    @Autowired
    private PatientsRepositoryJPA patientRepositoryJPA;

    /**
     * Lay thong tin benh nhan theo id
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getPatient(PatientDTO itemParamsEntity, Authentication authentication) throws TeleCareException {
        // current patient logged
        Integer currentPatientId = patientServiceJPA.getUserIdFromToken(authentication);
        itemParamsEntity.setPatientId(currentPatientId);
        Object dataResult = patientRepository.getPatient(itemParamsEntity);
        return dataResult;
    }


    /**
     * lay danh sach nguoi than
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getPatientRelatives(PatientDTO itemParamsEntity, Authentication authentication, Integer patientId) throws TeleCareException {
        Integer currentPatientLoggedId = patientServiceJPA.getUserIdFromToken(authentication);
        Boolean authenticated = patientServiceJPA.checkAuthentication(patientId, currentPatientLoggedId);
        // TODO thêm mã lỗi
        if (!authenticated) {
            FnCommon.throwsErrorApp(ErrorApp.ERR_USER_NOT_PERMISSION);
        }
        itemParamsEntity.setPatientId(patientId);
        ResultSelectEntity dataResult = patientRepository.getPatientRelatives(itemParamsEntity);
        return dataResult;
    }

    @Override
    public Object getPatientRelativeInfo(PatientDTO itemParamsEntity, Authentication authentication) throws TeleCareException {
        Integer currentUserIdLogged = patientServiceJPA.getUserIdFromToken(authentication);
        PatientsRelationshipsEntity patientsRelationshipsEntity = patientRelationshipServiceJPA.findByPatientIdAndPatientParentId(
                itemParamsEntity.getPatientId(), currentUserIdLogged);
        if (patientsRelationshipsEntity == null) {
            throw new TeleCareException(ErrorApp.ERR_PATIENT_RELATIONSHIP_NOT_EXIST); // khong co quan he nguoi than thi ko duoc xem
        }
        CatsRelationshipsEntity relationshipEntity = catsRelationshipsServiceJPA.findById(
                patientsRelationshipsEntity.getRelationshipId()).orElse(null);

        String relationshipName = relationshipEntity.getName();
        PatientDTO dataResult = (PatientDTO) patientRepository.getPatient(itemParamsEntity);
        dataResult.setRelationshipId(patientsRelationshipsEntity.getRelationshipId());
        dataResult.setRelationshipName(relationshipName);
        return dataResult;
    }

    @Override
    public Object getPatientInfo(PatientDTO itemParamsEntity) {
        PatientDTO dataResult = (PatientDTO) patientRepository.getPatient(itemParamsEntity);
        return dataResult;
    }

    /**
     * api get patients
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getPatients(PatientDTO itemParamsEntity, Authentication authentication) throws TeleCareException {
        String keycloakId = FnCommon.getUserIdLogin(authentication);
        Boolean isPatient = patientServiceJPA.existsByKeycloakUserId(keycloakId);
        // appointment_doctor_id
        Integer doctorId = itemParamsEntity.getDoctorId();

        // TODO: người dùng đăng nhập không có quyền lấy thông tin bệnh nhân của 1 bác sĩ
        if (isPatient && doctorId != null) {
            FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
        }

        DoctorsEntity doctorEntity = doctorServiceJPA.findByKeycloakUserId(keycloakId);

        if (doctorId != null) {
            // TODO: bác sĩ không tồn tại
            if (doctorEntity == null) {
                FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
            }
            // TODO: bác sĩ đang đăng nhập không có quyền get danh sách bệnh nhân của người khác
            else if (!doctorId.equals(doctorEntity.getDoctorId())){
                FnCommon.throwsErrorApp(ErrorApp.ERROR_INPUTPARAMS);
            }
        }

        ResultSelectEntity dataResult = patientRepository.getPatients(itemParamsEntity);
        return dataResult;
    }


}