package com.betopan.pitschallenge.record;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.betopan.pitschallenge.user.User;
import com.betopan.pitschallenge.user.UserRepository;
import com.betopan.pitschallenge.util.sortby.SortByService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
  public @ResponseBody Object findAllOfMine(
    HttpServletRequest req,
    @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
    @RequestParam(value = "sortBy", defaultValue = "date:desc") String sortBy,
    @RequestParam(value = "searchInputQuery", defaultValue = "") String searchInputQuery
  ) {
    String authUsername = (String)req.getAttribute("authUsername");
    User authenticatedUser = this.userRepository.findByUsername(authUsername);

    Sort parsedSortBy = this.sortByService.parse(sortBy);

    Page<Record> result = this.recordRepository.findAllByUserId(
      authenticatedUser.getId(),
      searchInputQuery,
      PageRequest.of(pageNumber, pageSize, parsedSortBy)
    );

    return new Object() {
      public List<Record> data = result.getContent();
      public long totalElements = result.getTotalElements();
      public int totalPages = result.getTotalPages();
    };
  }

  @DeleteMapping("/{recordId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public @ResponseBody void delete(@PathVariable int recordId) {
    Optional<Record> recordWrapper = this.recordRepository.findById(recordId);

    if (recordWrapper.isEmpty()) {
      throw new RuntimeException("Resource not found with id: " + recordId);
    }
    
    Record record = recordWrapper.get();
    record.setIsSoftDeleted(true);
    this.recordRepository.save(record);
  }
  
}
