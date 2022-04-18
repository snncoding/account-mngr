package com.snn.accountmngr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@SpringBootApplication
public class AccountMngrApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountMngrApplication.class, args);
    }

    @GetMapping("/test")
    public String sayHalle() {
        return String.format("Hello Dear, Date: %s", new Date().toString());
    }
}
