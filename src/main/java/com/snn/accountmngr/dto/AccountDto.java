package com.snn.accountmngr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
public class AccountDto {
    private Integer id;
    private BigDecimal credit;
    private Set<TransactionDto> transactions;
}
