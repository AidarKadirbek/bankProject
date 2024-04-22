package com.example.bank.controller;

import com.example.bank.model.Limit;
import com.example.bank.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/limits")
public class LimitController {

    @Autowired
    private LimitService limitService;

    @PostMapping("/set")
    public ResponseEntity<Limit> setMonthlyLimit(
            @RequestParam BigDecimal amount,
            @RequestParam String currency,
            @RequestParam String category) {

        Limit limit = limitService.setMonthlyLimit(amount, currency, category);
        return ResponseEntity.ok(limit);
    }

    @GetMapping("/get")
    public ResponseEntity<Limit> getCurrentLimit(
            @RequestParam String currency,
            @RequestParam String category) {

        Optional<Limit> limit = limitService.getCurrentLimit(currency, category);
        return limit.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
