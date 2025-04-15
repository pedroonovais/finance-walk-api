package br.com.fiap.finance_walk_api.specfication;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.finance_walk_api.model.Transaction;
import br.com.fiap.finance_walk_api.model.TransactionFilter;

public abstract class TransactionSpecfication {
    
    public static Specification<Transaction> withFilters(TransactionFilter filter){
        return (root, query, cb) -> {
            // Predicado
            List<Predicate> predicates = new ArrayList<>();

            if(filter.description() != null){
                predicates.add( cb.like(
                    cb.lower(root.get("description")), "%" + filter.description().toLowerCase() + "%"
                ));
            }

            if (filter.startDate() != null && filter.endDate() != null) {
                predicates.add(
                    cb.between(root.get("date"), filter.startDate(), filter.endDate())
                );
            }

            if (filter.startDate() != null && filter.endDate() == null) {
                predicates.add(
                    cb.equal(root.get("date"), filter.startDate())    
                );
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }

}
