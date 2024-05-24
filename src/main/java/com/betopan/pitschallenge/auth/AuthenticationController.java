package com.betopan.pitschallenge.auth;

import org.springframework.web.bind.annotation.RestController;

import com.betopan.pitschallenge.auth.exceptions.InvalidCredentialsException;
import com.betopan.pitschallenge.user.User;
import com.betopan.pitschallenge.user.UserRepository;
import com.betopan.pitschallenge.util.jwt.JwtService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthenticationController {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private AuthenticationService authService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public @ResponseBody Object login(
    @RequestBody LoginRequestDTO requestBody
  ) {
    String username = requestBody.getUsername();
    String password = requestBody.getPassword();
    User foundUser = this.userRepository.findByUsername(username);

    if (foundUser == null || !this.passwordEncoder.matches(password, foundUser.getPassword())) {
      throw new InvalidCredentialsException();
    }

    if (foundUser.getStatus().equals("inactive")) {
      throw new RuntimeException("Your account has been deactivated.");
    }

    String jws = this.jwtService.generate(username);

    return new Object() {
      public String token = jws;
    };
  }

  @PostMapping("/logout")
  public @ResponseBody Object logout(HttpServletRequest req) {
    String jws = (String)req.getAttribute("jws");

    this.authService.expireToken(jws);

    return new Object() {
      public String message = "Your session has been successfully expired.";
    };
  }
  


}
