package com.example.repository;

import com.example.entity.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("machine")
public interface WashingMachineRepository extends JpaRepository<WashingMachine, Integer> {
    List<WashingMachine> findByName(String name);
}
