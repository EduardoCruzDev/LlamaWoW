package org.llamawow.controller;


import org.llamawow.dto.LoginDto;
import org.llamawow.entity.AccountEntity;
import org.llamawow.model.ParamsEncrypt;
import org.llamawow.repository.auth.AccountRepository;
import org.llamawow.service.impl.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EncryptionService encryptionService;

    @GetMapping("/login")
    public String login(Model model) {
        logger.info("Acceso a la página de login");
        model.addAttribute("loginDto", new LoginDto());
        return "login";  // Nombre de la vista Thymeleaf
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("loginDto") LoginDto loginDto, Model model) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        logger.info("Intento de login para usuario: {}", username);
        AccountEntity account = accountRepository.findByUsername(username).orElse(null);
        if (account != null) {
            try {
                byte[] computedVerifier = EncryptionService.computeVerifier(
                        ParamsEncrypt.trinitycore,
                        account.getSalt(),
                        username.toUpperCase(),
                        password
                );
                if (java.util.Arrays.equals(computedVerifier, account.getVerifier())) {
                    logger.info("Login exitoso para usuario: {}", username);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        account.getUsername(), null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);


                    return "redirect:/profile";
                } else {
                    logger.warn("Login fallido: verificador incorrecto para usuario: {}", username);
                }
            } catch (Exception e) {
                logger.error("Error en el proceso de verificación para usuario: {}", username, e);
            }
        } else {
            logger.warn("Login fallido: usuario no encontrado: {}", username);
        }
        model.addAttribute("paramError", true);
        return "login";
    }
}
