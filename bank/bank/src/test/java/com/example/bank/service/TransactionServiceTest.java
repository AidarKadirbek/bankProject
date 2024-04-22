package com.example.bank.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bank.model.Transaction;
import com.example.bank.repository.TransactionRepository;
import com.example.bank.service.impl.TransactionServiceImpl;

@SpringBootTest
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService = new TransactionServiceImpl();

    @Test
    public void testSaveTransaction() {
        Transaction transaction = new Transaction(2L, "USD", BigDecimal.valueOf(700.0));
        transaction.setCurrencyShortname("USD");
        transaction.setSum(BigDecimal.valueOf(500.0));

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);

        assertNotNull(savedTransaction);
        assertEquals("USD", savedTransaction.getCurrencyShortname());
        assertEquals(BigDecimal.valueOf(500.0), savedTransaction.getSum());
    }

    @Test
    public void testFindTransactionsExceedingLimit() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1L, "USD", BigDecimal.valueOf(600.0)));
        transactions.add(new Transaction(2L, "USD", BigDecimal.valueOf(700.0)));

        when(transactionRepository.findByLimitExceededTrue()).thenReturn(transactions);

        List<Transaction> exceededTransactions = transactionService.findTransactionsExceedingLimit();

        assertNotNull(exceededTransactions);
        assertEquals(2, exceededTransactions.size());
    }
}
