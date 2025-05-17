package org.llamawow.controller;


import org.llamawow.dto.LoginDto;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

@Controller
public class LoginController {


        @GetMapping("/login")
        public String login() {
            return "login";  // Nombre de la vista Thymeleaf
        }

        @PostMapping("/login")
    public String login(@ModelAttribute("loginDto") LoginDto loginDto, Model model ) {

    String username = loginDto.getUsername();
    String password = loginDto.getPassword();
    if("admin".equals(username) && "admin".equals(password)){
        return "equipment";
    }
    model.addAttribute("InvalidCredentials",true);
       return "login";
    }
}

