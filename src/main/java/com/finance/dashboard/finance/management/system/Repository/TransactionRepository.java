package com.finance.dashboard.finance.management.system.Repository;

import com.finance.dashboard.finance.management.system.entities.TransactionType;
import com.finance.dashboard.finance.management.system.entities.Transactions;
import com.finance.dashboard.finance.management.system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Long> {

    List<Transactions> findByUser(User user);
List<Transactions> findByUserId(Long userid);
    List<Transactions> findByType(TransactionType type);
}
