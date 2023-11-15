package com.example.service;

import com.example.DTO.User;
import com.example.entity.Client;
import com.example.repository.ClientRepository;
import com.example.security.JwtUtil;
import com.example.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;

    public String becomeSeller(String login) {
        Client client = clientRepository.findByLogin(login);
        client.setRole("ROLE_SELLER");
        clientRepository.save(client);
        return "Successful";
    }

    public String register(User user) {
        if (clientRepository.findByLogin(user.getLogin()) == null) {
            Client client = new Client();
            client.setLogin(user.getLogin());
            client.setPassword(encoder.encode(user.getPassword()));
            client.setRole("ROLE_USER");
            clientRepository.save(client);
            return "Successful";
        } else {
            return "User already exists";
        }
    }

    public String login(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getLogin(),
                        user.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(userDetails.toString());

        Client client = clientRepository.findByLogin(userDetails.getUsername());

        String jwt = jwtUtil.generateAccessToken(client.getLogin());

        return jwt;
    }
}