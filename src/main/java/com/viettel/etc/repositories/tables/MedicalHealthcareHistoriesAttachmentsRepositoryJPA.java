package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.MedicalHealthcareHistoriesAttachmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHealthcareHistoriesAttachmentsRepositoryJPA extends JpaRepository<MedicalHealthcareHistoriesAttachmentsEntity,Integer> {
}
