package com.example.bank.service;

import com.example.bank.model.ExchangeRate;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeRateService {

    BigDecimal getExchangeRate(String fromCurrency, String toCurrency, LocalDate date);
}