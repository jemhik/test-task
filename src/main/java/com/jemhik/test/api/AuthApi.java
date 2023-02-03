package com.jemhik.test.api;

import com.jemhik.test.dto.AuthenticationRequest;
import com.jemhik.test.dto.RefreshTokenRequest;
import com.jemhik.test.dto.RefreshTokenResponse;
import com.jemhik.test.dto.RegisterRequest;
import com.jemhik.test.dto.auth.AuthenticationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = "Authentication API")
@RequestMapping("/api/v1/auth")
public interface AuthApi {

  @ApiOperation("SignUp new user")
  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/signup")
  ResponseEntity<AuthenticationResponse> signUp(@RequestBody RegisterRequest request);

  @ApiOperation("Authenticate user")
  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/authentication")
  ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);

  @ApiOperation("Refresh access token by refresh token")
  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/refresh")
  ResponseEntity<RefreshTokenResponse> refresh(@RequestBody RefreshTokenRequest request);

}
