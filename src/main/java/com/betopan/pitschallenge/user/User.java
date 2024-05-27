package com.betopan.pitschallenge.user;

import java.util.ArrayList;
import java.util.List;

import com.betopan.pitschallenge.operation.Operation;
import com.betopan.pitschallenge.record.Record;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "users")
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(unique = true)
  private String username;

  private String password;
  private String status;
  private double balance;


  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<Record> operations = new ArrayList<>();


  public User() {
  }

  public User(String username, String password, String status, double balance) {
    this.username = username;
    this.password = password;
    this.status = status;
    this.balance = balance;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public boolean hasEnoughBalanceToExecute(Operation operation) {
    return this.balance >= operation.getCost();
  }

  public void decrementBalanceBy(double amount) {
    this.balance -= amount;
  }


  public List<Record> getOperations() {
    return this.operations;
  }

  public void setOperations(List<Record> operations) {
    this.operations = operations;
  }

}
