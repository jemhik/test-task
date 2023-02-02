package com.jemhik.test.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "TRANSACTION")
public class Transaction {
  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID id;
  private Long initiatorId;
  private Long senderId;
  private Long receiverId;
  private double trxnAmount;
  private double feeAmount;
  private double serviceCode;
  private String bankCode;
  @Builder.Default
  private boolean refunded = false;
  private int status;
  @Builder.Default
  private boolean success = false;
  @Builder.Default
  private boolean deleted = false;

  @CreatedDate
  private LocalDateTime created;

  @LastModifiedDate
  private LocalDateTime modified;

}
