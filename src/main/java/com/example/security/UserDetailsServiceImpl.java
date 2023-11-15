package com.example.security;

import com.example.entity.Client;
import com.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Client client = clientRepository.findByLogin(login);

        if (client == null) throw new UsernameNotFoundException("Login" + login + "not found");

        return new UserDetailsImpl (
                client.getLogin(),
                client.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(client.getRole()))
        );
    }
}
