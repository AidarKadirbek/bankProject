package com.example.bank.service;

import com.example.bank.model.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction saveTransaction(Transaction transaction);

    List<Transaction> findTransactionsExceedingLimit();
}
