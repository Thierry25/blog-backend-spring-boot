package com.thierry.marcelin.bloggingapp.payload.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @Size(min = 2, max = 100)
    private String name;
    @Email
    private String email;
    @Size(min = 2, max = 24)
    private String username;
    @Size(min = 12, max = 64)
    private String password;
}
