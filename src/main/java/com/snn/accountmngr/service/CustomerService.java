package com.snn.accountmngr.service;

import com.snn.accountmngr.dto.CustomerDto;
import com.snn.accountmngr.exception.MyEntityNotFoundException;
import com.snn.accountmngr.mapper.CustomerMapper;
import com.snn.accountmngr.model.Customer;
import com.snn.accountmngr.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@AllArgsConstructor
public class CustomerService {
    public static final String CUSTOMER = "customer";

    private final CustomerRepository repository;
    private final CustomerMapper customerMapper;

    public Set<CustomerDto> findAll() {
        List<Customer> entities = repository.findAll();
        if (entities == null)
            return null;
        return entities.stream().map(p -> customerMapper.toCustomerDto(p)).collect(toSet());
    }

    public CustomerDto create(CustomerDto dto) {
        Customer entity = repository.save(customerMapper.toCustomer(dto));
        return customerMapper.toCustomerDto(entity);
    }

    public Customer findById(Integer customerId) {
        return repository.findById(customerId).
                orElseThrow(() -> new MyEntityNotFoundException(customerId, CUSTOMER));
    }
}
