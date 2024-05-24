package com.betopan.pitschallenge;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.betopan.pitschallenge.operation.Operation;
import com.betopan.pitschallenge.operation.OperationRepository;
import com.betopan.pitschallenge.operation.enums.OperationType;
import com.betopan.pitschallenge.user.User;
import com.betopan.pitschallenge.user.UserRepository;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class PitsChallengeApplication implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private OperationRepository operationRepository;

  @Value("${app.user.password}")
  private String defaultUserPassword;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) {
    // Seed root user
    if (this.userRepository.findByUsername("root@gmail.com") == null) {
      this.userRepository.save(new User(
        "root@gmail.com", 
        this.passwordEncoder.encode(this.defaultUserPassword), 
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

  public static void main(String[] args) {
		SpringApplication.run(PitsChallengeApplication.class, args);
	}
}
