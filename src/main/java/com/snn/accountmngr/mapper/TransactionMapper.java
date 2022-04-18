package com.snn.accountmngr.mapper;

import com.snn.accountmngr.dto.TransactionDto;
import com.snn.accountmngr.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
public class TransactionMapper {

    public Set<TransactionDto> toTransaction(Set<Transaction> from) {
        if (from == null)
            return null;
        return from.
                stream().
                map(k -> TransactionDto.
                        builder().
                        id(k.getId()).
                        credit(k.getCredit()).
                        dateTime(k.getDateTime()).
                        build()).
                collect(toSet());
    }
}
