package com.example.bank.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bank.model.ExchangeRate;
import com.example.bank.repository.ExchangeRateRepository;
import com.example.bank.service.ExchangeRateService;
import com.example.bank.service.impl.ExchangeRateServiceImpl;


@ExtendWith(MockitoExtension.class) // Используем MockitoExtension для поддержки Mockito в JUnit 5
public class ExchangeRateServiceTest {

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;

    @Test
    public void testGetExchangeRate() {
        String fromCurrency = "USD";
        String toCurrency = "KZT";
        LocalDate date = LocalDate.now();

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setFromCurrency(fromCurrency);
        exchangeRate.setToCurrency(toCurrency);
        exchangeRate.setRate(BigDecimal.valueOf(420.0));

        when(exchangeRateRepository.findByFromCurrencyAndToCurrencyAndDate(fromCurrency, toCurrency, date))
                .thenReturn(Optional.of(exchangeRate));

        BigDecimal rate = exchangeRateService.getExchangeRate(fromCurrency, toCurrency, date);

        assertNotNull(rate);
        assertEquals(BigDecimal.valueOf(420.0), rate);
    }
}


