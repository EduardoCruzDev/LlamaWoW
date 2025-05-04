package org.llamawow.dto;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.*;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

@Getter
@Setter
public class AccountCreateDto {
    @NotNull
    @Length(min = 5, max = 20)
    private String username;
    @NotNull
    private String password;
    @NotNull
    @Email
    private String email;
}
