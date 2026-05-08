package com.mercado.pagos.controller;

import com.mercado.pagos.dto.AuthResponseDto;
import com.mercado.pagos.model.Account;
import com.mercado.pagos.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/me")
    public ResponseEntity<AuthResponseDto> getMe(
            @AuthenticationPrincipal Account account
            ) {
        return ResponseEntity.ok(accountService.getMe(account));
    }

}
