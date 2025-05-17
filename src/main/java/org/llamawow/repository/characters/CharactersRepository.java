package org.llamawow.repository.characters;


import org.llamawow.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

@Repository
public interface CharactersRepository extends JpaRepository<CharacterEntity, Long> {
    Optional<CharacterEntity> findByNameIgnoreCase (String name);
}
