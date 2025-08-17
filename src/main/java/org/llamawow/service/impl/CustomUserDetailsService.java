package org.llamawow.service.impl;




import org.llamawow.entity.AccountEntity;
import org.llamawow.repository.auth.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final EncryptionService encryptionService;




    public CustomUserDetailsService(AccountRepository accountRepository, EncryptionService encryptionService) {

        this.accountRepository = accountRepository;
        this.encryptionService = encryptionService;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User
                .withUsername(account.getUsername())
                .password(account.getPassword())  // No se usa la contraseña en texto claro porque ya la verificamos con el "verifier"
                .roles("USER")  // Asume roles según tu lógica
                .build();
    }
}
