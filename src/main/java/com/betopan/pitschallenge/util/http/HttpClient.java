package com.betopan.pitschallenge.util.http;

public interface HttpClient {
  public <T> T get(String url, Class<T> returnType);
}
