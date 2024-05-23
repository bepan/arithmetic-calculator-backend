package com.betopan.pitschallenge.operation;

import java.util.List;

import com.betopan.pitschallenge.record.Record;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "operations")
@Entity
public class Operation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true)
  private String type;
  
  private double cost;

  @OneToMany(mappedBy = "operation")
  @JsonIgnore
  private List<Record> users;

  public Operation() {
  }

  public Operation(String type, double cost) {
    this.type = type;
    this.cost = cost;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getCost() {
    return this.cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public List<Record> getUsers() {
    return this.users;
  }

  public void setUsers(List<Record> users) {
    this.users = users;
  }

}
