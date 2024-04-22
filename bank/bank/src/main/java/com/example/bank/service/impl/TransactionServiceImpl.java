package com.example.bank.service.impl;

import com.example.bank.model.Limit;
import com.example.bank.model.Transaction;
import com.example.bank.repository.LimitRepository;
import com.example.bank.repository.TransactionRepository;
import com.example.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LimitRepository limitRepository; // Внедрение LimitRepository

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findTransactionsExceedingLimit() {
        List<Transaction> transactions = transactionRepository.findAll();
        Map<String, BigDecimal> sumByCurrency = new HashMap<>();

        // Суммирование транзакций по валютам
        for (Transaction transaction : transactions) {
            String currency = transaction.getCurrencyShortname();
            BigDecimal sum = transaction.getSum();
            if (sumByCurrency.containsKey(currency)) {
                sumByCurrency.put(currency, sumByCurrency.get(currency).add(sum));
            } else {
                sumByCurrency.put(currency, sum);
            }
        }

        // Получение текущих лимитов
        List<Limit> limits = limitRepository.findAll(); // Теперь будет работать
        Map<String, BigDecimal> limitByCurrency = new HashMap<>();
        for (Limit limit : limits) {
            String currency = limit.getCurrency();
            BigDecimal amount = limit.getAmount();
            if (limitByCurrency.containsKey(currency)) {
                limitByCurrency.put(currency, limitByCurrency.get(currency).add(amount));
            } else {
                limitByCurrency.put(currency, amount);
            }
        }

        // Пометка транзакций, превышающих лимит
        List<Transaction> exceededTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            String currency = transaction.getCurrencyShortname();
            BigDecimal transactionSum = transaction.getSum();
            BigDecimal limitAmount = limitByCurrency.getOrDefault(currency, BigDecimal.ZERO);
            if (sumByCurrency.get(currency).compareTo(limitAmount) > 0) {
                transaction.setLimitExceeded(true);
                exceededTransactions.add(transaction);
            }
        }

        return exceededTransactions;
    }
}

