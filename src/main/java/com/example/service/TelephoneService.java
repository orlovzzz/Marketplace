package com.example.service;

import com.example.entity.Telephone;
import com.example.repository.TelephoneRepository;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("phoneS")
@Getter
public class TelephoneService {

    @Autowired
    @Qualifier("phone")
    private TelephoneRepository telephoneRepository;

    public List<Telephone> getAllTelephones() {
        return telephoneRepository.findAll();
    }

    public Telephone getTelephoneById(int id) {
        return telephoneRepository.findById(id).orElse(null);
    }

    public void addTelephone(Telephone telephone) {
        telephoneRepository.save(telephone);
    }

    public void deleteTelephone(int id) {
        telephoneRepository.deleteById(id);
    }

    public void changeTelephone(int id, Telephone telephone) {
        Telephone telephone_c = getTelephoneById(id);
        if (telephone_c != null) {
            if (telephone.getPerformance() != 0) telephone_c.setPerformance(telephone.getPerformance());
            if (telephone.getAccSize() != 0) telephone_c.setAccSize(telephone.getAccSize());
            if (telephone.getName() != null) telephone_c.setName(telephone.getName());
            if (telephone.getNumberSeller() != null) telephone_c.setNumber(telephone.getNumber());
            if (telephone.getPrice() != 0) telephone_c.setPrice(telephone.getPrice());
            if (telephone.getNumber() != 0) telephone_c.setNumber(telephone.getNumber());
            telephoneRepository.save(telephone_c);
        }
    }

    public Telephone getTelephoneByName(String name) {
        List<Telephone> telephones = telephoneRepository.findByName(name);
        if(!telephones.isEmpty()) {
            return telephones.get(0);
        } else {
            return null;
        }
    }

}
