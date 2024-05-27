package com.betopan.pitschallenge.record;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecordRepository extends JpaRepository<Record, Integer> {

  @Query("SELECT r FROM Record r " 
    + "WHERE r.user.id = :userId "
    + "AND r.isSoftDeleted = false "
    + "AND (r.operation.type LIKE %:query% "
    + "OR r.amount LIKE %:query% "
    + "OR r.userBalance LIKE %:query% "
    + "OR r.operationResult LIKE %:query%)")
  Page<Record> findAllByUserId(
    @Param("userId") int userId,
    @Param("query") String query, 
    Pageable pageable
  );

}
