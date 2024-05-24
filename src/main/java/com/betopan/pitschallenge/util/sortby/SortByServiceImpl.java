package com.betopan.pitschallenge.util.sortby;

import java.util.Arrays;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SortByServiceImpl implements SortByService {

  @Override
  public Sort parse(String value) {
    String[] sortByOptions = value.split(",");
    String[] splitFirstOption = sortByOptions[0].split(":");
    
    Sort sortingOptions = splitFirstOption[1] == "asc"
      ? Sort.by(splitFirstOption[0]).ascending()
      : Sort.by(splitFirstOption[0]).descending();

    String[] restOfSortByOptions = Arrays.copyOfRange(sortByOptions, 1, sortByOptions.length);

    for(String option : restOfSortByOptions) {
      String[] splitOption = option.split(":");
      sortingOptions = splitOption[1] == "asc" 
        ? sortingOptions.and(Sort.by(splitOption[0]).ascending()) 
        : sortingOptions.and(Sort.by(splitOption[0]).descending());
    }

    return sortingOptions;
  }

}
