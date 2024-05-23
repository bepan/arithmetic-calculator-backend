package com.betopan.pitschallenge.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public List<User> findAll() {
    return this.userRepository.findAll();
  }
  

}
