package com.viettel.etc.repositories;

import com.viettel.etc.dto.VaccinationsDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

import java.util.Optional;

/**
 * Autogen class Repository Interface: 
 * 
 * @author toolGen
 * @date Thu Aug 27 09:47:44 ICT 2020
 */
public interface VaccinationsRepository {


    public ResultSelectEntity getVaccinationsHistory(VaccinationsDTO itemParamsEntity);

    Optional<VaccinationsDTO> getDetailVaccinationsHistory(VaccinationsDTO itemParamsEntity);
}