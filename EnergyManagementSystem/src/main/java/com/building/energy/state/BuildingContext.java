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
}
