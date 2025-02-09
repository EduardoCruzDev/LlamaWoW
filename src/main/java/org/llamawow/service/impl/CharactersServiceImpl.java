package org.llamawow.service.impl;

import org.llamawow.repository.characters.CharactersRepository;

public class CharactersServiceImpl {

    private final CharactersRepository charactersRepository;


    public CharactersServiceImpl(CharactersRepository charactersRepository) {
        this.charactersRepository = charactersRepository;
    }
}
