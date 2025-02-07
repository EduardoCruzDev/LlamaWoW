package org.llamawow.controller;

import jakarta.servlet.http.*;
import org.llamawow.dto.AccountCreateDto;
import org.llamawow.service.AccountService;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {
    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;

    }

    @PostMapping("/register")
    public String saveStudent(
            @ModelAttribute("register") AccountCreateDto createDto,

            HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();

        accountService.create(createDto, clientIp);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("register", new AccountCreateDto());
        return "register";
    }

    @GetMapping("/")
    public String home(Model model) {
        return "main";
    }

}
