package com.snn.accountmngr.mapper;

import com.snn.accountmngr.dto.CustomerDto;
import com.snn.accountmngr.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
                accounts(accountMapper.toAccountDtos(entity.getAccounts())).
                build();
    }


    private Customer mapCustomerDtoToCustomer(CustomerDto from) {
        return Customer.
                builder().
                id(from.getId()).
                name(from.getName()).
                surname(from.getSurname()).
                accounts(accountMapper.toAccounts(from.getAccounts())).
                build();
    }

    public Customer toCustomer(CustomerDto from) {
        return Customer.
                builder().
                id(from.getId()).
                name(from.getName()).
                surname(from.getSurname()).
                accounts(accountMapper.toAccounts(from.getAccounts())).
                build();
    }
}
