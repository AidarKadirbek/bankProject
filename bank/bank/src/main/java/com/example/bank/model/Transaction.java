package com.example.bank.model;

import java.math.BigDecimal;

public class Transaction {
    private Long id;
    private String currencyShortname;
    private BigDecimal sum;
    private boolean limitExceeded;

    public Transaction() {
    }

    public Transaction(Long id, String currencyShortname, BigDecimal sum) {
        this.id = id;
        this.currencyShortname = currencyShortname;
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyShortname() {
        return currencyShortname;
    }

    public void setCurrencyShortname(String currencyShortname) {
        this.currencyShortname = currencyShortname;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
    public boolean isLimitExceeded() {
        return limitExceeded;
    }

    public void setLimitExceeded(boolean limitExceeded) {
        this.limitExceeded = limitExceeded;
    }
}
