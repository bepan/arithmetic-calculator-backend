package com.betopan.pitschallenge.operation;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.betopan.pitschallenge.auth.AuthenticationService;
import com.betopan.pitschallenge.user.User;
import com.betopan.pitschallenge.user.UserRepository;
import com.betopan.pitschallenge.util.jwt.JwtService;

@WebMvcTest(value = OperationController.class)
public class OperationControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private JwtService jwtService;

  // @MockBean
  // @Qualifier("handlerExceptionResolver")
  // private HandlerExceptionResolver resolver;

  @MockBean
  private AuthenticationService authService;

	@MockBean private UserRepository userRepository;
  @MockBean private OperationRepository operationRepository;
  // @MockBean private OperationService operationService;
  // @MockBean private RecordRepository recordRepository;

  // @Test
  // void GivenUserWantsToExecuteOperation__WhenPassingUnsupportedOperation__ThenBadRequestExcpetionShouldBeThrown() throws Exception {
  //   String unsupportedOperation = "unsupported_operation";
  //   this.mockMvc
  //     .perform(
  //       post("/operations/execute")
  //         .header("Content-Type", "application/json")
  //         .header("Authorization", "alberto.crcu15@gmail.com")
  //         .content(String.format("{\"operationType\": \"%s\"}", unsupportedOperation))
  //     )
  //     .andDo(print())
  //     .andExpect(status().isBadRequest())
  //     .andExpect(jsonPath("$.errors[0]", is("The operationType must be any of: addition|subtraction|multiplication|division|square_root|random_string")));
  // }

  @Test
  void GivenUserWantsToExecuteOperation__WhenUserDoesNotHaveEnoughBalance__ThenForbiddenExceptionShouldBeThrown() 
  throws Exception 
  {
    // User zeroBalanceUser = new User();
    // zeroBalanceUser.setBalance(0);
    // when(userRepository.findByUsername("alberto.crcu15@gmail.com")).thenReturn(zeroBalanceUser);
    // this.mockMvc
    //   .perform(
    //     post("/operations/execute")
    //       .header("Content-Type", "application/json")
    //       // .header("Authorization", "Bearer alberto.crcu15@gmail.com")
    //       .content(String.format("{\"operationType\": \"division\"}"))
    //   )
    //   .andDo(print())
    //   .andExpect(status().isOk());
  }

}
