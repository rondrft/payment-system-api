package com.mercado.pagos.service;

import com.mercado.pagos.exception.DuplicateEmailException;
import com.mercado.pagos.model.Account;
import com.mercado.pagos.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void checkEmailNotExist(String email) {
        if (accountRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email is already in use");
        }
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

}
