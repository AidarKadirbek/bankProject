package com.example.bank.service.impl;

import com.example.bank.service.LimitService; // Импорт интерфейса LimitService

import com.example.bank.model.ExpenseCategory;
import com.example.bank.model.Limit;
import com.example.bank.repository.LimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class LimitServiceImpl implements LimitService {

    @Autowired
    private LimitRepository limitRepository;

    @Override
    public Limit setMonthlyLimit(BigDecimal amount, String currency, String category) {
        ExpenseCategory expenseCategory = parseExpenseCategory(category);
        Optional<Limit> existingLimit = limitRepository.findByCurrencyAndCategory(currency, expenseCategory);
        if (existingLimit.isPresent()) {
            return existingLimit.get(); // Не обновляем существующий лимит
        }

        Limit limit = new Limit();
        limit.setAmount(amount);
        limit.setCurrency(currency);
        limit.setCategory(expenseCategory);
        limit.setStartDate(LocalDate.now());

        return limitRepository.save(limit);
    }

    @Override
    public Optional<Limit> getCurrentLimit(String currency, String category) {
        ExpenseCategory expenseCategory = parseExpenseCategory(category);
        return limitRepository.findByCurrencyAndCategory(currency, expenseCategory);
    }

    private ExpenseCategory parseExpenseCategory(String category) {
        try {
            return ExpenseCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid ExpenseCategory: " + category);
        }
    }
}

