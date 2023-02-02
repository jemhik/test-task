package com.jemhik.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
  private Long initiatorId;
  private Long senderId;
  private Long receiverId;
  private double trxnAmount;
  private double feeAmount;
  private double serviceCode;
  private String bankCode;
}
