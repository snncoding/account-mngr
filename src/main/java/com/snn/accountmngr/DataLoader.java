package com.snn.accountmngr;

import com.snn.accountmngr.dto.CustomerDto;
import com.snn.accountmngr.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CustomerService service;


    @Override
    public void run(String... args) throws Exception {
        service.create(CustomerDto.builder().name("John").surname("Wick").accounts(new ArrayList<>()).build());
        service.create(CustomerDto.builder().name("Jason").surname("Bourne").accounts(new ArrayList<>()).build());
        service.create(CustomerDto.builder().name("Trinity").surname("Neo").accounts(new ArrayList<>()).build());
    }
}
