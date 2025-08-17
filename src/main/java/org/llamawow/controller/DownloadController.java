package org.llamawow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@Controller
public class DownloadController {
    @GetMapping("/downloads")
    public String downloads(Model model, RedirectAttributesModelMap redirectAttributes) {
            if (!model.containsAttribute("successMessage")) {
                model.addAttribute("successMessage", null);
            }
            return "downloads";
        }

}
