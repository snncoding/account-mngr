package com.snn.accountmngr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class CustomerDto {
    private Integer id;
    private String name;
    private String surname;
    private List<AccountDto> accounts = new ArrayList<>();
}
