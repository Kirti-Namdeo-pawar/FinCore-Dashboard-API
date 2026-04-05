package com.finance.dashboard.finance.management.system.servies;

import com.finance.dashboard.finance.management.system.dtos.TransactionRequest;
import com.finance.dashboard.finance.management.system.dtos.TransactionResponse;
import com.finance.dashboard.finance.management.system.dtos.UpdateTransactionRequest;

import java.util.List;

public interface TransactionServiceInterface {
    void createTransaction(TransactionRequest request, String username);

    List<TransactionResponse> getTransactions(String username);

    void updateTransaction(Long id, UpdateTransactionRequest request, String username);

    void deleteTransaction(Long id, String username);
}
