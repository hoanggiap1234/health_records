package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.CatsRelationshipsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Cats_relationships
 *
 * @author ToolGen
 * @date Thu Aug 13 22:25:22 ICT 2020
 */
@Repository
public interface CatsRelationshipsRepositoryJPA extends JpaRepository<CatsRelationshipsEntity, Integer> {

    List<CatsRelationshipsEntity> findByRelationshipIdNot(Integer relationshipId);

    CatsRelationshipsEntity findFirstByNameAndIsActiveAndIsDelete(String name, int isActive, int isDelete);
}
