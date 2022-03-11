package com.viettel.etc.repositories.tables;import com.viettel.etc.repositories.tables.entities.PatientsRelationshipsEntity;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;/** * Autogen class Repository Interface: Create Repository For Table Name Patients_relationships *  * @author ToolGen * @date Thu Aug 13 22:25:22 ICT 2020 */@Repositorypublic interface PatientsRelationshipsRepositoryJPA extends JpaRepository<PatientsRelationshipsEntity, Long> {    void deleteByPatientIdAndPatientParentId(Integer patientRelativeId, Integer patientId);    Boolean existsByPatientIdAndPatientParentId(Integer patientRelativeId, Integer patientId);    PatientsRelationshipsEntity findByPatientIdAndPatientParentId(Integer patientRelativeId, Integer patientId);}