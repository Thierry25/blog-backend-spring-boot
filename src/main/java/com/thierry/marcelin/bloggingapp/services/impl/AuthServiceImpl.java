package com.thierry.marcelin.bloggingapp.services.impl;

import com.thierry.marcelin.bloggingapp.exceptions.ResourceNotFoundException;
import com.thierry.marcelin.bloggingapp.models.Role;
import com.thierry.marcelin.bloggingapp.models.User;
import com.thierry.marcelin.bloggingapp.payload.dto.LoginDTO;
import com.thierry.marcelin.bloggingapp.payload.dto.RegisterDTO;
import com.thierry.marcelin.bloggingapp.repositories.RoleRepository;
import com.thierry.marcelin.bloggingapp.repositories.UserRepository;
import com.thierry.marcelin.bloggingapp.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           ModelMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        var authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Successfully logged-in";
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        if(userRepository.existsByUsername(registerDTO.getUsername()))
            throw new ResourceNotFoundException("Username already taken");
        if(userRepository.existsByEmail(registerDTO.getEmail()))
            throw new ResourceNotFoundException("Email already in use");

        var userDetails = mapToEntity(registerDTO);
        userDetails.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        var roles = new HashSet<Role>();
        var currentRole = roleRepository.findByName("ROLE_USER").orElseThrow();
        roles.add(currentRole);
        userDetails.setRoles(roles);
        userRepository.save(userDetails);
        return "Successfully Saved";
    }

    private User mapToEntity(RegisterDTO registerDTO){
        return mapper.map(registerDTO, User.class);
    }
}
