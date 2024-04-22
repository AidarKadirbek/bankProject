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

import com.example.bank.model.ExpenseCategory;
import com.example.bank.model.Limit;
import com.example.bank.repository.LimitRepository;
import com.example.bank.service.LimitService;
import com.example.bank.service.impl.LimitServiceImpl;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class LimitServiceTest {

    @Mock
    private LimitRepository limitRepository;

    @InjectMocks
    private LimitServiceImpl limitService;

    @Test
    public void testSetMonthlyLimit() {
        BigDecimal amount = BigDecimal.valueOf(1000.0);
        String currency = "USD";
        String category = "PRODUCT";

        Limit existingLimit = new Limit();
        existingLimit.setCurrency(currency);
        existingLimit.setCategory(ExpenseCategory.PRODUCT);

        when(limitRepository.findByCurrencyAndCategory(currency, ExpenseCategory.PRODUCT)).thenReturn(Optional.of(existingLimit));

        Limit limit = limitService.setMonthlyLimit(amount, currency, category);

        assertNotNull(limit);
        assertEquals(existingLimit, limit);
    }

    @Test
    public void testGetCurrentLimit() {
        String currency = "USD";
        String category = "SERVICE";

        Limit limit = new Limit();
        limit.setCurrency(currency);
        limit.setCategory(ExpenseCategory.SERVICE);

        when(limitRepository.findByCurrencyAndCategory(currency, ExpenseCategory.SERVICE)).thenReturn(Optional.of(limit));

        Optional<Limit> result = limitService.getCurrentLimit(currency, category);

        assertTrue(result.isPresent());
        assertEquals(limit, result.get());
    }
}
