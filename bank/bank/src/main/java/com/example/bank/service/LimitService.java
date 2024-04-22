package com.example.bank.service;

import com.example.bank.model.Limit;

import java.math.BigDecimal;
import java.util.Optional;

public interface LimitService {

    Limit setMonthlyLimit(BigDecimal amount, String currency, String category);

    Optional<Limit> getCurrentLimit(String currency, String category);
}