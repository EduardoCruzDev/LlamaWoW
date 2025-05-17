package org.llamawow.service.impl;

import org.llamawow.dto.CharactersDto;
import org.llamawow.entity.CharacterEntity;
import org.llamawow.repository.characters.CharactersRepository;
import org.llamawow.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

@Service
public class CharactersServiceImpl implements CharactersService {

    private final CharactersRepository charactersRepository;

    @Autowired
    public CharactersServiceImpl(CharactersRepository charactersRepository) {
        this.charactersRepository = charactersRepository;
    }

    @Override
    public List<CharactersDto> getAllCharacters() {
        return charactersRepository.findAll().stream()
                .sorted(Comparator.comparing(CharacterEntity::getTotalKills).reversed()) // Ordenar de mayor a menor
                .map(character -> new CharactersDto(character.getName(), character.getRace(), character.getTotalKills()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CharactersDto> getCharactersByName(String name) {
        return charactersRepository.findByNameIgnoreCase(name)
                .map(character -> new CharactersDto(character.getName(), character.getRace(), character.getTotalKills()));
    }
}
