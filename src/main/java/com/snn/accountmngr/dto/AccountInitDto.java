package com.snn.accountmngr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class AccountInitDto {

    private Integer customerId;
    private BigDecimal initCredit;
}
