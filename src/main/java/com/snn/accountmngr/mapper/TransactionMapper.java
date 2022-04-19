package com.snn.accountmngr.mapper;

import com.snn.accountmngr.dto.TransactionDto;
import com.snn.accountmngr.model.Transaction;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
public class TransactionMapper {

    public TransactionDto toTransactionDto(Transaction from) {
        return TransactionDto.
                        builder().
                        id(from.getId()).
                        credit(from.getCredit()).
                        dateTime(from.getDateTime()).
                        build();
    }
}
