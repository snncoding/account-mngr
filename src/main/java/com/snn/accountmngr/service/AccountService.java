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
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final CustomerService customerService;
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;
    private final AccountMapper accountMapper;

    public AccountDto create(AccountInitDto dto) {
        Customer customer = customerService.findById(dto.getCustomerId());
        Account account = Account.builder().
                credit(dto.getInitCredit()).
                customer(customer).
                transactions(new HashSet<>()).
                build();

        AccountDto accountDto = accountMapper.toAccountDto(accountRepository.save(account));

        if (BigDecimal.ZERO.compareTo(dto.getInitCredit()) < 0) {
            Transaction transaction = Transaction.
                    builder().
                    dateTime(LocalDateTime.now(ZoneId.systemDefault())).
                    credit(dto.getInitCredit()).
                    account(account).
                    build();
            transactionService.create(transaction);
        }
        return accountDto;
    }

    public List<AccountInfoDto> getAccountInfo() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.toAccountInfoDto(accounts);
    }


}
