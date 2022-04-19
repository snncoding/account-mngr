package com.snn.accountmngr.service;

import com.snn.accountmngr.dto.AccountDto;
import com.snn.accountmngr.dto.AccountInfoDto;
import com.snn.accountmngr.dto.AccountInitDto;
import com.snn.accountmngr.dto.CustomerDto;
import com.snn.accountmngr.mapper.AccountMapper;
import com.snn.accountmngr.mapper.TransactionMapper;
import com.snn.accountmngr.model.Account;
import com.snn.accountmngr.model.Customer;
import com.snn.accountmngr.model.Transaction;
import com.snn.accountmngr.repository.AccountRepository;
import com.snn.accountmngr.repository.CustomerRepository;
import com.snn.accountmngr.repository.TransactionRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CustomerRepository customerRepo;
    @Mock
    private CustomerService customerService;
    private AccountMapper accountMapper;
    private TransactionMapper transactionMapper;

    private AccountService accountService;

    private List<Account> accounts;
    private List<Customer> customers;
    private CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        transactionMapper = new TransactionMapper();
        accountMapper = new AccountMapper(transactionMapper);
        accountService = new AccountService(customerService, accountRepository, accountMapper);

        customers = Lists.list(
                new Customer(1, "Neo", "Tirinity", null),
                new Customer(2, "John", "Weyn", null)
        );
        customerDto = new CustomerDto(1, "Neo", "Tirinity", null);
        List<Transaction> transactions = Lists.list(new Transaction(1, BigDecimal.TEN, LocalDateTime.now(), null));
        List<Transaction> transactions2 = Lists.list(new Transaction(2, BigDecimal.TEN, LocalDateTime.now(), null));

        accounts = Lists.list(
                new Account(1, BigDecimal.TEN, customers.get(0), new HashSet<>(transactions)),
                new Account(2, BigDecimal.ZERO, customers.get(1), new HashSet<>(transactions2))
        );


    }

    @Test
    void create() {
        Account account = accounts.get(0);

        when(customerService.findById(1)).thenReturn(customers.get(0));

        Account initAccount = new Account(account.getCredit(), account.getCustomer());
        when(accountRepository.save(any())).thenReturn(account);

        AccountDto accountDto = accountService.create(new AccountInitDto(account.getCustomer().getId(), account.getCredit()));

        assertNotNull(accountDto,"After account create service must return new AccountDto, but it is returned null");
        assertEquals(accountDto.getId(), account.getId(), "Expected object not equal");
    }

    @Test
    void getAccountInfo() {
        when(accountRepository.findAll()).thenReturn(accounts);
        List<AccountInfoDto> accountInfo = accountService.getAccountInfo();

        assertNotNull(accountInfo, "Service result must not be null");
        assertTrue(accountInfo.size() > 0, "Return list size must be bigger than zero");
        assertEquals(accountInfo.get(0).getName(), accounts.get(0).getCustomer().getName(), "Name must be equal");
        assertEquals(accountInfo.get(0).getSurname(), accounts.get(0).getCustomer().getSurname(), "Surname must be equal");
    }
}