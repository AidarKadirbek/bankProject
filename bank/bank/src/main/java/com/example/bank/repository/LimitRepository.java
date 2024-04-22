package com.example.bank.repository;

import com.example.bank.model.ExpenseCategory;
import com.example.bank.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LimitRepository extends JpaRepository<Limit, Long> {

    Optional<Limit> findByCurrencyAndCategory(String currency, ExpenseCategory category);
}
