package com.betopan.pitschallenge.operation;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.betopan.pitschallenge.user.User;
import com.betopan.pitschallenge.user.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class OperationControllerTests {

  @Autowired
  private MockMvc mockMvc;

	@MockBean
	private UserRepository userRepository;

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

  // @Test
  // void GivenUserWantsToExecuteOperation__WhenUserDoesNotHaveEnoughBalance__ThenForbiddenExceptionShouldBeThrown() 
  // throws Exception 
  // {
  //   User zeroBalanceUser = new User();
  //   zeroBalanceUser.setBalance(0);
  //   when(userRepository.findByUsername("alberto.crcu15@gmail.com")).thenReturn(zeroBalanceUser);
  //   this.mockMvc
  //     .perform(
  //       post("/operations/execute")
  //         .header("Content-Type", "application/json")
  //         .header("Authorization", "alberto.crcu15@gmail.com")
  //         .content(String.format("{\"operationType\": \"division\"}"))
  //     )
  //     .andDo(print())
  //     .andExpect(status().isForbidden());
  // }

}
