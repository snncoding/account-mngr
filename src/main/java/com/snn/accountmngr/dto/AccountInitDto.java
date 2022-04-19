package com.snn.accountmngr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class AccountInitDto {

    @NotNull(message = "CustomerId must not be null")
    private Integer customerId;
    @NotNull(message = "InitCredit must not be null")
    private BigDecimal initCredit;
}
