package com.betopan.pitschallenge.record;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.betopan.pitschallenge.operation.Operation;
import com.betopan.pitschallenge.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_operation_records")
public class Record {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @JsonIgnore
  private User user;

  @ManyToOne
  @JoinColumn(name = "operation_id", referencedColumnName = "id")
  private Operation operation;

  private String amount;

  @Column(name = "user_balance")
  private String userBalance;

  @Column(name = "operation_result")
  private String operationResult;

  @CreationTimestamp
  @Column(updatable = false)
  private Date date;

  @Column(name = "is_soft_deleted")
  private boolean isSoftDeleted = false;

  public Record() {
  }

  public Record(User user, Operation operation, String operationResult) {
    this.user = user;
    this.operation = operation;
    this.amount = String.valueOf(operation.getCost());
    this.userBalance = String.valueOf(user.getBalance());
    this.operationResult = operationResult;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Operation getOperation() {
    return this.operation;
  }

  public void setOperation(Operation operation) {
    this.operation = operation;
  }

  public String getAmount() {
    return this.amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getUserBalance() {
    return this.userBalance;
  }

  public void setUserBalance(String userBalance) {
    this.userBalance = userBalance;
  }

  public String getOperationResult() {
    return this.operationResult;
  }

  public void setOperationResult(String operationResult) {
    this.operationResult = operationResult;
  }

  public Date getDate() {
    return this.date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public boolean getIsSoftDeleted() {
    return this.isSoftDeleted;
  }

  public void setIsSoftDeleted(boolean isSoftDeleted) {
    this.isSoftDeleted = isSoftDeleted;
  }

}
