package com.jemhik.test.repository;

import com.jemhik.test.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, UUID> {

  Optional<TokenEntity> findByRefreshToken(String refreshToken);
}
