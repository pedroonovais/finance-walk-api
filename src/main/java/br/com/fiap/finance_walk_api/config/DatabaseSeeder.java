package br.com.fiap.finance_walk_api.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.finance_walk_api.model.Category;
import br.com.fiap.finance_walk_api.model.Transaction;
import br.com.fiap.finance_walk_api.model.TransactionType;
import br.com.fiap.finance_walk_api.repository.CategoryRepository;
import br.com.fiap.finance_walk_api.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @PostConstruct
    public void init() {
        var categories = List.of(
                Category.builder().name("Educação").icon("Book").build(),
                Category.builder().name("Lazer").icon("Dices").build(),
                Category.builder().name("Transporte").icon("Bus").build(),
                Category.builder().name("Salário").icon("Money").build());

        categoryRepository.saveAll(categories);

        var descriptions = List.of(
                "Uber",
                "Cinema",
                "Conta de Luz",
                "Faculdade",
                "Supermercado",
                "Aluguel",
                "Combustível",
                "Restaurante",
                "Farmácia",
                "Internet",
                "Academia",
                "Manutenção do Carro",
                "Seguro de Saúde",
                "Mensalidade do Celular",
                "Estacionamento",
                "Roupas",
                "Assinatura Netflix",
                "Café",
                "Curso Online",
                "Pet Shop");

        var transactions = new ArrayList<Transaction>();
        for (int i = 0; i < 50; i++) {
            transactions.add(
                    Transaction.builder()
                            .description(descriptions.get(new Random().nextInt(descriptions.size())))
                            .amount(BigDecimal.valueOf(10 +new Random().nextDouble() * 500))
                            .date(LocalDate.now().minusDays(new Random().nextInt(365)))
                            .type(TransactionType.EXPENSE)
                            .category(categories.get(new Random().nextInt(categories.size())))
                            .build()
                            );
        }

        transactionRepository.saveAll(transactions);
    }
}
