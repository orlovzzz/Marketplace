package com.example.service;

import com.example.entity.Client;
import com.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

//    @Autowired
//    private BCryptPasswordEncoder encoder;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(int id) {;
        return clientRepository.findById(id).orElse(null);
    }

    public Client getClientByLogin(String login) {
        Client client = clientRepository.findByLogin(login);
        if (client == null) {
            return null;
        }
        return client;
    }

    public void addClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

    public void changeClient(int id, Client client) {
        Client client_f = clientRepository.findById(id).get();
        if (client_f != null) {
            if (client.getName() != null) client_f.setName(client.getName());
            if (client.getSurname() != null) client_f.setSurname(client.getSurname());
            if (client.getEmail() != null) client_f.setEmail(client.getEmail());
            if (client.getLogin() != null) client_f.setLogin(client.getLogin());
            if (client.getPassword() != null) {
                client_f.setPassword(passwordEncoder.encode(client.getPassword()));
            }
            clientRepository.save(client_f);
        }
    }

}
