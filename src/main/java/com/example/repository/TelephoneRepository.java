package com.example.repository;

import com.example.entity.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("phone")
public interface TelephoneRepository extends JpaRepository<Telephone, Integer> {
    List<Telephone> findByName(String name);
}
