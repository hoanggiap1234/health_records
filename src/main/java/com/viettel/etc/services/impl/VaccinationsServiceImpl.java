package com.viettel.etc.services.impl;

import com.google.gson.Gson;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.viettel.etc.dto.CovidPatientDTO;
import com.viettel.etc.dto.CovidPatientResultDTO;
import com.viettel.etc.dto.ResponseVaccinationHistoryDTO;
import com.viettel.etc.dto.VaccinationsDTO;
import com.viettel.etc.repositories.VaccinationsRepository;
import com.viettel.etc.repositories.tables.entities.PatientsEntity;
import com.viettel.etc.services.MessageService;
import com.viettel.etc.services.VaccinationsService;
import com.viettel.etc.services.tables.PatientsServiceJPA;
import com.viettel.etc.utils.*;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Thu Aug 27 09:47:44 ICT 2020
 */
@Service
public class VaccinationsServiceImpl implements VaccinationsService{

    @Autowired 
    private VaccinationsRepository vaccinationsRepository;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PatientsServiceJPA patientsServiceJPA;
    /**
     * api get vaccinations history
     * 
     * @param itemParamsEntity params client
     * @return 
     */
    @Override
    public ResultSelectEntity getVaccinationsHistory(VaccinationsDTO itemParamsEntity, Integer patientId, Authentication authentication) throws TeleCareException {
        TelecareUserEntity userEntity = FnCommon.getTelecareUserInfo(authentication);
        if (userEntity == null) {
            throw new TeleCareException(ErrorApp.ERR_USER_PERMISSION, messageService.getMessage(Constants.ERR_USER_PERMISSION, itemParamsEntity.getLanguage()));
        }
        // check quyen cua tai khoan dang nhap voi patientId truyen len
        Integer patientIdToken = userEntity.getTelecareUserId().intValue();
        Boolean hasRelationship = patientsServiceJPA.checkRelationship(patientId, patientIdToken);
        if (!hasRelationship){
            throw new TeleCareException(ErrorApp.ERR_USER_PERMISSION, messageService.getMessage(Constants.ERR_USER_PERMISSION, itemParamsEntity.getLanguage()));
        }
        // khong query theo param tren path
        // lay thong tin cua patientId truyen len de tim ra thong tin patient roi query
        PatientsEntity patientsEntity = patientsServiceJPA.getOne(patientId);
        itemParamsEntity.setFullname(patientsEntity.getFullname());
        itemParamsEntity.setPhoneNumber(patientsEntity.getPhoneNumber());
        ResultSelectEntity dataResult = vaccinationsRepository.getVaccinationsHistory(itemParamsEntity);
        return dataResult;
    }

    /**
     * api get detail vaccinations history
     * 
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Optional<VaccinationsDTO> getDetailVaccinationsHistory(VaccinationsDTO itemParamsEntity) {
        /*
        ==========================================================
        itemParamsEntity: params nguoi dung truyen len
        ==========================================================
        */
        return vaccinationsRepository.getDetailVaccinationsHistory(itemParamsEntity);
    }

    @Override
    public Object getVaccinationsCovidHistory(VaccinationsDTO dataParams, Integer patientId, Authentication authentication) throws TeleCareException, IOException {
        TelecareUserEntity userEntity = FnCommon.getTelecareUserInfo(authentication);
        if (userEntity == null) {
            throw new TeleCareException(ErrorApp.ERR_USER_PERMISSION, messageService.getMessage(Constants.ERR_USER_PERMISSION, dataParams.getLanguage()));
        }
        // check quyen cua tai khoan dang nhap voi patientId truyen len
        Integer patientIdToken = userEntity.getTelecareUserId().intValue();
        Boolean hasRelationship = patientsServiceJPA.checkRelationship(patientId, patientIdToken);
        if (!hasRelationship){
            throw new TeleCareException(ErrorApp.ERR_USER_PERMISSION, messageService.getMessage(Constants.ERR_USER_PERMISSION, dataParams.getLanguage()));
        }
        // khong query theo param tren path
        // lay thong tin cua patientId truyen len de tim ra thong tin patient roi query
        PatientsEntity patientsEntity = patientsServiceJPA.getOne(patientId);
        CovidPatientDTO body = new CovidPatientDTO();

        String token = FnCommon.getTokenHSSK();
        String url = System.getenv("HSSK_VACCINES_URL") + "/api/v1/immunization/covid-patient/vaccinations-covid";
//        String url = "http://localhost:9006/covid-patient/vaccinations-covid";
        FnCommon.copyProperties(patientsEntity, body);
        // ignore
        body.setIdentification(null);
        body.setHealthInsuranceNumber(null);
        body.setPersonalPhoneNumber(patientsEntity.getPhoneNumber());
        body.setFullname(NlpUtils.correctVnAccentSentence(Normalizer.normalize(patientsEntity.getFullname(), Normalizer.Form.NFKC)));

        RequestBody requestBody = RequestBody.create(Constants.JSON, FnCommon.toStringJson(body));
        Response response = FnCommon.doPostRequest(url, token, requestBody);

        ResponseVaccinationHistoryDTO result;
        if (response == null || !response.isSuccessful()){
            result = new ResponseVaccinationHistoryDTO();
            ResponseVaccinationHistoryDTO.VaccinationHistoryData data = new ResponseVaccinationHistoryDTO.VaccinationHistoryData();
            data.setListData(new ArrayList<>());
            data.setCount(0);
            result.setData(data);
        } else {
            result = new Gson().fromJson(response.body().string(), ResponseVaccinationHistoryDTO.class);
        }

        return result.getData();
    }
}