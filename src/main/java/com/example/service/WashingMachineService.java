package com.example.service;

import com.example.entity.Book;
import com.example.entity.WashingMachine;
import com.example.repository.WashingMachineRepository;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("machineS")
@Getter
public class WashingMachineService {

    @Autowired
    @Qualifier("machine")
    private WashingMachineRepository washingMachineRepository;

    public List<WashingMachine> getAllWashingMachines() {
        return washingMachineRepository.findAll();
    }

    public WashingMachine getWashingMachineById(int id) {
        return washingMachineRepository.findById(id).orElse(null);
    }

    public void addWashingMachine(WashingMachine washingMachine) {
        washingMachineRepository.save(washingMachine);
    }

    public void deleteWashingMachine(int id) {
        washingMachineRepository.deleteById(id);
    }

    public void changeWashingMachine(int id, WashingMachine washingMachine) {
        WashingMachine washingMachine_c = getWashingMachineById(id);
        if (washingMachine_c != null) {
            if (washingMachine.getPerformance() != 0) washingMachine_c.setPerformance(washingMachine.getPerformance());
            if (washingMachine.getTankSize() != 0) washingMachine_c.setTankSize(washingMachine.getTankSize());
            if (washingMachine.getName() != null) washingMachine_c.setName(washingMachine.getName());
            if (washingMachine.getNumberSeller() != null) washingMachine_c.setNumber(washingMachine.getNumber());
            if (washingMachine.getPrice() != 0) washingMachine_c.setPrice(washingMachine.getPrice());
            if (washingMachine.getNumber() != 0) washingMachine_c.setNumber(washingMachine.getNumber());
            washingMachineRepository.save(washingMachine_c);
        }
    }

    public WashingMachine getWashingMachineByName(String name) {
        List<WashingMachine> machines = washingMachineRepository.findByName(name);
        if(!machines.isEmpty()) {
            return machines.get(0);
        } else {
            return null;
        }
    }

}