package com.boyo.deliveryservice.services.implementations;

import com.boyo.deliveryservice.models.Account;
import com.boyo.deliveryservice.repositories.AccountRepository;
import com.boyo.deliveryservice.services.interfaces.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }
}
