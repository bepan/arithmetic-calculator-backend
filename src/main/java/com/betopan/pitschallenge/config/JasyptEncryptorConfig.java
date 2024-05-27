package com.betopan.pitschallenge.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptEncryptorConfig {

  @Value("${security.jasypt.secret-key}")
  private String jasyptSecretKey;

  @Bean(name = "jasyptStringEncryptor")
  public StringEncryptor getPasswordEncryptor() {
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    
    config.setPassword(this.jasyptSecretKey); // encryptor's private key
    config.setPoolSize("1");
    config.setProviderName("SunJCE");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    encryptor.setConfig(config);
    return encryptor;
  }


}
