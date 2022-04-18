package com.snn.accountmngr.mapper;

import com.snn.accountmngr.dto.AccountDto;
import com.snn.accountmngr.dto.AccountInfoDto;
import com.snn.accountmngr.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
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
                build();
    }

    public List<AccountInfoDto> toAccountInfoDto(List<Account> from) {
        return from.
                stream().
                map(p -> AccountInfoDto.
                        builder().
                        name(p.getCustomer().getName()).
                        surname(p.getCustomer().getSurname()).
                        credit(p.getCredit()).
                        transactions(transactionMapper.toTransaction(p.getTransactions())).
                        build()).collect(toList());
    }

    public List<AccountDto> toAccountDtos(Set<Account> from) {
        if (from == null)
            return null;
        return from.
                stream().
                map(k -> toAccountDto(k)).
                collect(Collectors.toList());
    }


    public Set<Account> toAccounts(List<AccountDto> from) {
        if (from == null)
            return null;

        return from.
                stream().
                map(p -> Account.
                        builder().
                        id(p.getId()).
                        credit(p.getCredit()).
                        build()).collect(toSet());
    }
}
