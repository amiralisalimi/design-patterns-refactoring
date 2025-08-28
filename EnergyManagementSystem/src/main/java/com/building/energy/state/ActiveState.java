package com.building.energy.state;

public class ActiveState implements EnergyState {
    @Override
    public int getEnergyConsumption() {
        return 0;
    }

    @Override
    public String getStatus() {
        return null;
    }
}
