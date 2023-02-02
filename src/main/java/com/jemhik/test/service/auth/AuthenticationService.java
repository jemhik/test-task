package com.jemhik.test.service.auth;

import com.jemhik.test.dto.AuthenticationRequest;
import com.jemhik.test.dto.RefreshTokenRequest;
import com.jemhik.test.dto.RefreshTokenResponse;
import com.jemhik.test.dto.RegisterRequest;
import com.jemhik.test.dto.auth.AuthenticationResponse;
import com.jemhik.test.entity.TokenEntity;
import com.jemhik.test.entity.User;
import com.jemhik.test.entity.enums.Role;
import com.jemhik.test.repository.TokenRepository;
import com.jemhik.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse signUp(RegisterRequest request) {
    var user = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();

    userRepository.save(user);
    var jwtAccessToken = jwtService.generateAccessToken(user);
    var jwtRefreshToken = jwtService.generateRefreshToken(user);
    return AuthenticationResponse.builder()
            .accessToken(jwtAccessToken)
            .refreshToken(jwtRefreshToken)
            .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
    var jwtAccessToken = jwtService.generateAccessToken(user);
    var jwtRefreshToken = jwtService.generateRefreshToken(user);
    TokenEntity token = TokenEntity.builder()
            .userId(user.getId())
            .accessToken(jwtAccessToken)
            .refreshToken(jwtRefreshToken)
            .build();
    tokenRepository.save(token);
    return AuthenticationResponse.builder()
            .accessToken(jwtAccessToken)
            .refreshToken(jwtRefreshToken)
            .build();
  }

  public RefreshTokenResponse refresh(RefreshTokenRequest request) {
    String refreshToken = request.getRefreshToken();
    TokenEntity token = tokenRepository.findByRefreshToken(refreshToken).orElseThrow();
    String userEmail = userRepository.findById(token.getUserId()).orElseThrow().getEmail();
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
    if (jwtService.isRefreshTokenValid(refreshToken, userDetails)) {
      var jwtAccessToken = jwtService.generateAccessToken(userDetails);
      token.setAccessToken(jwtAccessToken);
      tokenRepository.save(token);
      return RefreshTokenResponse.builder()
              .accessToken(jwtAccessToken)
              .refreshToken(token.getRefreshToken())
              .build();
    }
    return null;
  }
}
