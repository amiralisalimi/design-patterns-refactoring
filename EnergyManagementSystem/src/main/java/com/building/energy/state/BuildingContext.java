package com.building.energy.state;

public class BuildingContext {
    private EnergyState currentState;

    public BuildingContext() {
        this.currentState = new ActiveState();
    }

    public void setState(EnergyState state) {
        this.currentState = state;
    }

    public EnergyState getCurrentState() {
        return currentState;
    }

    public int getCurrentConsumption() {
        return currentState.getEnergyConsumption();
    }

    public String showStatus() {
        return currentState.getStatus();
    }
}

