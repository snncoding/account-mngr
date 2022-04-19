package com.snn.accountmngr.service;

import com.snn.accountmngr.dto.CustomerDto;
import com.snn.accountmngr.exception.MyEntityNotFoundException;
import com.snn.accountmngr.mapper.AccountMapper;
import com.snn.accountmngr.mapper.CustomerMapper;
import com.snn.accountmngr.mapper.TransactionMapper;
import com.snn.accountmngr.model.Account;
import com.snn.accountmngr.model.Customer;
import com.snn.accountmngr.model.Transaction;
import com.snn.accountmngr.repository.CustomerRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;
    private CustomerMapper customerMapper;
    private AccountMapper accountMapper;

    @InjectMocks
    private CustomerService customerService;

    List<Customer> customers;
    private Set<Transaction> transactions;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        accountMapper = new AccountMapper(new TransactionMapper());
        customerMapper = new CustomerMapper(accountMapper);
        customerService = new CustomerService(repository, customerMapper);

        transactions = Lists.list(new Transaction(1, BigDecimal.TEN, LocalDateTime.now(), null)).stream().collect(Collectors.toSet());
        Set<Transaction> transactions2 = Lists.list(new Transaction(2, BigDecimal.TEN, LocalDateTime.now(), null)).stream().collect(Collectors.toSet());

        customers = Lists.list(
                new Customer(1, "Name", "Surname",
                        new HashSet<>(Lists.list(new Account(1, BigDecimal.TEN, null, transactions)))
                ),
                new Customer(2, "Name2", "Surname2",
                        new HashSet<>(Lists.list(new Account(2, BigDecimal.ZERO, null, transactions2)))
                )
        );
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(customers);

        Set<CustomerDto> customerDtos = customerService.findAll();
        assertNotNull(customerDtos, "Return value must have value");
        assertEquals(customerDtos.size(), customers.size(), "Return value must have same size wıth customers");
    }

    @Test
    void create() {
        Customer customer = customers.get(0);
        when(repository.save(any())).thenReturn(customer);

        CustomerDto customerDto = customerService.create(
                new CustomerDto(
                        null,
                        customer.getName(),
                        customer.getSurname(),
                        customer.getAccounts().
                                stream().
                                map(accountMapper::toAccountDto).
                                collect(Collectors.toList())
                )
        );

        assertEquals(customerDto.getId(), customer.getId());
    }

    @Test
    void findById() {
        Customer customer = customers.get(0);

        when(repository.findById(customer.getId())).thenReturn(Optional.of(customer));

        Customer result = customerService.findById(customer.getId());

        assertEquals(result, customer, "Result not equal with expected object");

        assertThrows(MyEntityNotFoundException.class, () -> customerService.findById(1111), "Expected Not found exception");

    }
}