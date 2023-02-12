package com.thierry.marcelin.bloggingapp.services;

import com.thierry.marcelin.bloggingapp.payload.dto.LoginDTO;
import com.thierry.marcelin.bloggingapp.payload.dto.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);
}
