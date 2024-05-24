package com.betopan.pitschallenge.record;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betopan.pitschallenge.user.User;
import com.betopan.pitschallenge.user.UserRepository;
import com.betopan.pitschallenge.util.sortby.SortByService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/records")
public class RecordController {

  @Autowired
  private RecordRepository recordRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private SortByService sortByService;

  @GetMapping("/mine")
  public Page<List<Record>> findAllOfMine(
    HttpServletRequest req,
    @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
    @RequestParam(value = "sortBy", defaultValue = "date:desc") String sortBy
  ) {
    String authUsername = (String)req.getAttribute("authUsername");

    User authenticatedUser = this.userRepository.findByUsername(authUsername);

    Sort parsedSortBy = this.sortByService.parse(sortBy);

    return this.recordRepository.findAllByUserId(
      authenticatedUser.getId(), 
      PageRequest.of(pageNumber, pageSize, parsedSortBy)
    );
  }
  

}
