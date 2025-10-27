package com.example.Compulsory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Garden {

    private final WaterPumpService pumpFromConstructor;
    private WaterPumpService pumpFromSetter;
    private WaterPumpService pumpFromMethod;

    @Autowired
    public Garden(WaterPumpService waterPumpService) {
        System.out.println("[Garden] Constructor injection");
        this.pumpFromConstructor = waterPumpService;
    }

    @Autowired
    private WaterPumpService pumpFromField;

    @Autowired
    public void setPumpFromSetter(WaterPumpService waterPumpService) {
        System.out.println("[Garden] Setter injection");
        this.pumpFromSetter = waterPumpService;
    }

    @Autowired
    public void prepareWaterSystem(WaterPumpService waterPumpService) {
        System.out.println("[Garden] Method injection");
        this.pumpFromMethod = waterPumpService;
    }

    public void waterPlants() {
        System.out.println("[Garden] Watering plants...");
        pumpFromConstructor.pumpWater();
        pumpFromField.pumpWater();
        pumpFromSetter.pumpWater();
        pumpFromMethod.pumpWater();
    }

}
