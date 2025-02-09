package org.llamawow.service.impl;


import org.llamawow.dto.AccountCreateDto;
import org.llamawow.entity.AccountEntity;
import org.llamawow.model.ParamsEncrypt;

import org.llamawow.repository.auth.AccountRepository;
import org.llamawow.service.AccountService;
import org.springframework.stereotype.*;

import java.security.*;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }


    @Override
    public void create(AccountCreateDto request, String ip) {


        if (accountRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("The user is already registered");
        }

        SecureRandom random = new SecureRandom();

        try {
            byte[] salt = new byte[32];
            random.nextBytes(salt);

            final String username = request.getUsername();

            byte[] verifier = EncryptionService.computeVerifier(ParamsEncrypt.trinitycore, salt, username.toUpperCase(),
                    request.getPassword());
            AccountEntity account = new AccountEntity();
            account.setSalt(salt);
            account.setVerifier(verifier);
            account.setLocked(false);
            account.setUsername(username);
            account.setEmail(request.getEmail());
            accountRepository.save(account);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
