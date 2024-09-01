package com.example.solva;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequestDto {
    private Integer receiverId;
    private String amount;
    private String currency;
}
