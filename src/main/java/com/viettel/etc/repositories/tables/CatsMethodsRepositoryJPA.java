package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.CatsMethodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CatsMethodsRepositoryJPA extends JpaRepository<CatsMethodsEntity, Long> {
    @Query(value = "select method_code from cats_methods where name=?1 and is_active=1 and is_delete=0 limit 1", nativeQuery = true)
    String findMethodCodeByMethodName(String methodName);
}
