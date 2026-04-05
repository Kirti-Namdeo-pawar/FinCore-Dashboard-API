package com.finance.dashboard.finance.management.system.servies;

import com.finance.dashboard.finance.management.system.Repository.TransactionRepository;
import com.finance.dashboard.finance.management.system.Repository.UserRepository;
import com.finance.dashboard.finance.management.system.dtos.TransactionRequest;
import com.finance.dashboard.finance.management.system.dtos.TransactionResponse;
import com.finance.dashboard.finance.management.system.dtos.UpdateTransactionRequest;
import com.finance.dashboard.finance.management.system.entities.Transactions;
import com.finance.dashboard.finance.management.system.entities.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionServiceInterface {

    private final TransactionRepository transactionRepo;
    private final UserRepository userRepo;
    @Override
    public void createTransaction(TransactionRequest req, String username) {
User user=userRepo.findByUsername(req.getUsername()).orElseThrow(()->new RuntimeException("User not found..."));
Transactions trans=new Transactions();
        trans.setAmount(req.getAmount());
        trans.setType(req.getType());
        trans.setCategory(req.getCategory());
        trans.setDate(req.getDate());
        trans.setNotes(req.getNotes());
        trans.setUser(user);
        transactionRepo.save(trans);
    }

    @Override
    public List<TransactionResponse> getTransactions(String username) {

        User user=userRepo.findByUsername(username).orElseThrow(()->new RuntimeException("User not Found..."));

        List<Transactions> trans;

        // 🔥 KEY LOGIC
        if (user.getRole().name().equals("ROLE_ADMIN") ||
                user.getRole().name().equals("ROLE_ANALYST")) {

            // admin + analyst → see all
            trans = transactionRepo.findAll();

        } else {

            // viewer → not allowed ideally (but safe fallback)
            trans = transactionRepo.findByUserId(user.getId());
        }

        return trans.stream()
                .map(this::mapToResponse)
                .toList();

    }

    private TransactionResponse mapToResponse(Transactions t) {
        return new TransactionResponse(
                t.getId(),
                t.getAmount(),
                t.getType(),
                t.getCategory(),
                t.getDate(),
                t.getNotes()
        );
    }

    @Override
    public void updateTransaction(Long id, UpdateTransactionRequest req, String username) {
Transactions t=transactionRepo.findById(id).orElseThrow(()->new RuntimeException("No Transaction records found by this id"));

        if (req.getAmount() != null) t.setAmount(req.getAmount());
        if (req.getCategory() != null) t.setCategory(req.getCategory());
        if (req.getType() != null) t.setType(req.getType());
        if (req.getDate() != null) t.setDate(req.getDate());
        if (req.getNotes() != null) t.setNotes(req.getNotes());

        transactionRepo.save(t);
    }

    @Override
    public void deleteTransaction(Long id, String username) {
        Transactions t = transactionRepo.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));


        transactionRepo.delete(t);
    }
}
