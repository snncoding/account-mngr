package com.snn.accountmngr.mapper;

import com.snn.accountmngr.dto.AccountDto;
import com.snn.accountmngr.dto.AccountInfoDto;
import com.snn.accountmngr.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Component
@AllArgsConstructor
public class AccountMapper {

    private final TransactionMapper transactionMapper;

    public AccountDto toAccountDto(Account from) {
        return AccountDto.
                builder().
                id(from.getId()).
                credit(from.getCredit()).
                transactions(
                        Objects.requireNonNull(from.getTransactions()).
                                stream().map(transactionMapper::toTransactionDto).
                                collect(Collectors.toSet())
                ).build();
    }

    public AccountInfoDto toAccountInfoDto(Account from) {
        return AccountInfoDto.
                builder().
                name(from.getCustomer().getName()).
                surname(from.getCustomer().getSurname()).
                credit(from.getCredit()).
                transactions(
                        Objects.requireNonNull(from.getTransactions()).
                                stream().
                                map(transactionMapper::toTransactionDto).
                                collect(Collectors.toSet())
                ).
                build();
    }

    public Account toAccount(AccountDto from) {
        return new Account(from.getId(), from.getCredit(), null, null);
    }
}
