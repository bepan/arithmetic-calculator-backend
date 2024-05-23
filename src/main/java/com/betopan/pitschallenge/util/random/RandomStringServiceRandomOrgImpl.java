package com.betopan.pitschallenge.util.random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betopan.pitschallenge.util.http.HttpClient;

@Service
public class RandomStringServiceRandomOrgImpl implements RandomStringService {

  private static String URL = "https://www.random.org/strings/?num=1&len=32&digits=on&upperalpha=on&loweralpha=on&unique=on&format=plain&rnd=new";

  @Autowired
  private HttpClient httpClient;

  @Override
  public String generate() {
    String randomString = this.httpClient.get(RandomStringServiceRandomOrgImpl.URL, String.class);
    return randomString.replace("\n", "");
  }

}
