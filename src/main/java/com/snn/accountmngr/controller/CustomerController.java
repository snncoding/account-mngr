package com.snn.accountmngr.controller;

import com.snn.accountmngr.dto.CustomerDto;
import com.snn.accountmngr.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public Set<CustomerDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public CustomerDto create(@RequestBody CustomerDto dto) {
        return service.create(dto);
    }
}
