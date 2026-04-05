package com.finance.dashboard.finance.management.system.dtos;

import com.finance.dashboard.finance.management.system.entities.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class TransactionResponse {
    private Long id;
    private Double amount;
    private TransactionType type;
    private String category;
    private LocalDate date;
    private String notes;
}
