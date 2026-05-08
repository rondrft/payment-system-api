package com.mercado.pagos.service;

import com.mercado.pagos.dto.TransferRequestDto;
import com.mercado.pagos.dto.TransferResponseDto;
import com.mercado.pagos.exception.AccountNotFoundException;
import com.mercado.pagos.exception.InsufficientFundsException;
import com.mercado.pagos.model.Account;
import com.mercado.pagos.model.Transaction;
import com.mercado.pagos.model.TransactionStatus;
import com.mercado.pagos.repository.AccountRepository;
import com.mercado.pagos.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public TransferResponseDto transfer(TransferRequestDto request) {

        Account sender = accountRepository.findByIdWithLock(request.getSenderId())
                .orElseThrow(() -> new AccountNotFoundException("Sender not found"));

        Account receiver = accountRepository.findByIdWithLock(request.getReceiverId())
                .orElseThrow(() -> new AccountNotFoundException("Receiver not found"));

        if (sender.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        sender.setBalance(sender.getBalance().subtract(request.getAmount()));
        receiver.setBalance(receiver.getBalance().add(request.getAmount()));

        accountRepository.save(sender);
        accountRepository.save(receiver);

        Transaction transaction = Transaction.builder()
                .sender(sender)
                .receiver(receiver)
                .amount(request.getAmount())
                .status(TransactionStatus.COMPLETED)
                .build();

        transactionRepository.save(transaction);

        return TransferResponseDto.builder()
                .transactionId(transaction.getId())
                .senderName(sender.getName())
                .receiverName(receiver.getName())
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .createdAt(transaction.getCreatedAt())
                .build();
    }

    public Page<TransferResponseDto> getTransactionHistory(UUID accountId, Pageable pageable) {
        return transactionRepository
                .findBySender_IdOrReceiver_IdOrderByCreatedAtDesc(accountId, accountId, pageable)
                .map(transaction -> TransferResponseDto.builder()
                        .transactionId(transaction.getId())
                        .senderName(transaction.getSender().getName())
                        .receiverName(transaction.getReceiver().getName())
                        .amount(transaction.getAmount())
                        .status(transaction.getStatus())
                        .createdAt(transaction.getCreatedAt())
                        .build());
    }
}
