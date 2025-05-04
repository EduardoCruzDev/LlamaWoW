package org.llamawow.repository.auth;


import org.llamawow.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByUsername(String username);
}
