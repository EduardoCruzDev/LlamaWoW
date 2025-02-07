package org.llamawow.service;


import org.llamawow.dto.AccountCreateDto;

public interface AccountService {
    void create(AccountCreateDto request, String ip);
}
