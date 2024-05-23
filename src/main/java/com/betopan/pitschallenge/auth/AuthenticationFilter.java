package com.betopan.pitschallenge.auth;

import jakarta.servlet.Filter; 
import jakarta.servlet.FilterChain; 
import jakarta.servlet.ServletException; 
import jakarta.servlet.ServletRequest; 
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.betopan.pitschallenge.auth.exceptions.InvalidJWTException;
import com.betopan.pitschallenge.auth.exceptions.NoTokenProvidedException;
import com.betopan.pitschallenge.util.jwt.JwtService; 

@Component
public class AuthenticationFilter implements Filter {

  @Autowired
  private JwtService jwtService;

  @Autowired
  @Qualifier("handlerExceptionResolver")
  private HandlerExceptionResolver resolver;

  @Autowired
  AuthenticationService authService;

  private List<String> notApplicableUris = Arrays.asList("/login");

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) 
  throws IOException, ServletException
  {
    try {
      HttpServletRequest httpReq = (HttpServletRequest) req;
      String authorization = httpReq.getHeader("Authorization");

      if (this.notApplicableUris.contains(httpReq.getRequestURI())) {
        filterChain.doFilter(req, res);
        return;
      }

      if (authorization == null) {
        throw new NoTokenProvidedException();
      }

      String token = authorization.substring(7);
      
      if (this.authService.isTokenManuallyExpired(token)) {
        throw new InvalidJWTException();
      }

      String authenticatedUsername = this.jwtService.verifyAndGetSubject(token);
      req.setAttribute("jws", token);
      req.setAttribute("authUsername", authenticatedUsername);
      filterChain.doFilter(req, res); 
    }
    catch (Exception e) {
      this.resolver.resolveException((HttpServletRequest)req, (HttpServletResponse)res, null, e);
    }
  }

}
