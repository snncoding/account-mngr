package com.snn.accountmngr.controller;

import com.snn.accountmngr.dto.CustomerDto;
import com.snn.accountmngr.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    @ApiOperation("Gets all customer info")
    public ResponseEntity<Set<CustomerDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @ApiOperation("Creates a new customer")
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }
}
