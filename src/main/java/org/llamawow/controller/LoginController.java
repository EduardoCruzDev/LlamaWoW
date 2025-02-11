package org.llamawow.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.llamawow.dto.AccountCreateDto;
import org.llamawow.dto.LoginDto;
import org.llamawow.service.impl.EncryptionService;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final EncryptionService encryptionService;
    public LoginController(EncryptionService encryptionService){
        this.encryptionService = encryptionService;
    }


        @GetMapping("/login")
        public String login() {
            return "login";  // Nombre de la vista Thymeleaf
        }
}
