package org.llamawow.controller;

import jakarta.servlet.http.*;
import org.llamawow.dto.AccountCreateDto;
import org.llamawow.service.AccountService;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

@Controller
public class AccountController {
    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;

    }

    @PostMapping("/register")
    public String saveStudent(Model model,
                              @ModelAttribute("register") AccountCreateDto createDto,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request) {

        String clientIp = request.getRemoteAddr();

        accountService.create(createDto, clientIp);
        redirectAttributes.addFlashAttribute("successMessage", "¡Registro exitoso!");
        return "redirect:/downloads";
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
