package com.betopan.pitschallenge.operation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Integer> {

  Operation findByType(String type);

}
