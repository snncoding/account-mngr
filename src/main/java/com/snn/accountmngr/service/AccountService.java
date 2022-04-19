package com.snn.accountmngr.service;

import com.snn.accountmngr.dto.AccountDto;
import com.snn.accountmngr.dto.AccountInfoDto;
import com.snn.accountmngr.dto.AccountInitDto;
import com.snn.accountmngr.mapper.AccountMapper;
import com.snn.accountmngr.model.Account;
import com.snn.accountmngr.model.Customer;
import com.snn.accountmngr.model.Transaction;
import com.snn.accountmngr.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountService {

    private final CustomerService customerService;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDto create(AccountInitDto dto) {
        Customer customer = customerService.findById(dto.getCustomerId());
        Account account = new Account(dto.getInitCredit(), customer);

        if (BigDecimal.ZERO.compareTo(dto.getInitCredit()) < 0) {
            Transaction transaction = Transaction.
                    builder().
                    dateTime(LocalDateTime.now(ZoneId.systemDefault())).
                    credit(dto.getInitCredit()).
                    account(account).
                    build();

            account.getTransactions().add(transaction);
        }
        Account save = accountRepository.save(account);
        AccountDto accountDto = accountMapper.toAccountDto(save);

        return accountDto;
    }

    public List<AccountInfoDto> getAccountInfo() {
        List<Account> accounts = accountRepository.findAll();

        return Objects.requireNonNull(accounts).
                stream().
                map(accountMapper::toAccountInfoDto).
                collect(Collectors.toList());
    }


}
