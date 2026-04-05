package com.finance.dashboard.finance.management.system.controllers;

import com.finance.dashboard.finance.management.system.dtos.TransactionRequest;
import com.finance.dashboard.finance.management.system.dtos.TransactionResponse;
import com.finance.dashboard.finance.management.system.dtos.UpdateTransactionRequest;
import com.finance.dashboard.finance.management.system.servies.TransactionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private  final TransactionServiceImpl tranService;
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> create(@RequestBody @Valid TransactionRequest request, Authentication auth) {
tranService.createTransaction(request, auth.getName());
        return ResponseEntity.ok("Transaction created");
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public List<TransactionResponse> getAll(Authentication auth) {
        return tranService.getTransactions(auth.getName());
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody UpdateTransactionRequest request, Authentication auth) {
        tranService.updateTransaction(id, request, auth.getName());
        return ResponseEntity.ok("Record is updated successfully");
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id, Authentication auth) {
 tranService.deleteTransaction(id, auth.getName());
        return ResponseEntity.ok("Record is deleted successfully");
    }
}
/*
You may define roles such as:

Viewer: Can only view dashboard data
Analyst: Can view records and access insights
Admin: Can create, update, and manage records and users
 */
