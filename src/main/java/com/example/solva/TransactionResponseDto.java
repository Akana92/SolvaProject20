package com.example.solva;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
public class TransactionResponseDto {
    private String fromUserName;
    private String toUserName;
    private String currencyShortName;
    private BigDecimal amount;
    private ZonedDateTime dateTime;
}
