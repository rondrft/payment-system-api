package com.mercado.pagos.controller;

import com.mercado.pagos.dto.TransferRequestDto;
import com.mercado.pagos.dto.TransferResponseDto;
import com.mercado.pagos.model.Account;
import com.mercado.pagos.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponseDto> transfer (
            @AuthenticationPrincipal Account account, @Valid @RequestBody TransferRequestDto request
    ) {
        TransferResponseDto response = transactionService.transfer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/history")
    public ResponseEntity<Page<TransferResponseDto>> getHistory(
            @AuthenticationPrincipal Account account, Pageable pageable
    ) {
        Page<TransferResponseDto> response = transactionService.getTransactionHistory(account.getId(), pageable);
        return ResponseEntity.ok(response);
    }

}
