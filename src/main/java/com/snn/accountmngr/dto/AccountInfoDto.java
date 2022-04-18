package com.snn.accountmngr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class AccountInfoDto {
    private String name;
    private String surname;
    private BigDecimal credit;
    private Set<TransactionDto> transactions;
}
