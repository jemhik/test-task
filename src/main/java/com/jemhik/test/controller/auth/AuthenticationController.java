package com.jemhik.test.controller.auth;

import com.jemhik.test.api.AuthApi;
import com.jemhik.test.dto.AuthenticationRequest;
import com.jemhik.test.dto.RefreshTokenRequest;
import com.jemhik.test.dto.RefreshTokenResponse;
import com.jemhik.test.dto.RegisterRequest;
import com.jemhik.test.dto.auth.AuthenticationResponse;
import com.jemhik.test.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthApi {

  private final AuthenticationService service;

  @Override
  public ResponseEntity<AuthenticationResponse> signUp(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(service.signUp(request));
  }

  @Override
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @Override
  public ResponseEntity<RefreshTokenResponse> refresh(@RequestBody RefreshTokenRequest request) {
    return ResponseEntity.ok(service.refresh(request));
  }

}
