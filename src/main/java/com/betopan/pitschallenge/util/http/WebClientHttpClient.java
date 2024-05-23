package com.betopan.pitschallenge.util.http;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientHttpClient implements HttpClient {

  private WebClient.Builder builder = WebClient.builder();

  @Override
  public <T> T get(String url, Class<T> returnType) {
    return this.builder.build()
      .get()
      .uri(url)
      .retrieve()
      .bodyToMono(returnType)
      .block();
  }

}