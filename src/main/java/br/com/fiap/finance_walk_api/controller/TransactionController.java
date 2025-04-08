package br.com.fiap.finance_walk_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.finance_walk_api.model.Transaction;
import br.com.fiap.finance_walk_api.repository.TransactionRepository;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.web.bind.annotation.GetMapping;


//@RestController
//@RequestMapping("/transactions")
@Slf4j
public class TransactionController {
    
    record TransactionFilter(String description, LocalDate date, BigDecimal amount){}

    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public List<Transaction> index(TransactionFilter filter) {
        log.info("Buscando transações com descrição " + filter.description + " e data " + filter.date); 

        // if (description != null && date != null) return repository.findByDescriptionContainingIgnoringCaseAndDate(description, date);
        // if (description != null) return repository.findByDescriptionContainingIgnoringCase(description);
        // if (date != null) return repository.findByDate(description);

        var probe = Transaction.builder()
                        .description(filter.description)
                        .date(filter.date)
                        .amount(filter.amount)
                        .build();

        var matcher = ExampleMatcher.matchingAll()
                        .withIgnoreCase()
                        .withIgnoreNullValues()
                        .withStringMatcher(StringMatcher.CONTAINING);

        var example = Example.of(probe, matcher);


        return repository.findAll(example);
    }
    

}
