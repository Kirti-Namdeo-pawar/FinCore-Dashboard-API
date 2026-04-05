package com.finance.dashboard.finance.management.system.dtos;

import com.finance.dashboard.finance.management.system.entities.TransactionType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTransactionRequest {
    private Double amount;
    private TransactionType type;
    private String category;
    private LocalDate date;
    private String notes;
}
