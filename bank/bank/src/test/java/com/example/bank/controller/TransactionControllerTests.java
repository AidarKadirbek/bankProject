package com.example.bank.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveTransaction() throws Exception {
        String jsonRequest = "{\"accountFrom\":1,\"accountTo\":2,\"currencyShortname\":\"USD\",\"sum\":500.0}";

        mockMvc.perform(post("/transactions/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencyShortname").value("USD"))
                .andExpect(jsonPath("$.sum").value(500.0));
    }

    @Test
    public void testFindTransactionsExceedingLimit() throws Exception {
        mockMvc.perform(get("/transactions/exceededLimit"))
                .andExpect(status().isOk());
        // Add more assertions based on expected result
    }
}

