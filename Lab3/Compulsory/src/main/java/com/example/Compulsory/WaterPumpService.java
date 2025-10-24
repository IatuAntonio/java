package com.example.Compulsory;

import org.springframework.stereotype.Service;

@Service
public class WaterPumpService {

    public void pumpWater() {
        System.out.println("[WaterPumpService] Pumping water to the garden...");
    }

}
