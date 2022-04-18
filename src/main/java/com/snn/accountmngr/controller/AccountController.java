package com.snn.accountmngr.controller;

import com.snn.accountmngr.dto.AccountDto;
import com.snn.accountmngr.dto.AccountInfoDto;
import com.snn.accountmngr.dto.AccountInitDto;
import com.snn.accountmngr.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping
    @ApiOperation("Creates a new account")
    public ResponseEntity<AccountDto> createAcount(@Valid @RequestBody AccountInitDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    @ApiOperation("Gets account info")
    public ResponseEntity<List<AccountInfoDto>> getAccountInfo() {
        return ResponseEntity.ok(service.getAccountInfo());
    }
}
