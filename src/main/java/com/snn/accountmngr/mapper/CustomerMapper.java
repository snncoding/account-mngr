package com.snn.accountmngr.mapper;

import com.snn.accountmngr.dto.AccountDto;
import com.snn.accountmngr.dto.CustomerDto;
import com.snn.accountmngr.model.Account;
import com.snn.accountmngr.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomerMapper {

    private final AccountMapper accountMapper;

    public CustomerDto toCustomerDto(Customer entity) {
        return CustomerDto.
                builder().
                id(entity.getId()).
                name(entity.getName()).
                surname(entity.getSurname()).
                accounts(
                        Objects.requireNonNull(entity.getAccounts()).
                                stream().
                                map(accountMapper::toAccountDto).
                                collect(Collectors.toList())).
                build();
    }

    public Customer toCustomer(CustomerDto from) {
        return Customer.
                builder().
                id(from.getId()).
                name(from.getName()).
                surname(from.getSurname()).
                accounts(
                        (from.getAccounts() == null ? new ArrayList<AccountDto>() : from.getAccounts()).
                                stream().
                                map(accountMapper::toAccount).
                                collect(Collectors.toSet())
                ).
                build();
    }
}
