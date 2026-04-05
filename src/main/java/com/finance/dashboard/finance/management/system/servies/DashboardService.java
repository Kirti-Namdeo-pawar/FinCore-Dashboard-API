package com.finance.dashboard.finance.management.system.servies;

import com.finance.dashboard.finance.management.system.Repository.TransactionRepository;
import com.finance.dashboard.finance.management.system.Repository.UserRepository;
import com.finance.dashboard.finance.management.system.entities.TransactionType;
import com.finance.dashboard.finance.management.system.entities.Transactions;
import com.finance.dashboard.finance.management.system.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final TransactionRepository transRepo;
private final UserRepository userRepo;


    public Map<String, Object> getSummary(String username) {

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Transactions> trans;

        // 🔥 ROLE BASED LOGIC
        if (user.getRole().name().equals("ROLE_ADMIN") ||
                user.getRole().name().equals("ROLE_ANALYST")) {

            // Admin + Analyst → ALL DATA
            trans = transRepo.findAll();

        } else {

            // Viewer → ONLY OWN DATA
            trans = transRepo.findByUserId(user.getId());
        }

        // 🔥 COMMON CALCULATION
        double income = trans.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .mapToDouble(Transactions::getAmount)
                .sum();

        double expense = trans.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transactions::getAmount)
                .sum();

        double balance = income - expense;

        Map<String, Object> response = new HashMap<>();
        response.put("totalIncome", income);
        response.put("totalExpense", expense);
        response.put("netBalance", balance);

        return response;
    }
}
/*
Provide APIs or backend logic that can return summary level data for a dashboard.

Examples include:

Total income
Total expenses
Net balance
Category wise totals
Recent activity
Monthly or weekly trends
The purpose here is to show how you design backend endpoints or service logic for aggregated data, not just basic CRUD operations.
 */