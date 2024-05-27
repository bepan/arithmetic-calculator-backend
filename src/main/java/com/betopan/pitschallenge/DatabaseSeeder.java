package com.betopan.pitschallenge;

import java.util.Arrays;
import java.util.Random;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.betopan.pitschallenge.operation.Operation;
import com.betopan.pitschallenge.operation.OperationRepository;
import com.betopan.pitschallenge.operation.OperationType;
import com.betopan.pitschallenge.user.User;
import com.betopan.pitschallenge.user.UserRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private OperationRepository operationRepository;

  @Value("${app.user.password}")
  private String defaultUserPassword;
  @Autowired
  private StringEncryptor encryptor;

  @Override
  public void run(String... args) {
    // Seed root user
    if (this.userRepository.findByUsername("root@gmail.com") == null) {
      this.userRepository.save(new User(
        "root@gmail.com", 
        this.encryptor.encrypt(this.defaultUserPassword),
        "active",
        100
      ));
    }

    // Seed basic operations
    Arrays.asList(OperationType.values()).stream().forEach(operation -> {
      Random random = new Random();
      if (this.operationRepository.findByType(operation.name().toLowerCase()) == null) {
        this.operationRepository.save(new Operation(operation.name().toLowerCase(), random.nextInt(20) + 1));
      }
    });

    System.out.println("Database initialized.");
  }

}
