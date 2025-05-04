package org.llamawow.dto;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

        @NotNull
        private String username;
        @NotNull
        private String password;

}
