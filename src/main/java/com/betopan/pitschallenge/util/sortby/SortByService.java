package com.betopan.pitschallenge.util.sortby;

import org.springframework.data.domain.Sort;

public interface SortByService {
  Sort parse(String value);
}
