package org.llamawow.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llamawow.dto.LoginDto;
import org.llamawow.entity.AccountEntity;
import org.llamawow.model.ParamsEncrypt;
import org.llamawow.repository.auth.AccountRepository;
import org.llamawow.service.impl.EncryptionService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private EncryptionService encryptionService;
    @Mock
    private Model model;
    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginExitoso() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("usuario");
        loginDto.setPassword("password");
        AccountEntity account = new AccountEntity();
        account.setUsername("usuario");
        account.setSalt(new byte[]{1,2,3});
        account.setVerifier(new byte[]{10,20,30});
        when(accountRepository.findByUsername("usuario")).thenReturn(Optional.of(account));
        try (MockedStatic<EncryptionService> mocked = mockStatic(EncryptionService.class)) {
            mocked.when(() -> EncryptionService.computeVerifier(ParamsEncrypt.trinitycore, account.getSalt(), "USUARIO", "password"))
                .thenReturn(new byte[]{10,20,30});
            String result = loginController.loginPost(loginDto, model);
            assertEquals("redirect:/profile", result);
        }
    }

    @Test
    void testLoginUsuarioIncorrecto() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("noexiste");
        loginDto.setPassword("password");
        when(accountRepository.findByUsername("noexiste")).thenReturn(Optional.empty());
        String result = loginController.loginPost(loginDto, model);
        assertEquals("login", result);
        verify(model).addAttribute("paramError", true);
    }

    @Test
    void testLoginPasswordIncorrecta() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("usuario");
        loginDto.setPassword("malapass");
        AccountEntity account = new AccountEntity();
        account.setUsername("usuario");
        account.setSalt(new byte[]{1,2,3});
        account.setVerifier(new byte[]{10,20,30});
        when(accountRepository.findByUsername("usuario")).thenReturn(Optional.of(account));
        try (MockedStatic<EncryptionService> mocked = mockStatic(EncryptionService.class)) {
            mocked.when(() -> EncryptionService.computeVerifier(ParamsEncrypt.trinitycore, account.getSalt(), "USUARIO", "malapass"))
                .thenReturn(new byte[]{99,99,99});
            String result = loginController.loginPost(loginDto, model);
            assertEquals("login", result);
            verify(model).addAttribute("paramError", true);
        }
    }
}
