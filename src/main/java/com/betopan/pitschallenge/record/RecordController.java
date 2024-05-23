package com.betopan.pitschallenge.record;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/records")
public class RecordController {

  @Autowired
  private RecordRepository recordRepository;

  @GetMapping
  public List<Record> findAll() {
    return this.recordRepository.findAll();
  }
  

}
