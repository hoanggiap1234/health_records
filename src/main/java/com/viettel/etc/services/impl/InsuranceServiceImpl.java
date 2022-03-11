package com.viettel.etc.services.impl;

import com.viettel.etc.dto.InsuranceDTO;
import com.viettel.etc.repositories.InsuranceRepository;
import com.viettel.etc.repositories.tables.PatientsRepositoryJPA;
import com.viettel.etc.services.InsuranceService;
import com.viettel.etc.services.tables.PatientsRelationshipsServiceJPA;
import com.viettel.etc.services.tables.PatientsServiceJPA;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Tue Aug 18 10:12:25 ICT 2020
 */
@Service
public class InsuranceServiceImpl implements InsuranceService{

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private PatientsRepositoryJPA patientRepositoryJPA;

    @Autowired
    private PatientsRelationshipsServiceJPA patientRelationshipServiceJPA;

    @Autowired
    private PatientsServiceJPA patientServiceJPA;


    /**
     * lay danh sach nguoi than
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getInsuranceInfo(Integer patientId, InsuranceDTO itemParamsEntity, Authentication authentication)
                                    throws TeleCareException {
        // patient id param is patient or patient relative
//        Integer patientIdParam = itemParamsEntity.getPatientId();
        // current patient logged
        Integer currentPatientId = patientServiceJPA.getUserIdFromToken(authentication);
        Boolean authenticated = patientServiceJPA.checkAuthentication(patientId, currentPatientId);
        if (!authenticated) {
            FnCommon.throwsErrorApp(ErrorApp.ERR_USER_NOT_PERMISSION);
        }
        // TODO: @tungvv2 fix ko check relationship
//        Boolean hasRelationship = patientServiceJPA.checkRelationship(patientIdParam, currentPatientId);
//        if (!hasRelationship) {
//            FnCommon.throwsErrorApp(ErrorApp.ERR_PATIENT_RELATIONSHIP_NOT_EXIST);
//        }

        Object dataResult = insuranceRepository.getInsuranceInfo(itemParamsEntity);
        if (dataResult == null) {
            FnCommon.throwsErrorApp(ErrorApp.ERR_DATA_HEALTH_INSURANCE_NOT_EXIST);
        }
        return dataResult;
    }
}