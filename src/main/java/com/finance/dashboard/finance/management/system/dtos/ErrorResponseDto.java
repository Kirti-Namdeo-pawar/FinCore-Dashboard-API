package com.finance.dashboard.finance.management.system.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
public class ErrorResponseDto {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String msg;
}
/*
{
  "timestamp": "2026-05-14T10:30:00",
  "status": 404,
  "error": "NOT_FOUND",
  "message": "Transaction not found with id: 5"
}
 */