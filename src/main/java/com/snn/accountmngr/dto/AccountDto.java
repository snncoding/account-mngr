package com.snn.accountmngr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
public class AccountDto {
    private Integer id;
    private BigDecimal credit;
    private CustomerDto customer;
}
