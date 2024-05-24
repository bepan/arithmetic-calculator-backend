package com.betopan.pitschallenge.record;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.betopan.pitschallenge.user.User;


public interface RecordRepository extends JpaRepository<Record, Integer> {

  Page<List<Record>> findAllByUserId(int userId, Pageable pageable);

}
