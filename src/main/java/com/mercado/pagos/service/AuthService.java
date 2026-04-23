package com.mercado.pagos.service;

import com.mercado.pagos.dto.AuthResponseDto;
import com.mercado.pagos.dto.RegisterRequestDto;
import com.mercado.pagos.model.Account;
import com.mercado.pagos.model.Role;
import com.mercado.pagos.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public AuthResponseDto register(RegisterRequestDto dto) {

        accountService.checkEmailNotExist(dto.getEmail());

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        Account account = Account.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(encodedPassword)
                .role(Role.USER)
                .balance(dto.getBalance())
                .createdAt(LocalDateTime.now())
                .build();

        Account savedAccount = accountService.createAccount(account);

        String token = jwtService.generateToken(savedAccount);

        return AuthResponseDto.builder()
                .token(token)
                .name(savedAccount.getName())
                .email(savedAccount.getEmail())
                .balance(savedAccount.getBalance())
                .build();


    }

}
