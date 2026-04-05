package com.finance.dashboard.finance.management.system.dtos;

import com.finance.dashboard.finance.management.system.entities.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionRequest {
    @NotNull
    private Double amount;

    @NotNull
    private TransactionType type;

    @NotBlank
    private String category;

    @NotNull
    private LocalDate date;

    private String notes;
    private String username;
}
