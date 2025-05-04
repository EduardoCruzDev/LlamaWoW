package org.llamawow.controller;


import org.llamawow.service.impl.EncryptionService;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

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
