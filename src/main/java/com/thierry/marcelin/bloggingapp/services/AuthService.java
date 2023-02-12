package com.thierry.marcelin.bloggingapp.services;

import com.thierry.marcelin.bloggingapp.payload.dto.LoginDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);
}
