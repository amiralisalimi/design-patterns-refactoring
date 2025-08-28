package com.building.energy.state;

public class ActiveState implements EnergyState {
    @Override
    public int getEnergyConsumption() {
        return 100;
    }

    @Override
    public String getStatus() {
        return "System is fully active.";
    }
}
