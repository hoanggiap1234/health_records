package com.viettel.etc.services.impl;

import com.viettel.etc.dto.*;
import com.viettel.etc.repositories.DrugsRepository;
import com.viettel.etc.repositories.MedicalHealthcareHistoriesRepository;
import com.viettel.etc.repositories.PriceRepository;
import com.viettel.etc.services.MedicalHealthcareHistoriesService;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.utils.TelecareUserEntity;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Tue Sep 22 16:35:41 ICT 2020
 */
@Service
public class MedicalHealthcareHistoriesServiceImpl implements MedicalHealthcareHistoriesService {

	@Autowired
	private MedicalHealthcareHistoriesRepository medicalHealthcareHistoriesRepository;

	@Autowired
	private DrugsRepository drugsRepository;

	@Autowired
	private PriceRepository priceRepository;

	/**
	 * Danh sach cac don thuoc (Ho so suc khoe)
	 *
	 * @param itemParamsEntity params client
	 * @return
	 */
	@Override
	public ResultSelectEntity getHealthcareDrugs(MedicalHealthcareHistoriesDTO itemParamsEntity) {
        /*
        ==========================================================
        itemParamsEntity: params nguoi dung truyen len
        ==========================================================
        */
		ResultSelectEntity dataResult = medicalHealthcareHistoriesRepository.getHealthcareDrugs(itemParamsEntity);
        /*
        ==========================================================
        TODO: (Code at here) Thuc hien luong nghiep vu chi tiet
        ==========================================================
        */
		return dataResult;
	}

	@Override
	public Optional<MedicalHealthcareHistoriesDTO> getDetailHealthcareDrug(MedicalHealthcareHistoriesDTO itemParamsEntity) {
		Optional<MedicalHealthcareHistoriesDTO> dataResult = medicalHealthcareHistoriesRepository.getDetailHealthcareDrug(itemParamsEntity);

		DrugsDTO dto = new DrugsDTO();
		dto.setHistoriesId(itemParamsEntity.getHistoriesId());
		dto.setPhoneNumber(itemParamsEntity.getPhoneNumber());
		dataResult.ifPresent(medicalHealthcareHistoriesDTO -> medicalHealthcareHistoriesDTO.setDrugs(drugsRepository.getListDrug(dto)));
		return dataResult;
	}

	/**
	 * Danh sach lich tai kham
	 *
	 * @param itemParamsEntity params client
	 * @return
	 */
	@Override
	public ResultSelectEntity getReExaminations(MedicalHealthcareHistoriesDTO itemParamsEntity, Authentication authentication) throws TeleCareException {
        /*
        ==========================================================
        itemParamsEntity: params nguoi dung truyen len
        ==========================================================
        */

		TelecareUserEntity userSystemEntity = FnCommon.getTelecareUserInfo(authentication);
		if(userSystemEntity==null) {
			throw new TeleCareException(ErrorApp.ERR_USER_NOT_PERMISSION);
		}
		Long patientBookingId = userSystemEntity.getTelecareUserId();

		ResultSelectEntity dataResult =
				medicalHealthcareHistoriesRepository.getReExaminations(itemParamsEntity, patientBookingId);

		List<ReExaminationDTO> listData = (List<ReExaminationDTO>) dataResult.getListData();
		if(listData!=null && listData.size()>0) {
			for(ReExaminationDTO entity : listData) {
				//medicalExaminationFee
				List<DoctorDTO> doctorPriceListData = (List<DoctorDTO>) priceRepository.getDoctorExamPrice(entity.getDoctorId());
				for(DoctorDTO doctorPrice : doctorPriceListData) {
					if(doctorPrice.getPhaseId()!=null) {
						entity.setMedicalExaminationFee(doctorPrice.getMedicalExaminationFee());
						break;
					} else if (doctorPrice.getDoctorPhaseId()==0){
						entity.setMedicalExaminationFee(doctorPrice.getMedicalExaminationFee());
					}
				}
				//services
				List<ServiceDTO> serviceListData = (List<ServiceDTO>) priceRepository.getServicePrice(entity.getServiceId());
				for(ServiceDTO service : serviceListData) {
					if(service.getPhaseId()!=null) {
						service.setPhaseId(null);
						service.setServicePhaseId(null);
						entity.setServices(service);
						break;
					} else if (service.getServicePhaseId()==0){
						service.setPhaseId(null);
						service.setServicePhaseId(null);
						entity.setServices(service);
					}
				}
				entity.setServiceId(null);
			}
		}

		return dataResult;
	}

}