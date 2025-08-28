package com.building.energy.strategy;

public class PeakCostStrategy implements CostStrategy {
    @Override
    public int calculateCost(int units) {
        return units * 1000;
    }
}
