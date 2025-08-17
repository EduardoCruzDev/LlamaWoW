package org.llamawow.controller;

import org.llamawow.dto.CharactersDto;
import org.llamawow.dto.EquippedItem;
import org.llamawow.entity.CharacterEntity;
import org.llamawow.repository.characters.CharactersRepository;
import org.llamawow.service.CharactersService;
import org.llamawow.service.impl.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/userpanel")
public class UserpanelController {

    private final CharactersService charactersService;


    public UserpanelController(CharactersService charactersService) {
        this.charactersService = charactersService;
    }

    @GetMapping("/")
    public String ranking(Model model) {
        try {
            List<CharactersDto> characters = charactersService.getAllCharacters();
            model.addAttribute("characters", characters);
            return "equipment";
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            model.addAttribute("errorMessage", "An error occurred while fetching characters details.");
            return "error";  // A custom error page
        }
    }


    @Autowired
    private CharactersRepository charactersRepository;

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/character/{name}/equipment")
    public String viewEquipment(@PathVariable String name, Model model) {
        System.out.println("Entrando a viewEquipment con name = " + name);
        Optional<CharacterEntity> characterOpt = charactersRepository.findByNameIgnoreCase(name);

        if (characterOpt.isPresent()) {
            Integer characterId = characterOpt.get().getGuid();  // Asegúrate de que esto sea correcto
            System.out.println("ID del personaje obtenido: " + characterId);
            List<EquippedItem> items = inventoryService.getEquippedItemsByCharacterId(characterId);

            model.addAttribute("characterName", name);
            model.addAttribute("equippedItems", items);
            model.addAttribute("characterImageUrl", "/images/mi_personaje.jpg");

            return "equipment";
        } else {
            return "error";
        }
    }
}
