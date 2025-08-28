package com.building.energy.state;

public class EcoState implements EnergyState {
    @Override
    public int getEnergyConsumption() {
        return 40;
    }

    @Override
    public String getStatus() {
        return "System is in eco mode.";
    }
}

