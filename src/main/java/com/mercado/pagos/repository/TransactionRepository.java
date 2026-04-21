package com.mercado.pagos.repository;

import com.mercado.pagos.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Page<Transaction> findBySender_IdOrReceiver_IdOrderByCreatedAtDesc(
            UUID senderId,
            UUID receiverId,
            Pageable pageable
    );

}
