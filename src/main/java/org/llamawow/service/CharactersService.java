package org.llamawow.service;

import org.llamawow.dto.CharactersDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

@Service
public interface CharactersService {
    List<CharactersDto> getAllCharacters();
    Optional<CharactersDto> getCharactersByName(String name);
}
