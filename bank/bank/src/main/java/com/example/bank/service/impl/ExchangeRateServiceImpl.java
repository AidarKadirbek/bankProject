package com.example.bank.service.impl;

import com.example.bank.model.ExchangeRate;
import com.example.bank.repository.ExchangeRateRepository;
import com.example.bank.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final RestTemplate restTemplate;
    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ExchangeRateServiceImpl(RestTemplateBuilder restTemplateBuilder, ExchangeRateRepository exchangeRateRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency, LocalDate date) {
        String url = "https://api.example.com/exchangerates?from=" + fromCurrency + "&to=" + toCurrency + "&date=" + date;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String responseBody = response.getBody();
        // Здесь должна быть логика обработки ответа API и извлечения курса
        return BigDecimal.ONE; // Замените возврат константы на реальный курс
    }
}
