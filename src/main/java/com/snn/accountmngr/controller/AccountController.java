package com.snn.accountmngr.controller;

import com.snn.accountmngr.dto.AccountDto;
import com.snn.accountmngr.dto.AccountInfoDto;
import com.snn.accountmngr.dto.AccountInitDto;
import com.snn.accountmngr.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping
    public AccountDto createAcount(@RequestBody AccountInitDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<AccountInfoDto> getAccountInfo() {
        return service.getAccountInfo();
    }
}
