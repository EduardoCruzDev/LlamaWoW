package org.llamawow.controller;


import org.llamawow.dto.CharactersDto;
import org.llamawow.service.CharactersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RankingController {


    private final CharactersService charactersService;


    public RankingController(CharactersService charactersService) {
        this.charactersService = charactersService;
    }

    @GetMapping("/ranking")
    public String ranking(Model model) {
        try {
            List<CharactersDto> characters = charactersService.getAllCharacters();
            model.addAttribute("characters", characters);
            return "ranking";
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            model.addAttribute("errorMessage", "An error occurred while fetching characters.");
            return "error";  // A custom error page
        }
    }
    }

