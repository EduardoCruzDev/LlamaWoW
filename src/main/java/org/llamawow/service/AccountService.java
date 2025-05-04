package org.llamawow.service;


import org.llamawow.dto.AccountCreateDto;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

public interface AccountService {
    void create(AccountCreateDto request, String ip);
}
