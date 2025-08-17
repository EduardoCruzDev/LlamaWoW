package org.llamawow.controller;

import org.llamawow.entity.AccountEntity;
import org.llamawow.repository.auth.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

@Controller
public class ProfileController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/profile")
    public String profile( Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Entrando al método profile");
        if (auth == null) {
            logger.warn("Authentication es null");
            return "redirect:/login";
        }
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            logger.warn("Usuario no autenticado");
            return "redirect:/login";
        }
        String username = auth.getName();
        logger.info("Acceso a perfil por usuario: {}", username);
        AccountEntity account = accountRepository.findByUsername(username).orElse(null);
        if (account != null) {
            logger.info("Datos de perfil obtenidos para usuario: {}", username);
            model.addAttribute("username", account.getUsername());
            model.addAttribute("email", account.getEmail());
        } else {
        }
        return "profile";
    }
}

